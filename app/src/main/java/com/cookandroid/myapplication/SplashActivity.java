package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;


/**
 * Created by LGPC on 2015-12-01.
 */
public class SplashActivity extends Activity {


    NetworkManager networkManager;
    //Alarm alarm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // alarm = new Alarm();
        //alarm.registerAlarm(getApplicationContext());
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler() , 3000); // 3초 후에 hd Handler 실행
    }

    private class splashhandler implements Runnable{
        InfoDBManager infoDBmanager;
        public void run() {

            infoDBmanager  = new InfoDBManager(getApplicationContext(), "Info.db", null, 1);
            SQLiteDatabase db=infoDBmanager.getReadableDatabase();

            String query = "select * from infoTABLE";
            Cursor result = db.rawQuery(query, null);
            Log.d("ㅎㅎㅎㅎ??", infoDBmanager.PrintData() + "좀되라 ㅎㅎ");
            if(result.getCount()>0){
                startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
                SplashActivity.this.finish();
                new AsyncTask<String, String, Integer>() {
                    @Override
                    protected Integer doInBackground(String... params) {

                        return NetworkManager.commitPedometer(getApplicationContext());
                    }

                    //메인쓰레드로
                    @Override
                    protected void onPostExecute(Integer aBoolean) {
                    }
                }.execute("");
                // 로딩페이지 Activity Stack에서 제거
            }
            else{
                startActivity(new Intent(getApplication(), SignActivity.class)); // 로딩이 끝난후 이동할 Activity
                SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
            }

        }
    }

}
