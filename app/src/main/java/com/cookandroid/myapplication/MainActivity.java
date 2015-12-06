package com.cookandroid.myapplication;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends ActionBarActivity {

    private TabHost tabHost;
    private Toolbar toolbar;
    private ImageView skinny, standard, overweight, obesity;
    //private ActionMenuView amvMenu;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skinny = (ImageView)findViewById(R.id.skinny);
        standard = (ImageView)findViewById(R.id.standard);
        overweight = (ImageView)findViewById(R.id.overweight);
        obesity = (ImageView)findViewById(R.id.obesity);


        final InfoDBManager manager = new InfoDBManager(getApplicationContext(), "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select bmi_decision from infoTABLE", null);

        cursor.moveToFirst();

        if(cursor.getString(0).equals("저체중")){
            skinny.setVisibility(View.VISIBLE);
        }
        else if(cursor.getString(0).equals("정상")){
            standard.setVisibility(View.VISIBLE);
        }
        else if(cursor.getString(0).equals("과체중")){
            overweight.setVisibility(View.VISIBLE);

        }
        else if(cursor.getString(0).equals("비만")){
            obesity.setVisibility(View.VISIBLE);
        }




        toolbar = (Toolbar) findViewById(R.id.toolbar);// Attaching the layout to the toolbar object
       //amvMenu = (ActionMenuView) toolbar.findViewById(R.id.amvMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_main, amvMenu.getMenu());
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
                return true;
            case R.id.action_info:
                Log.d("셀렉트", "클릭");
                Intent intent2 = new Intent(this, InfoActivity.class);
                Log.d("셀렉트", "클릭");
                startActivity(intent2);
                this.finish();
                return true;
            case R.id.action_rank:
                Intent intent3 = new Intent(this, RankActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_walk:
                Intent intent4 = new Intent(this, WalkActivity.class);
                startActivity(intent4);
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
