package com.example.team6project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ExampleViewHolder>  {


    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    private ArrayList<AdminItem> mExampleList;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mItemName;
        public TextView mItemAddress;
        public  TextView mItemPhone;



        public ExampleViewHolder(View itemView ,final OnItemClickListener listener) {
            super(itemView);

            mItemName=itemView.findViewById(R.id.item_name);
            mItemAddress=itemView.findViewById(R.id.item_status);
            mItemPhone=itemView.findViewById(R.id.item_contact_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdminAdapter(ArrayList<AdminItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        AdminItem currentItem = mExampleList.get(position);
        holder.mItemName.setText(currentItem.getItemName());
        String Address="Address: "+currentItem.getAddress();
        holder.mItemAddress.setText(Address);
        holder.mItemPhone.setText("Phone:"+currentItem.getItemPhone());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
