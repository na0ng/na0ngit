package com.example.intent;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleGridViewActivity extends AppCompatActivity {

    RecyclerView gridView;
    private List<GridItem> list = new ArrayList<>();

    GridLayoutManager gridLayoutManager; //RecyclerView.LayoutManager gridLayoutManager;
    Adapter_Grid gAdapter ;
    private Context context;

    private int spanCnt = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_grid_view);

        gridView = (RecyclerView) findViewById(R.id.recycleGridView);
        gridLayoutManager = new GridLayoutManager(this,spanCnt); //spanCount : 한화면에 보여지는 이미지 개수 지정하는 곳인거 같음

        //gridView.setHasFixedSize(true);
        gridView.setLayoutManager(gridLayoutManager);





        for(int r=0; r <=1000; r++) {
            int result = (int) (Math.random()*4);

            //Integer picaNm = R.drawable.pica1;

            String mDrawableName = "pica"+result;
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

           //Log.d("kny", String.valueOf(picaNm));

            list.add(new GridItem(resID));

            //Log.d("kny", list.size());
        }

        gAdapter = new Adapter_Grid(list, this);
        gridView.setAdapter(gAdapter);

    }
}


