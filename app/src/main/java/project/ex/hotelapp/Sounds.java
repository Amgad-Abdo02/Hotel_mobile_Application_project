package project.ex.hotelapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

public class Sounds extends Service {
    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       int i =  intent.getIntExtra("turn",0);
        if (mp != null)
            mp.release();
       switch (i) {
           case 1:
               mp = MediaPlayer.create(this, R.raw.login);
               mp.start();
               break;
           case 2:
               mp = MediaPlayer.create(this, R.raw.book);
               mp.start();
                break;
        }
        return START_STICKY;
    }
}