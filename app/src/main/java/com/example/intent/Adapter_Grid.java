package com.example.intent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Grid extends RecyclerView.Adapter<Adapter_Grid.MyViewHolder>{

    private List<GridItem> list = new ArrayList<>();
    private Context context;

    public Adapter_Grid(List<GridItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /*
    * 생성자
    * */

    @NonNull
    @Override
    /*ViewHolder 생성 시 호출 (View Type에 따라 여러 타입의 ViewHolder를 생성 가능)
    * Layout을 ViewHolder에 저장*/
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    //화면에 새로운 View가 붙을 때마다 호출되므로, 실질적인 데이터 처리
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        GridItem gridItem = list.get(i);

        Glide.with(context)
                .load(gridItem.getImg()) //.load(gridItem.getImg())

                .into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imgView);
        }

    }
}
