package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by LGPC on 2015-12-02.
 */
public class InfoActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView textView,textView2,textView3,textView4,textView5,textView6,textView7, textView8, textView9, textView10, textView11, bmi, bmi2;
    private EditText name, age, height, weight;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton radioButton, radioButton2;
    private float bmiNum=0;
    private String heightStr, weightStr;
    Calculation cal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        cal = new Calculation();

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView7 = (TextView)findViewById(R.id.textView7);
        textView8 = (TextView)findViewById(R.id.textView8);
        textView9 = (TextView)findViewById(R.id.textView9);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);
        bmi = (TextView)findViewById(R.id.bmi);
        bmi2 = (TextView)findViewById(R.id.bmi2);
        name = (EditText)findViewById(R.id.editText);
        age = (EditText)findViewById(R.id.editText2);
        height = (EditText)findViewById(R.id.editText3);
        weight = (EditText)findViewById(R.id.editText4);

        button = (Button)findViewById(R.id.button);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButton = (RadioButton)findViewById(R.id.radioButton);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightStr=height.getText().toString();
                weightStr=weight.getText().toString();
                if(heightStr.equals("")) heightStr="0";
                if(weightStr.equals("")) weightStr="0";

                float bmiNum = cal.bmiCalculator(Float.parseFloat(heightStr), Float.parseFloat(weightStr));

                String bmiStr = String.format("%.2f", bmiNum);
                bmi.setText(bmiStr);
                bmi2.setText(cal.bmiDecision(bmiNum));
            }
        });

        toolbar = (Toolbar) findViewById(R.id.include);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);

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

                return true;
            case R.id.action_rank:
                Intent intent2 = new Intent(this, RankActivity.class);
                startActivity(intent2);
                this.finish();
                return true;
            case R.id.action_walk:
                Intent intent3 = new Intent(this, WalkActivity.class);
                startActivity(intent3);
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
