package com.example.intent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.intent.R;

public class SubActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

    }

    public void onLVButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
        startActivity(intent);

        finish();
    }

    public void onRVButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RecycleViewActivity.class);
        startActivity(intent);

        finish();
    }
}
