package com.example.intent.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.intent.R;

public class TweenAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    Button scaleBtn, translateBtn, rotateBtn, alphaBtn, setBtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);

        scaleBtn = findViewById(R.id.scaleBtn);
        translateBtn = findViewById(R.id.translateBtn);
        rotateBtn = findViewById(R.id.rotateBtn);
        alphaBtn = findViewById(R.id.alphaBtn);
        setBtn = findViewById(R.id.setBtn);

        textView = findViewById(R.id.textView);

        scaleBtn.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
        rotateBtn.setOnClickListener(this);
        alphaBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Animation animation = null;

        switch ((view.getId())) {

            case R.id.scaleBtn :

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_scale);
                break;

            case R.id.translateBtn :

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_translate);
                break;

            case R.id.rotateBtn :

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_rotate);
                break;

            case R.id.alphaBtn :

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_alpha);
                break;

            case R.id.setBtn :

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_set);
                break;
        }

        textView.startAnimation(animation); //애니메이션 시작
        textView.invalidate(); //화면갱신
    }
}
