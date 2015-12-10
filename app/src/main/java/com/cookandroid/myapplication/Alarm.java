package com.cookandroid.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LGPC on 2015-12-09.
 */
public class Alarm {

    private static int ONE_DAY = 24*60*60*1000;

    long millisecond;
    Calculation cal;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);

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
        midnight.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), current.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
//        sdf.setCalendar(midnight);
        Log.d("시간을알아보자", "" + sdf.format(new Date(midnight.getTimeInMillis())));
        millisecond = (midnight.getTimeInMillis()+1000) - current.getTimeInMillis();
        millisecond = (midnight.getTimeInMillis()) ;
        manager.setRepeating(AlarmManager.RTC,millisecond,ONE_DAY,sender);
    }
}

