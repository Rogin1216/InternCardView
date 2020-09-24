package com.test.interncardview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.test.interncardview.Database.PriceList;
import com.test.interncardview.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.test.interncardview.Database.MemberList;


public class MyViewHolderMember extends FirebaseRecyclerAdapter <MemberList, MyViewHolderMember.ViewHolder> {

    public MyViewHolderMember(@NonNull FirebaseRecyclerOptions<MemberList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolderMember.ViewHolder holder, int position, @NonNull MemberList model) {
        holder.name_list.setText(model.getFullname());
    }

    @NonNull
    @Override
    public MyViewHolderMember.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_view_layout, parent, false);
        return new MyViewHolderMember.ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_list = (TextView) itemView.findViewById(R.id.name_list);

        }
    }

}
