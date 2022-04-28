package com.example.moletest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    List<Record> record;
    Context context;
    int position;
    Activity activity;
    int pos;

    public MyAdapter(Activity activity, Context ct, List<Record> record){
        this.activity = activity;
        context = ct;
        this.record = record;
        pos = 1;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        this.position = position;
        holder.pos.setText(String.valueOf(pos));
        holder.playerName.setText(record.get(position).getUser());
        holder.score.setText(record.get(position).getScore());
        pos++;
    }

    public Record getCustomer(int position){
        return record.get(position);
    }

    @Override
    public int getItemCount() {
        return record.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView playerName,score, pos;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pos = itemView.findViewById(R.id.tv_playerPosition);
            playerName = itemView.findViewById(R.id.tv_playerName);
            score = itemView.findViewById(R.id.tv_score);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
