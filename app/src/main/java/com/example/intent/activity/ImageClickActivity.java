package com.example.intent.activity;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.intent.R;
import com.example.intent.adapter.AdapterFragment;
import com.example.intent.item.GridItem;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;


public class ImageClickActivity extends AppCompatActivity {

    //19.2.13 zoom in/out 기능으로 추가
    private int pos;
    private PhotoView photoView ;

    //19.2.14 image swipe 기능으로 추가
    private ViewPager viewPager;
    private AdapterFragment adapterFragment;


    private ArrayList<GridItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_click);

//        Object selectItem = getIntent().getSerializableExtra("selectItem");
        pos = getIntent().getIntExtra("selectItem",0);
        data = (ArrayList<GridItem>) getIntent().getSerializableExtra("totalItem");

        //19.2.13 zoom in/out
//        photoView = (PhotoView) findViewById(R.id.selectImg);
//        photoView.setImageResource(gitem.getImg());


        //19.2.14 image swipe
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapterFragment = new AdapterFragment(this,data);
        viewPager.setAdapter(adapterFragment);
        viewPager.setCurrentItem(pos);

    }
}

