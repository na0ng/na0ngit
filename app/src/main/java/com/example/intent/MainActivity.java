package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DustViewActivity.class);
                //같이 넘겨줄 텍스트가 있을 때
                // intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
    }

    public void onButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
        startActivity(intent);

        finish();
    }

    public void onButton2Clicked(View v) {
        //Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:abc@kt.com"));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(intent);

        finish();
    }

    public void onGButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), RecycleGridViewActivity.class);
        startActivity(intent);

        finish();
    }

    public void onSButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), RecycleStaggereViewActivity.class);
        startActivity(intent);

        finish();
    }

    public void onLCButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), LifeCycleActivity.class);
        startActivity(intent);
    }
}
