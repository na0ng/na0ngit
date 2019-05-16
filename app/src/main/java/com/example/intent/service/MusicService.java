package com.example.intent.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.intent.IMyAidlInterface;

public class MusicService extends Service {

    private boolean isStop;
    private int count;


    public MusicService() {}

    /* 서비스, 액티비티 통신을 위한 Binder 객체
    * 액티비티에게 getCount() 메소드를 제공해 서비스에게 count 값을 전달한다. */
    IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {

        @Override
        public int getCount() throws RemoteException {
            return count;
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();

        // count 실행
        Thread thread = new Thread(new Counter());
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        isStop = true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {

        isStop = true;
        return super.onUnbind(intent);
    }

    private class Counter implements Runnable {

        //private int count;
        //private Handler handler = new Handler();

        @Override
        public void run() {

            for (count=0; count<10000; count++) {

                // 종료버튼 누르면 종료
                if(isStop) {

                    break;
                }

                /*
                * 쓰레드 안에서는 UI와 관련된 Toast 메시지 사용 못함
                * Handler를 통해 사용하도록 만들어줘야 한다.*/

/*                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });*/

                //1초씩 쉬게함
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
