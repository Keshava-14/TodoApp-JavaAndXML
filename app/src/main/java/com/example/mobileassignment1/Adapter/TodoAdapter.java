package com.example.mobileassignment1.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment1.AddnewTask;
import com.example.mobileassignment1.MainActivity;
import com.example.mobileassignment1.Model.ToDoModel;
import com.example.mobileassignment1.R;
import com.example.mobileassignment1.Utils.DataBaseHelper;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    private List<ToDoModel> mList;
    private MainActivity activity;
    private DataBaseHelper myDB;

    public TodoAdapter(DataBaseHelper myDb, MainActivity activity){
        this.activity = activity;
        this.myDB = myDb;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item = mList.get(position);
        holder.checkBox.setText(item.getTask());
        holder.checkBox.setChecked(item.getStatus() != 0);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    myDB.updateStatus(item.getId(), 1);
                } else {
                    myDB.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public Context getContext(){
        return activity;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setTask(List<ToDoModel> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deleteTask(int position){
        ToDoModel item = mList.get(position);
        myDB.deleteTask(item.getId());

        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItems(int position){
        ToDoModel item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", item.getId());
        bundle.putString("task", item.getTask());

        AddnewTask task = new AddnewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
}
