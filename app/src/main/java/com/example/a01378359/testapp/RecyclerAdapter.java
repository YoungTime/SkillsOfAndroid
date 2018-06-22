package com.example.a01378359.testapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 01378359 on 2018/6/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<String>title;
    private Context context;
//    private List<Integer> mHeights = Arrays.asList(300,400,520,130,201,510,620,410,110,201,130,510,630,110,300,400,520,130
//            ,201,510,620,410,110,201,130,510,630,110,
//            300,400,520,130,201,510,620,410,110,201,130,510,630);

    private List<Integer>mHeights;
    public RecyclerAdapter(Context context,List<String>title){
        this.context = context;
        this.title = title;
    }

    private void getRandomHeight(){
        mHeights = new ArrayList<>();
        for(int i=0; i < title.size();i++){
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int)(300+Math.random()*400));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context,R.layout.item_recycler,null);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        getRandomHeight();
        ViewGroup.LayoutParams layoutParams = holder.title.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.title.setLayoutParams(layoutParams);

        holder.title.setText(title.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delData(position);
                title.remove(position);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return title.size();
    }

    public void addData(int position){
        notifyItemInserted(position);
    }

    public void delData(int position){
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_item_show);
        }
    }
}
