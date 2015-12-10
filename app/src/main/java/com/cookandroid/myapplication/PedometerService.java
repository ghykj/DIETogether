package com.cookandroid.myapplication;

/**
 * Created by LGPC on 2015-12-08.
 */

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;


public class PedometerService extends Service implements SensorEventListener {

    public static int step = 0;

    public int count = step;
    public float calories = 0;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    Calculation cal;
    StepDBManager manager;


    private float x, y, z;
    private static final int SHAKE_THRESHOLD = 800;


    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;


    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        cal = new Calculation();
        final StepDBManager manager = new StepDBManager(getApplicationContext(), "step.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from stepTABLE where date ='"+cal.currentTime()+"'", null);
        if(cursor.getCount()<=0){
            manager.insert("insert into stepTABLE VALUES (null,'"
                    + cal.currentTime() + "','"
                    + step + "','"
                    + calories + "');");
            manager.close();
        }
        Log.d("컬럼수는몇개일까",cursor.getCount()+"");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);

        //db가 존재하는지 확인해서 있으면 삭제하고 다시 만들어버리자





        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        if (sensorManager != null)
            sensorManager.unregisterListener(this);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);


            if (gabOfTime > 100) {
                lastTime = currentTime;

                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];


                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;



                if (speed > SHAKE_THRESHOLD) {

                    final StepDBManager manager = new StepDBManager(getApplicationContext(), "step.db", null, 1);

                    Intent myFilteredResponse = new Intent("com.cookandroid.mayapplication.PedometerService");

                    SQLiteDatabase db = manager.getReadableDatabase();
                    Cursor cursor = db.rawQuery("select * from stepTABLE where date ='"+cal.currentTime()+"'", null);

                    step = count++;
                    calories = (float) cal.caloriesCalculator(step);



                    manager.modify("UPDATE stepTABLE SET step="+step);
                    manager.modify("UPDATE stepTABLE SET cal="+calories);
                    manager.close();

                    Log.d("stepppppppp", step + "");
                    //String msg = step + "" ;



                    SQLiteDatabase db = manager.getReadableDatabase();
                    Cursor cursor = db.rawQuery("select * from stepTABLE where date ='"+cal.currentTime()+"'", null);

                    cursor.moveToFirst();
                    String msg = cursor.getInt(2) + "";
                    String calStr = String.format("%.2f",cursor.getFloat(3));
                    myFilteredResponse.putExtra("step", msg);
                    //String calStr =String.format("%.2f", calories);
                    msg = calStr+" kcal";
                    myFilteredResponse.putExtra("calories",msg);

                    sendBroadcast(myFilteredResponse);
                    db.close();

                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }

    }

}
