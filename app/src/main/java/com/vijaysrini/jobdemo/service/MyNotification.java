package com.vijaysrini.jobdemo.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.vijaysrini.jobdemo.Constants;
import com.vijaysrini.jobdemo.R;

/**
 * Created by vijaysrinivasan on 10/1/15.
 */
public class MyNotification {

    public static final int NOTIFICATION_ID = 1;

    public static void createNotification(Activity activity, String text, String subText) {
        Log.i("MyNotification","creating notification");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://docs.android.com/"));
        PendingIntent pendingIntent =  PendingIntent.getActivity(activity,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity);
        builder
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_cast_on_1_light)
                .setContentTitle(Constants.APP_TITLE)
                .setContentText(text)
                .setSubText(subText)
                .setDefaults(Notification.DEFAULT_SOUND)
        ;
        // .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_cast_light))

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());
        Log.i("MyNotification","done attempting creating notification");
    }
}
