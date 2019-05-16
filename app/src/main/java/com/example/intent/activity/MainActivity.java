package com.example.intent.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.intent.R;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    //BroadcastReceiver broadcastReceiver = new MyBroadCastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //동적브로드캐스트리시버
        /*IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);*/

        Button button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DustViewActivity.class);
                //같이 넘겨줄 텍스트가 있을 때
                // intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });

        // 해시키야..
        getAppKeyHash();

    }
//해시키 가져오는 함수
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
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

    public void onSVButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
        startActivity(intent);
    }

    public void onLIButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), LayouyInflaterActivity.class);
        startActivity(intent);
    }

    public void onfileButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), FileInOutActivity.class);
        startActivity(intent);
    }

    public void onFAButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), FrameAnimationActivity.class);
        startActivity(intent);
    }

    public void onTAButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), TweenAnimationActivity.class);
        startActivity(intent);
    }

    public void onMDButtonClicked (View v) {
        Intent intent = new Intent(getApplicationContext(), MaterialDesignActivity.class);
        startActivity(intent);
    }
}
