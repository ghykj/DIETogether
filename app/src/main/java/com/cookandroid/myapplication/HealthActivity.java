package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGPC on 2015-11-29.
 */
public class HealthActivity extends ActionBarActivity{
    private TabHost tabHost;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private ActionMenuView amvMenu;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        toolbar = (Toolbar) findViewById(R.id.include);// Attaching the layout to the toolbar object
        //amvMenu = (ActionMenuView) toolbar.findViewById(R.id.amvMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(getApplicationContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<ListItems> items=new ArrayList<>();

        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(),items,R.layout.activity_health));


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
                this.finish();
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