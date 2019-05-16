package com.example.intent.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.intent.IMyAidlInterface;
import com.example.intent.R;
import com.example.intent.service.MusicService;

public class MusicActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnStop;
    private Button btnTime;

    private boolean isService = false;
    private int resultCnt = 0;

    private IMyAidlInterface binder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            /*
            * 서비스가 가지고 있는 binder를 전달받는다.
            * (서비스에서 구체화한 getCount()메소드를 받는다.*/

            binder = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btnStart = findViewById(R.id.StartBtn);
        btnStop = findViewById(R.id.StopBtn);
        btnTime = findViewById(R.id.TimeBtn);

        //서비스시작
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //서비스 시작
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                //startService(intent);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);

                Toast.makeText(getApplicationContext(), "서비스가 시작되었습니다.", Toast.LENGTH_SHORT).show();

                isService = true;
                new Thread (new GetCountThread()).start();
            }
        });

        //서비스종료
        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //서비스종료
                /*Intent intent = new Intent(getApplicationContext(), MusicService.class);
                stopService(intent);*/

                unbindService(conn);

                Toast.makeText(getApplicationContext(), "서비스가 종료되었습니다.", Toast.LENGTH_SHORT).show();
                isService = false;
            }
        });

        //수행시간
        btnTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(), "서비스가 실행중 아님", Toast.LENGTH_SHORT).show();
                    return;
                } else if (isService) {
                    Toast.makeText(getApplicationContext(), "서비스 " + resultCnt + "초째 실행중", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class GetCountThread implements Runnable {

        //binder에서 count가져와서 set하기 위해서는 handler 필요
        private Handler handler = new Handler();

        @Override
        public void run() {

            while(isService) {

                if(binder == null) {
                    continue;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            resultCnt = binder.getCount();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });

                //0.5초 텀준대
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
