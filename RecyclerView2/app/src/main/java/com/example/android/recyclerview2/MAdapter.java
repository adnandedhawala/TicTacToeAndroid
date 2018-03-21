package com.example.android.recyclerview2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 3/18/2018.
 */

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {

    private ArrayList<ExampleItem> arrayList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClicked(int position);
        void onDeleteClicked(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public MAdapter(ArrayList<ExampleItem> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);

        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExampleItem exampleItem=arrayList.get(position);

        holder.imageView.setImageResource(exampleItem.getmImgResource());
        holder.tv1.setText(exampleItem.getLine1());
        holder.tv2.setText(exampleItem.getLine2());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageView imgDelete;
        public TextView tv1;
        public TextView tv2;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imgView);
            imgDelete =(ImageView)itemView.findViewById(R.id.img_delete);
            tv1 = (TextView) itemView.findViewById(R.id.tvLine1);
            tv2 = (TextView) itemView.findViewById(R.id.tvLine2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClicked(position);
                        }
                    }
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClicked(position);
                        }
                    }
                }
            });
        }
    }
}
