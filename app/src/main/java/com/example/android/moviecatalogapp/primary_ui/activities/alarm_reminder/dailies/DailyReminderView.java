package com.example.android.moviecatalogapp.primary_ui.activities.alarm_reminder.dailies;

import com.example.android.moviecatalogapp.primary_ui.base.MvpView;


interface DailyReminderView extends MvpView{
    void save();

    void loadData(String time);
}
