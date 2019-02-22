package com.example.intent.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.intent.R;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Toast.makeText(this, "onCreate() 호출", Toast.LENGTH_LONG).show();

        Button LCButton = findViewById(R.id.LCButton);
        LCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
         });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart() 호출", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop() 호출", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy() 호출", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause() 호출", Toast.LENGTH_LONG).show();
/*        SharedPreferences sharedPreferences =  getSharedPreferences("pref", Activity.MODE_PRIVATE);
        sharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "나옹");
        editor.commit();*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume() 호출", Toast.LENGTH_LONG).show();
    }
}
