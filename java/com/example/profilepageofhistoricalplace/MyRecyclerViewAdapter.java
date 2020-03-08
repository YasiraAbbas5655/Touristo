package com.example.profilepageofhistoricalplace;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<MyData> arr=new ArrayList<>();

    public MyRecyclerViewAdapter(List<MyData> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_layout,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.PersonName.setText(arr.get(i).getPersonName());
        myViewHolder.Comment.setText(arr.get(i).getComment());


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView MyImage;
        TextView PersonName;
        TextView Comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            MyImage=itemView.findViewById(R.id.Image_Of_Person_Commenting);
            PersonName=itemView.findViewById(R.id.Name_Of_Person);
            Comment=itemView.findViewById(R.id.Comment);
        }
    }
}
