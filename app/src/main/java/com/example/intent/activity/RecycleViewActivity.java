package com.example.intent.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.intent.R;
import com.example.intent.adapter.Adapter;
import com.example.intent.item.RecyclerItemForm;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    private List<RecyclerItemForm> data;
    private RecyclerView recyclerView;
    private LinearLayoutManager llManager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        initData();
        initllManager();
        initAdapter();
        initrecyclerView();


    }

    //1.데이터초기화
    private void initData() {
        //msg,img순
        data = new ArrayList<>();

        for (int i=1; i<=100; i++) {
            data.add(new RecyclerItemForm("나의 소듕한"+i+"번째데이터", R.drawable.ic_launcher_background));
        }
    }

    //2.LinearLayoutManager 초기화
    private void initllManager(){
        llManager = new LinearLayoutManager(this);
    }

    //3.어뎁터초기화
    private void initAdapter(){
        adapter = new Adapter(this,(ArrayList<RecyclerItemForm>) data);
    }

    //4.뷰와 레이아웃/어뎁터 연결
    private void initrecyclerView(){
        recyclerView = findViewById(R.id.recycleView1);
        recyclerView.setHasFixedSize(true); //각아이템이 균일하게 보여지도록 셋팅
        recyclerView.setLayoutManager(llManager); //RecyclerView를 레이아웃매니저에 붙임
        recyclerView.setAdapter(adapter);
        Log.d("confirm", "test recycle @@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

}