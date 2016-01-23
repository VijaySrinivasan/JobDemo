package com.vijaysrini.jobdemo.common;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.vijaysrini.jobdemo.R;

/**
 * Created by vijaysrinivasan on 10/1/15.
 */
public class MyNotificationManager {

    public static final int NOTIFICATION_ID = 1;

    public static void createNotification(Activity callingActivity, String text, String subText) {
        Log.i("MyNotificationManager","creating notification");
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://docs.android.com/"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("jobdemo://BBActivity"));
        PendingIntent pendingIntent =  PendingIntent.getActivity(callingActivity,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(callingActivity);
        builder
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.android_icon_cc)
                .setContentTitle(callingActivity.getResources().getString(R.string.demo_name))
                .setContentText(text)
                .setSubText(subText)
                .setDefaults(Notification.DEFAULT_SOUND)
        ;

        NotificationManager notificationManager = (NotificationManager) callingActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());
        Log.i("MyNotificationManager","done attempting creating notification");
    }
}
