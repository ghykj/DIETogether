package com.cookandroid.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final HealthDBManager healthDBManager = new HealthDBManager(context, "health.db", null, 1);
        //final InfoDBManager infoDBManager = new HealthDBManager(getApplicationContext(), "health.db", null, 1);
        final StepDBManager stepDBManager = new StepDBManager(context, "step.db", null, 1);


        Log.d("알람매니저확인", "여기들어왓나??????????");
        healthDBManager.modify("UPDATE healthTABLE SET checked=0");
        Toast.makeText(context,"하루를 시작합니다.",Toast.LENGTH_LONG).show();


        healthDBManager.close();

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.





        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
