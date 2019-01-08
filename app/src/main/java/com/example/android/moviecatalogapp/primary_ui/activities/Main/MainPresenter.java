package com.example.android.moviecatalogapp.primary_ui.activities.Main;

import android.content.Context;

import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.feature.test_alarm.daily.DailyAlarmPreference;
import com.example.android.moviecatalogapp.feature.test_alarm.daily.DailyAlarmReceiver;
import com.example.android.moviecatalogapp.feature.test_alarm.upcoming.SchedulerTask;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lenovo on 9/20/2017.
 */


public class MainPresenter implements MvpPresenter<MainView>{

    private final String TAG = getClass().getSimpleName();
    private MainView mainView;

    @Override
    public void onAttach(MainView mvpView){
        mainView = mvpView;
    }

    @Override
    public void onDetach(){
        mainView = null;
    }

    void onLoadData(Context context){
       /* *//*utk settings configuration*//*
        SettingsPreference settingsPreference = new SettingsPreference(context);
        boolean isDailyRemiderNotificationActive = settingsPreference.getDailyReminderActive();
        boolean isUpcomingRemiderNotificationActive = settingsPreference.getUpcomingReminderActive();
*/
        /*utk Daily Reminder*/
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        String time = dailyAlarmPreference.getRepeatingTime();
        if (time == null){
            Date dateDailyReminderDefault = new Date();
            try{
                dateDailyReminderDefault = new SimpleDateFormat("HH:mm", Locale.US)
                        .parse("12:00");
            }catch (ParseException e){
                e.printStackTrace();
            }
            dailyAlarmPreference.setRepeatingTime(
                    new SimpleDateFormat("HH:mm", Locale.US)
                    .format(dateDailyReminderDefault)
            );
            dailyAlarmPreference.setRepeatingMessage(
                    context.getString(R.string.message_daily_reminder)
            );
            time = dailyAlarmPreference.getRepeatingTime();
        }

            DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
            dailyAlarmReceiver.setRepeatingAlarm(
                    context,
                    time,
                    dailyAlarmPreference.getRepeatingMessage(),
                    true
            );

        /*utk Upcoming Movies*/
            SchedulerTask schedulerTask = new SchedulerTask(context);
            schedulerTask.createPeriodicTask();

        mainView.loadData();
    }
}
