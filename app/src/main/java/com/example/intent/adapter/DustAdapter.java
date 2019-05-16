package com.example.intent.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intent.R;
import com.example.intent.activity.DustMapActivity;
import com.example.intent.item.DustItem;

import java.util.ArrayList;

public class DustAdapter extends RecyclerView.Adapter<DustAdapter.MyViewHolder> {

    /*
        LayoutInflater : xml의 레이아웃을 메모리에 객체화하기 위해 사용하며,
                         화면의 일부분을 xml레이아웃 파일의 내용으로 적용하기 위해 사용
     */
    private ArrayList<DustItem> list = new ArrayList<DustItem>();
    //private LayoutInflater layoutInflater;
    private Context context;

    public DustAdapter(Context context, ArrayList<DustItem> data) {

        this.context = context;
        this.list = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        //View view = layoutInflater.inflate(R.layout.dust_item_layout,viewGroup,false);
        View view = LayoutInflater.from(context).inflate(R.layout.dust_item_layout,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {

        final DustItem data = list.get(position);

        viewHolder.stationName.setText(data.getStationName());
        viewHolder.dataTime.setText(data.getDataTime());
        viewHolder.pm10Value.setText(data.getPm10Value()+context.getString(R.string.pm_unit));
        viewHolder.pm25Value.setText(data.getPm25Value()+"㎍/㎥");
        viewHolder.pm10Grade.setText(data.getPm10Grade());
        viewHolder.pm25Grade.setText(data.getPm25Grade());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DustMapActivity.class);

                intent.putExtra("selectItem1", data.getStationName());
                //intent.putExtra("selectItem2", data.getDataTime());
                intent.putExtra("locationName", data.getLocationName());

                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                //v.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView stationName;
        public TextView dataTime;
        public TextView pm10Value;
        public TextView pm25Value;
        public TextView pm10Grade;
        public TextView pm25Grade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stationName = itemView.findViewById(R.id.areaName);
            dataTime = itemView.findViewById(R.id.date);
            pm10Value = itemView.findViewById(R.id.pm10Value);
            pm25Value = itemView.findViewById(R.id.pm25Value);
            pm10Grade = itemView.findViewById(R.id.pm10Grade);
            pm25Grade = itemView.findViewById(R.id.pm25Grade);
        }
    }
}