package com.example.android.listtodo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 3/20/2018.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> listitems;
    private Context context;

    public TaskAdapter(List<Task> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task=listitems.get(position);

        holder.showtask.setText(task.getName());
        holder.showtime.setText(task.getDate());
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView showtask;
        TextView showtime;
        

        public ViewHolder(View itemView) {
            super(itemView);

            showtask=(TextView)itemView.findViewById(R.id.tvShowTask);
            showtime =(TextView)itemView.findViewById(R.id.tvShowTime);
        }
    }
}
