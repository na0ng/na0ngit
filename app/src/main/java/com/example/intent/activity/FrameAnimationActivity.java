package com.example.intent.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.intent.R;

public class FrameAnimationActivity extends AppCompatActivity {

    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        final Button actionBtn = findViewById(R.id.actionBtn);
        ImageView img = findViewById(R.id.img);


        //ImageView의 배경으로 지정한 애니메이션 설정파일을 객체로 얻어옴
        animation = (AnimationDrawable) img.getDrawable();
        animation.start();


        actionBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(animation.isRunning()) { //애니메이션 실행중일 경우

                    animation.stop();
                    actionBtn.setText("뛰어");

                } else { //애니메이션 멈춰있는 경우

                    animation.start();
                    actionBtn.setText("멈춰");
                }
            }
        });

    }
}
