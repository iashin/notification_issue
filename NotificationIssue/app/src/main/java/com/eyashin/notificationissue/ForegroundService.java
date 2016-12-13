package com.eyashin.notificationissue;

import java.util.concurrent.TimeUnit;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import static com.eyashin.notificationissue.NotificationUtils.createNotification;

public class ForegroundService extends IntentService {

    private static final String TAG = ForegroundService.class.getSimpleName();

    private static final int FOREGROUND_SERVICE_ID = 30685;

    public ForegroundService() {
        super("name");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "Service created");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "Intent handled");
        startForeground(FOREGROUND_SERVICE_ID, createNotification(this, "Foreground notification", true));
        synchronized (this) {
            try {
                wait(TimeUnit.MINUTES.toMillis(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Service destroyed");
    }
}