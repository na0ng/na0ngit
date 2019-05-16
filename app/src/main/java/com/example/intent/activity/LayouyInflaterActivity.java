package com.example.intent.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.intent.R;

public class LayouyInflaterActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    LinearLayout layout; //부모뷰
    Button button1,button2,button3;
    LayoutInflater layoutInflater ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layouy_inflater);

        layout = findViewById(R.id.viewGroup);

        button1 = findViewById(R.id.LIbtn1);
        button2 = findViewById(R.id.LIbtn2);
        button3 = findViewById(R.id.LIbtn3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        button1.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //frameLayout.removeAllViews();
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); //인플레이터 획득

        switch(v.getId()) {
            case R.id.LIbtn1: //방법1

                View subView1 = layoutInflater.inflate(R.layout.layoutinflater_view_1, layout);

            break;

            case R.id.LIbtn2: //방법2

                View subView2 = layoutInflater.inflate(R.layout.layoutinflater_view_2, null);
                layout.addView(subView2);

                break;

            case R.id.LIbtn3: //방법3

                //layoutInflater = LayoutInflater.from(this);
                //layoutInflater = getLayoutInflater();
                layoutInflater.inflate(R.layout.activity_main, layout, true);

                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId()) {

            case R.id.LIbtn1:
                layout.removeAllViews();

                break;
        }

        return  true;
    }
}
