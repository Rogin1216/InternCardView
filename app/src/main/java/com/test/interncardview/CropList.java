package com.test.interncardview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.test.interncardview.Adapter.MyViewHolder;
import com.test.interncardview.Database.PriceList;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CropList extends AppCompatActivity {
        EditText input_minimal, input_maximum;
        Button btn_minimum, btn_maximum,looking_for;
        ArrayList<dataUser> list = new ArrayList<>();
        AdapterItem adapterItem;
        RecyclerView recyclerView;
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        FloatingActionButton fab_add;
        AlertDialog builderAlert;
        Context context;
        LayoutInflater layoutInflater;
        View showInput;
        Calendar calendar = Calendar.getInstance();
        Locale id = new Locale("in","ID");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",id);
        Date date_min;
        Date date_max;
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_crop);

            context = this;
            input_minimal = findViewById(R.id.input_minimal);
            input_maximum = findViewById(R.id.input_maximum);
            btn_minimum = findViewById(R.id.btn_min);
            btn_maximum = findViewById(R.id.btn_max);
            looking_for = findViewById(R.id.looking_for);
            recyclerView = findViewById(R.id.recyclerView);


            btn_minimum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            calendar.set(year,month,dayOfMonth);
                            input_minimal.setText(simpleDateFormat.format(calendar.getTime()));
                            date_min = calendar.getTime();

                            String input1 = input_maximum.getText().toString();
                            String input2 = input_maximum.getText().toString();

                            if(input1.isEmpty() && input2.isEmpty()){
                                looking_for.setEnabled(false);
                            }else{
                                looking_for.setEnabled(true);
                            }
                        }
                    }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });
            btn_maximum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            calendar.set(year,month,dayOfMonth);
                            input_maximum.setText(simpleDateFormat.format(calendar.getTime()));
                            date_max = calendar.getTime();

                            String input1 = input_maximum.getText().toString();
                            String input2 = input_minimal.getText().toString();

                            if(input1.isEmpty() && input2.isEmpty()){
                                looking_for.setEnabled(false);
                            }else{
                                looking_for.setEnabled(true);
                            }
                        }
                    }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            looking_for.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Query query = database.child("Prices").orderByChild("registration").startAt(date_min.getTime()).endAt(date_max.getTime());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            showListener(snapshot);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });
            fab_add.setOnClickListener(new View.OnClickListener() {
                //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    inputData();
                }
            });
            showData();
        }

        EditText et_name,et_price,et_date_register;
        Button btn_date_register,btn_save;
        Date et_time_register;


    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void inputData(){
        builderAlert = new AlertDialog.Builder(context).create();
        layoutInflater = getLayoutInflater();
        showInput = layoutInflater.inflate(R.layout.input_layout, null);
        builderAlert.setView(showInput);

        et_name = showInput.findViewById(R.id.et_name);
        et_price = showInput.findViewById(R.id.et_price);
        et_date_register = showInput.findViewById(R.id.et_date_register);
        btn_date_register = showInput.findViewById(R.id.btn_date_register);
        btn_save = showInput.findViewById(R.id.btn_save);
        builderAlert.show();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String price = et_price.getText().toString();
                String date = et_date_register.getText().toString();

                database.child("Prices").child(name).setValue(new dataUser(
                        name,price, et_time_register.getTime()
                        )).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Data inserted",Toast.LENGTH_SHORT).show();
                        builderAlert.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Data failed",Toast.LENGTH_SHORT).show();
                        builderAlert.dismiss();
                    }
                });
            }
        });

        btn_date_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        et_date_register.setText(simpleDateFormat.format(calendar.getTime()));
                        et_time_register = calendar.getTime();
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }
    private void showData() {
        database.child("Prices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showListener(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showListener(DataSnapshot snapshot) {
        list.clear();
        for(DataSnapshot item : snapshot.getChildren()){
            dataUser user = item.getValue(dataUser.class);
            list.add(user);
        }
        adapterItem = new AdapterItem(context,list);
        recyclerView.setAdapter(adapterItem);
    }
}