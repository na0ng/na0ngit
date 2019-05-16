package com.example.intent.Receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {

    private static final String BR_TAG = "kny_BroadCastReceiver";

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("나옹 BR_TEST : " + intent.getAction() + "\n");
        stringBuilder.append("URI : " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");

        String log = stringBuilder.toString();
        Log.d(BR_TAG, log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
    }
}

