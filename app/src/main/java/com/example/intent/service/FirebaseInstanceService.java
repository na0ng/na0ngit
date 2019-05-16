package com.example.intent.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.intent.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseInstanceService extends FirebaseMessagingService {

    /*구글 token 얻기
    * token은 단말별 고유한 값으로 푸시를 보낼때 사용됨 */
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("kny_newToken", s);
    }

    /*메시지 받았을때, 메시지에 대한 구현 부분*/
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        if(remoteMessage != null && remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage);
        }
    }

    /* remoteMessage 안에 getData와 getNotification */
    private void sendNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        // oreo버전(VERSION_CODES.O)부터 Notification Channel이 없으면 푸시가 생성되지 않는 현상 발생
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channel = "나옹채널";
            String channel_nm = "채널명";

            NotificationManager notichannel = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(channel, channel_nm, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("채널 설명");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(false);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});

            notichannel.createNotificationChannel(notificationChannel);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channel)
                    .setSmallIcon(R.drawable.pica0)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setChannelId(channel)
                    .setAutoCancel(true) /*클릭하면 없어지는거*/
                    .setDefaults(Notification.DEFAULT_ALL);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(9999, notificationBuilder.build());
        } else {
            Notification.Builder notificationBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.pica0)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(9999, notificationBuilder.build());
        }
    }
}
