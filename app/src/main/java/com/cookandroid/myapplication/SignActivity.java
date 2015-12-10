package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by LGPC on 2015-12-05.
 */
public class SignActivity extends Activity {

    private EditText id, name, age, height, weight;
    private TextView bmi,bmi2;
    private RadioGroup radioGroup;
    private RadioButton female, male;
    private Button button;

    String dbID;
    String dbName;
    int dbAge;
    int dbGender;
    float dbHeight;
    float dbWeight;
    float dbBMI;
    String dbDecision;
    String heightStr;
    String weightStr;

    Calculation cal;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        cal = new Calculation();
        bmi = (TextView)findViewById(R.id.signBMI);
        bmi2 = (TextView)findViewById(R.id.signBMI2);
        id = (EditText)findViewById(R.id.signID);
        name = (EditText)findViewById(R.id.signName);
        age = (EditText)findViewById(R.id.signAge);
        height = (EditText)findViewById(R.id.signHeight);
        weight = (EditText)findViewById(R.id.signWeight);
        female = (RadioButton)findViewById(R.id.signFemale);
        male = (RadioButton)findViewById(R.id.signMale);
        radioGroup = (RadioGroup)findViewById(R.id.signRadioGroup);
        button = (Button)findViewById(R.id.signButton);

        final InfoDBManager manager = new InfoDBManager(getApplicationContext(), "Info.db", null, 1);







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //정보받아오기


                //String bmiStr = String.format("%.2f", bmiNum);

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

                    JSONObject jsonObject = new JSONObject();
                    try{
                        jsonObject.put("id",dbID);
                        jsonObject.put("name",URLEncoder.encode(dbName, "UTF-8"));
                        if(dbGender==1) jsonObject.put("gender",URLEncoder.encode("여성", "UTF-8"));
                        else jsonObject.put("gender", URLEncoder.encode("남성", "UTF-8"));
                        jsonObject.put("age",dbAge);
                        jsonObject.put("height",(int)dbHeight);
                        jsonObject.put("weight",(int)dbWeight);
                    }
                    catch (Exception e){

                    }
                     new AsyncTask<JSONObject, String, Integer>() {
                        @Override
                        protected Integer doInBackground(JSONObject... params) {

                            return NetworkManager.Join(getApplicationContext(),params[0]);
                        }

                         //메인쓰레드로
                         @Override
                         protected void onPostExecute(Integer aBoolean) {
                             super.onPostExecute(aBoolean);
                             Log.d("불리언값",""+aBoolean);
                             if (aBoolean==0||aBoolean==1){
                                 manager.insert("insert into infoTABLE VALUES (null,'"
                                         + dbID + "','"
                                         + dbName + "',"
                                         + dbGender + ","
                                         + dbAge + ","
                                         + dbHeight + ","
                                         + dbWeight + ","
                                         + dbBMI + ",'"
                                         + dbDecision + "');");
                                 manager.close();
                                 Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                                 startActivity(intent3);
                                 finish();
                             }else if(aBoolean==2){

                                 Toast.makeText(getApplicationContext(), "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                             }
                         }
                     }.execute(jsonObject);


                    Log.d("싸인", manager.PrintData() + "좀되라 ㅎㅎ");
                }
            }
        });




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

            /*case android.R.id.home:
                Intent intent5 = new Intent (this, MainActivity.class);
                startActivity(intent5);
                return true;

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
                return true;*/

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
