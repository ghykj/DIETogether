package com.cookandroid.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LGPC on 2015-11-21.
 */
public class WalkActivity extends ActionBarActivity{
    private Toolbar toolbar;
    private GraphicsView view;
    private ImageView imageView2;
    private Button button;
    private Intent intentMyService;
    private BroadcastReceiver receiver;
    private boolean flag = true;
    private String step;
    private String calories;
    TextView countText;
    TextView calText;
    Calculation cal;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        cal = new Calculation();
        intentMyService = new Intent(this, PedometerService.class);
        receiver = new MyMainLocalRecever();
        //view = (GraphicsView) findViewById(R.id.view);
        imageView2 = (ImageView) findViewById(R.id.imageView5);
        countText = (TextView) findViewById(R.id.countText);
        calText = (TextView) findViewById(R.id.calText);


        toolbar = (Toolbar) findViewById(R.id.include);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);q
        // getSupportActionBar().setHomeButtonEnabled(true);

        //����Ǳ� ���ϴ� ���� ���


        final StepDBManager manager = new StepDBManager(getApplicationContext(), "step.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from stepTABLE where date ='" + cal.currentTime() + "'", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            countText.setText((cursor.getString(2)).toString());
            calText.setText(String.format("%.2f", cursor.getFloat(3))+" kcal");
        }
        //db.close();



//        CountText = (TextView)findViewById(R.id.TextView01);

        button = (Button) findViewById(R.id.pedometerButton);



        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (flag) {



                    button.setText("만보기멈추기");
                    IntentFilter mainFilter = new IntentFilter("com.cookandroid.mayapplication.PedometerService");
                    registerReceiver(receiver, mainFilter);
                    startService(intentMyService);
                    //view.turnOnPedometer();

                    // TODO Auto-generated method stub
                    try {


                        //txtMsg.setText("After stoping Service:\n"+service.getClassName());
                        //Toast.makeText(getApplicationContext(), "���� ����", 1).show();
                    } catch (Exception e) {
                        // TODO: handle exception
                        //Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
                    }
                }
                else {

                    button.setText("만보기시작");

                    //view.turnOffPedometer();

                    // TODO Auto-generated method stub
                    try {

                        unregisterReceiver(receiver);

                        stopService(intentMyService);

                        //Toast.makeText(getApplicationContext(), "���� ����", 1).show();
                        //txtMsg.setText("After stoping Service:\n"+service.getClassName());
                    } catch (Exception e) {
                        // TODO: handle exception
                        //Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
                    }
                }

                flag = !flag;

            }
        });
    }
  /*  public String getCount(){
        return count;
    }*/

    class MyMainLocalRecever extends BroadcastReceiver {
        String count;

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            step = intent.getStringExtra("step");
            calories = intent.getStringExtra("calories");
            Log.d("serviceData", "과연.." + step);
            //count = serviceData;
            countText.setText(step);
            calText.setText(calories);
            //Toast.makeText(getApplicationContext(), "Walking . . . ", 1).show();
        }
        public String getCount(){
            return count;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // R.id.action_settings:
            // User chose the "Settings" item, show the app settings UI...
            //return true;

            case R.id.action_health:
                Intent intent = new Intent(this, HealthActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            case R.id.action_info:
                Intent intent2 = new Intent(this, InfoActivity.class);
                startActivity(intent2);
                this.finish();
                return true;
            case R.id.action_rank:
                Intent intent3 = new Intent(this, RankActivity.class);
                startActivity(intent3);
                this.finish();
                return true;
            case R.id.action_walk:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}

