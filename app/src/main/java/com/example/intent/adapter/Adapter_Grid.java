package com.example.intent.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.intent.R;
import com.example.intent.activity.ImageClickActivity;
import com.example.intent.item.GridItem;

import java.util.ArrayList;

public class Adapter_Grid extends RecyclerView.Adapter<Adapter_Grid.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<GridItem> list = new ArrayList<>();

    public Adapter_Grid(ArrayList<GridItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
/*    ViewHolder 생성 시 호출 (View Type에 따라 여러 타입의 ViewHolder를 생성 가능)
    * Layout을 ViewHolder에 저장*/
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    //화면에 새로운 View가 붙을 때마다 호출되므로, 실질적인 데이터 처리
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final GridItem gridItem = list.get(i);

        Glide.with(context)
                .load(gridItem.getImg()) //.load(gridItem.getUrl())
                .into(myViewHolder.img);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(context, ImageClickActivity.class);
                intent.putExtra("selectItem", i); // 내가 선택한 img position
                intent.putExtra("totalItem",  list); // img 전체

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        //public PhotoView img;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgView);
            }

        }
    }
