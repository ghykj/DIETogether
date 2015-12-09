package com.cookandroid.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import java.util.Calendar;

/**
 * Created by LGPC on 2015-12-09.
 */
public class Alarm {

    private static int ONE_DAY = 24*60*60*1000;

    long millisecond;

    public Alarm(){

    }
    public void registerAlarm(Context context){
        Intent intent = new Intent(context, MyReceiver.class);
        //intent.setAction(MyReceiver.INNER_PUSH);
        PendingIntent sender
                = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager manager
                =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar current = Calendar.getInstance();
        Calendar midnight = Calendar.getInstance();
        midnight.set(current.YEAR,current.MONTH,current.DAY_OF_MONTH,23,59,59);
        millisecond = midnight.getTimeInMillis() - current.getTimeInMillis();
        manager.setRepeating(AlarmManager.RTC,System.currentTimeMillis()+millisecond,ONE_DAY,sender);
    }
}
