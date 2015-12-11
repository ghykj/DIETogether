package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LGPC on 2015-11-29.
 */
public class RankActivity extends ActionBarActivity {
    private TabHost tabHost;
    private Toolbar toolbar;
    //private ActionMenuView amvMenu;
    Context context;
    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;
    RecyclerRankViewAdapter adapter;
    List<ListRankItems> items = new ArrayList<>();
    Calculation cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        toolbar = (Toolbar) findViewById(R.id.include);// Attaching the layout to the toolbar object
        //amvMenu = (ActionMenuView) toolbar.findViewById(R.id.amvMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.back);

        cal = new Calculation();
        recyclerView=(RecyclerView)findViewById(R.id.view2);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        button = (Button)findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), FRegisterActivity.class);
                startActivity(intent3);
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        new AsyncTask<String, String, ArrayList<ListRankItems>>() {
            @Override
            protected ArrayList<ListRankItems> doInBackground(String... params) {

                return NetworkManager.getFreindData(getApplicationContext());
            }

            //메인쓰레드로
            @Override
            protected void onPostExecute(ArrayList<ListRankItems> aBoolean) {

               // final InfoDBManager manager = new InfoDBManager(getApplicationContext(), "Info.db", null, 1);
                final StepDBManager manager = new StepDBManager(getApplicationContext(), "step.db", null, 1);
                SQLiteDatabase db = manager.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from stepTABLE where date ='" + cal.currentTime() + "'", null);
                cursor.moveToFirst();



                items.addAll(aBoolean);
                items.add(new ListRankItems("나", 0, null, cursor.getInt(2), cursor.getFloat(3)));
                Collections.sort(items);
                adapter.notifyDataSetChanged();
                for(int i = 0 ; i < items.size(); i++){
                    Log.d("랭크에 잘 들어오니",""+items.get(i));
                }
            }
        }.execute("");

        adapter = new RecyclerRankViewAdapter(this, items, R.layout.activity_rank);
        recyclerView.setAdapter(adapter);


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
            //

            /*case android.R.id.home:
                Intent intent5 = new Intent (this, MainActivity.class);
                startActivity(intent5);
                return true;*/

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