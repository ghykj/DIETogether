package com.cookandroid.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LGPC on 2015-12-04.
 */
public class HRegisterActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private Button button;
    private EditText healthName,healthNum;
    private TextView textView, textView2,textView3;
    //private ActionMenuView amvMenu;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final HealthDBManager manager = new HealthDBManager(getApplicationContext(), "health.db", null, 1);
        toolbar = (Toolbar) findViewById(R.id.include);
        button = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView17);
        textView2 = (TextView) findViewById(R.id.textView18);
        textView3 = (TextView) findViewById(R.id.textView19);
        healthName = (EditText) findViewById(R.id.editText5);
        healthNum = (EditText) findViewById(R.id.editText6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!healthName.getText().toString().equals("")) {
                    if(healthNum.getText().toString().equals("")){
                        manager.insert("insert into healthTABLE VALUES (null,'"
                                +0+"','"
                                + healthName.getText().toString() + "','"
                                +0+ "');");
                        manager.close();
                        Intent intent3 = new Intent(getApplicationContext(), HealthActivity.class);
                        startActivity(intent3);
                        finish();
                    }
                    else {
                        manager.insert("insert into healthTABLE VALUES (null,'"
                                + 0 + "','"
                                + healthName.getText().toString() + "','"
                                + Integer.parseInt(healthNum.getText().toString()) + "');");
                        manager.close();
                        Intent intent3 = new Intent(getApplicationContext(), HealthActivity.class);
                        startActivity(intent3);
                        finish();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "운동 이름을 적어주세요!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }});
        // Attaching the layout to the toolbar object
        //amvMenu = (ActionMenuView) toolbar.findViewById(R.id.amvMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.back);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case android.R.id.home:
                Intent intent5 = new Intent (this,HealthActivity.class);
                startActivity(intent5);
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
