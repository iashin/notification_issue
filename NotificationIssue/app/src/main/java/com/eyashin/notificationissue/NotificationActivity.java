package com.eyashin.notificationissue;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static com.eyashin.notificationissue.NotificationUtils.createNotification;

public class NotificationActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

    private View mSendNotificationButton;
    private View mStartForegroundServiceButton;
    private View mStopForegroundServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mSendNotificationButton = findViewById(R.id.send_notification_button);
        mSendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendThreeNotifications();
            }
        });

        mStartForegroundServiceButton = findViewById(R.id.start_foreground_service_button);
        mStartForegroundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForegroundService();
            }
        });

        mStopForegroundServiceButton = findViewById(R.id.stop_foreground_service_button);
        mStopForegroundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopForegroundService();
            }
        });
    }

    private void sendThreeNotifications() {
        for(int i = 0; i < 3; i++) {
            mNotificationManager.notify(i, createNotification(this, String.format("Common notification №%s", i), false));
        }
    }

    private void startForegroundService() {
        startService(new Intent(this, ForegroundService.class));
        Toast.makeText(this, "Foreground service has started", Toast.LENGTH_SHORT).show();
    }

    private void stopForegroundService() {
        boolean result = stopService(new Intent(this, ForegroundService.class));
        final String message = result ? "Foreground service has stopped" : "Foreground service isn't running";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopForegroundService();
    }
}
