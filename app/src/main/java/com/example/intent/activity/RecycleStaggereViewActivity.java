package com.example.intent.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.intent.R;
import com.example.intent.adapter.Adapter_Grid;
import com.example.intent.item.GridItem;

import java.util.ArrayList;

public class RecycleStaggereViewActivity extends AppCompatActivity {

    RecyclerView gridView;
    private ArrayList<GridItem> list = new ArrayList<>();
    Adapter_Grid gAdapter;

    StaggeredGridLayoutManager staggeredGridLayoutManager; //RecyclerView.LayoutManager gridLayoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_staggere_view);

        gridView = (RecyclerView) findViewById(R.id.recycleGridView);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1); //(a,b) a:칼럼갯수, b: 0(가로정렬),1(세로정렬)
        gridView.setLayoutManager(staggeredGridLayoutManager);

        list.add(new GridItem(R.drawable.pica0));
        list.add(new GridItem(R.drawable.pica1));
        list.add(new GridItem(R.drawable.pica2));
        list.add(new GridItem(R.drawable.pica3));
        list.add(new GridItem(R.drawable.pica4));
        list.add(new GridItem(R.drawable.pica0));
        list.add(new GridItem(R.drawable.pica1));
        list.add(new GridItem(R.drawable.pica2));
        list.add(new GridItem(R.drawable.pica3));
        list.add(new GridItem(R.drawable.pica4));
        list.add(new GridItem(R.drawable.pica0));
        list.add(new GridItem(R.drawable.pica1));
        list.add(new GridItem(R.drawable.pica2));
        list.add(new GridItem(R.drawable.pica3));
        list.add(new GridItem(R.drawable.pica4));
        list.add(new GridItem(R.drawable.pica0));
        list.add(new GridItem(R.drawable.pica1));
        list.add(new GridItem(R.drawable.pica2));
        list.add(new GridItem(R.drawable.pica3));
        list.add(new GridItem(R.drawable.pica4));

        gAdapter = new Adapter_Grid(list, this);
        gridView = findViewById(R.id.recycleGridView);

        gridView.setAdapter(gAdapter);

    }

}
