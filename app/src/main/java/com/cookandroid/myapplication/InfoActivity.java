package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LGPC on 2015-12-02.
 */
public class InfoActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView textView,textView2,textView3,textView4,textView5,textView6,textView7, textView8, textView9, textView10, textView11, id, bmi, bmi2;
    private EditText name, age, height, weight;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton male, female;
    private float bmiNum=0;
    private String heightStr, weightStr;
    Calculation cal;
    InfoDBManager manager;

    String dbID;
    String dbName;
    int dbAge;
    int dbGender;
    float dbHeight;
    float dbWeight;
    float dbBMI;
    String dbDecision;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        cal = new Calculation();

        id = (TextView)findViewById(R.id.textView12);
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
        male = (RadioButton)findViewById(R.id.radioButton);
        female = (RadioButton)findViewById(R.id.radioButton2);

        final InfoDBManager manager = new InfoDBManager(getApplicationContext(), "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from infoTABLE", null);

        cursor.moveToFirst();
        id.setText(cursor.getString(1));
        name.setText(cursor.getString(2));


        Log.d("gender",manager.PrintData()+"");
        if(cursor.getInt(3)==1) female.setChecked(true);
        else if(cursor.getInt(3)==0) male.setChecked(true);

        age.setText(String.valueOf(cursor.getInt(4)));

        height.setText(String.valueOf(cursor.getFloat(5)));
        weight.setText(String.valueOf(cursor.getFloat(6)));
        bmi.setText(String.format("%.2f",cursor.getFloat(7)));
        bmi2.setText(cursor.getString(8));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id.getText().toString().equals("") || name.getText().toString().equals("")
                        || age.getText().toString().equals("") || height.getText().toString().equals("") || weight.getText().toString().equals("")
                        || (female.isChecked()==false && male.isChecked()==false)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "빠진 부분이 있나 확인 해 주세요!", Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    dbID = id.getText().toString();
                    dbName = name.getText().toString();
                    dbAge = Integer.parseInt(age.getText().toString());
                    dbHeight = Float.parseFloat(height.getText().toString());
                    dbWeight = Float.parseFloat(weight.getText().toString());

                    if (female.isChecked()) dbGender = 1;
                    else if (male.isChecked()) dbGender = 0;

                    dbBMI = cal.bmiCalculator(dbHeight, dbWeight);
                    dbDecision = cal.bmiDecision(dbBMI);
                    bmi.setText(String.format("%.2f",dbBMI));
                    bmi2.setText(dbDecision);

                    manager.modify("UPDATE infoTABLE SET name='" + dbName + "'");
                    manager.modify("UPDATE infoTABLE SET gender="+dbGender);
                    manager.modify("UPDATE infoTABLE SET age ="+dbAge);
                    manager.modify("UPDATE infoTABLE SET height ="+dbHeight);
                    manager.modify("UPDATE infoTABLE SET weight ="+dbWeight);
                    manager.modify("UPDATE infoTABLE SET bmi = "+dbBMI);
                    manager.modify("UPDATE infoTABLE SET bmi_decision='"+dbDecision+"'");
                    manager.close();
                    Log.d("싸인", manager.PrintData() + "좀되라 ㅎㅎ");
                    Toast toast = Toast.makeText(getApplicationContext(), "정보가 수정되었습니다!", Toast.LENGTH_SHORT);
                    toast.show();
                }
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
