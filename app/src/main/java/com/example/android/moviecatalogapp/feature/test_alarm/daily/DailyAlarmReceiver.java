package com.example.android.moviecatalogapp.feature.test_alarm.daily;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.android.moviecatalogapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DailyAlarmReceiver extends BroadcastReceiver{
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent){
        String title = context.getString(R.string.app_name);
        String message = context.getString(R.string.message_daily_reminder);
        int notifId = 101;
        showAlarmNotification(context, title, message, notifId);
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager)context.
                getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentTitle(title)
                .setContentText("Hi User, Have a nice day ! :D")
                .setColor(ContextCompat.getColor(context, android.R.color.holo_orange_light))
                .setVibrate(new long[]{900,900,900,900})
                .setSound(alarmSound);
        notificationManagerCompat.notify(notifId, builder.build());
    }

    public void setRepeatingAlarm(Context context, String time, String message, boolean isShowToast){
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyAlarmReceiver.class);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        Log.d(TAG, "repeating time: " + new SimpleDateFormat("HH:mm", Locale.US)
                .format(calendar.getTime())
        );

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                101,
                intent,
                0
        );

        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );

        if (isShowToast){
            Toast.makeText(context,"Repeating alarm set up", Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyAlarmReceiver.class);
        int requestCode = 101;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                0
        );
        alarmManager.cancel(pendingIntent);
    }
}
