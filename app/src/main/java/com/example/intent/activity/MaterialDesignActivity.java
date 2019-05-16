package com.example.intent.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.intent.R;

public class MaterialDesignActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "kny";

    private Animation fab_open, fab_close = null;
    private Boolean isFabOpen = false;
    private FloatingActionButton FABtn, FABtn1, FABtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        FABtn = findViewById(R.id.FABtn);
        FABtn1 = findViewById(R.id.FABtn1);
        FABtn2 = findViewById(R.id.FABtn2);

        FABtn.setOnClickListener(this);
        FABtn1.setOnClickListener(this);
        FABtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.FABtn :
                anim();
                Log.d(TAG, "Floating Action Button");
                Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();

                break;

            case R.id.FABtn1 :
                anim();
                Toast.makeText(this, "Btn1.ToolBar", Toast.LENGTH_SHORT).show();

                break;

            case R.id.FABtn2 :
                anim();
                Toast.makeText(this, "Button no.2", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    public void anim() {

        if(isFabOpen) {
            FABtn1.startAnimation(fab_close); //이 애니메이션 실행해주고
            FABtn2.startAnimation(fab_close);
            FABtn1.setClickable(false); //버튼은 비활성화 시킨다
            FABtn2.setClickable(false);

            isFabOpen = false;
        } else {
            FABtn1.startAnimation(fab_open);
            FABtn2.startAnimation(fab_open);
            FABtn1.setClickable(true);
            FABtn2.setClickable(true);

            isFabOpen = true;
        }
    }
}
