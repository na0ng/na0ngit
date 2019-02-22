package com.example.intent.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.intent.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    //List선언 아래 초기화는 ArrayList 생성 다른 이유 : List는 인터페이스, ArrayList는 클래스
    //기능확장에 유용하게 꼭 그 클래스형이 아니어도 되면 ~List는 List로 선언
    private List<String> data;
    //List를 데이터로 취급하는 Adapter 중 하나
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //메소드 미리 만들고 alt+enter 누르면 만들건지 물어봐서 편해
        // 1.데이터 초기화
        initData();

        // 2.Adapter 준비
        initAdapter();

        // 3.ListView에 Adapter 연결
        initListView();

    }

/*    protected void onMButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        finish();
    }*/

    //data 초기화
    private void initData() {
        data = new ArrayList<>();

        for(int i=1; i<=100; i++){
            data.add("나의 소듕한"+i+"번째 데이터");
        }
    }

    //Adapter 초기화
    private void initAdapter() {
        /*
            선언할때 인자 3개 필요
            (context(context,getApplicationContext,main 존재/ getApplicationContext로 길들여),
            레이아웃(android.R:안드로이드가 제공해주는 리소스),
            데이터)
        */
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,data);
    }

    //ListView에 Adapter 연결
    private void initListView() {
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
