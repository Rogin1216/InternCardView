package com.test.interncardview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.test.interncardview.Database.PriceList;
import com.test.interncardview.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.test.interncardview.Database.MemberList;

import java.util.List;


public class MyViewHolderMember extends FirebaseRecyclerAdapter <MemberList, MyViewHolderMember.ViewHolder> {

    public MyViewHolderMember(@NonNull FirebaseRecyclerOptions<MemberList> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MemberList model) {
            holder.fullname.setText(model.getFullname());
            //holder.current.setText(model.getCurrent());
            //holder.origin.setText(model.getOrigin());
            //holder.gender.setText(model.getGender());
            //holder.number.setText(model.getNumber());
        }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_view_layout, parent, false);
        return new ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView fullname, current, origin, gender, number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullname = (TextView) itemView.findViewById(R.id.name_list);
            //origin = (TextView) itemView.findViewById(R.id.origin_address);
            //current = (TextView) itemView.findViewById(R.id.current_address);
            //number = (TextView) itemView.findViewById(R.id.fnumber);
            //gender = (TextView) itemView.findViewById(R.id.gender);
        }
    }
}
