package com.eyashin.notificationissue;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils {

    public static Notification createNotification(Context context, String message, boolean isOngoing) {
        return new NotificationCompat.Builder(context).setOngoing(isOngoing)
                                                      .setSmallIcon(android.R.drawable.sym_def_app_icon)
                                                      .setColor(isOngoing ? Color.RED : Color.GREEN)
                                                      .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                                      .setContentTitle(context.getString(R.string.app_name))
                                                      .setContentText(message)
                                                      .build();
    }
}
