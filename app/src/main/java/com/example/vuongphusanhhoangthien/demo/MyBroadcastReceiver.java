package com.example.vuongphusanhhoangthien.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    public MyBroadcastReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.ambaothuc);
        mp.start();
    }
}
