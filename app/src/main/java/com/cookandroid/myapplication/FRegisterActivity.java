package com.cookandroid.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LGPC on 2015-12-04.
 */
public class FRegisterActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private Button button;
    private EditText friendID, friendName;
    private TextView textView, textView2,textView3;
    //private ActionMenuView amvMenu;
    Context context;
    Calculation cal;

    String IdStr, NameStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fregister);

        toolbar = (Toolbar) findViewById(R.id.include);
        button = (Button) findViewById(R.id.button4);
        //textView = (TextView) findViewById(R.id.textView17);
        //textView2 = (TextView) findViewById(R.id.textView18);
        //textView3 = (TextView) findViewById(R.id.textView19);
        friendID = (EditText) findViewById(R.id.friendID);
        friendName = (EditText) findViewById(R.id.friendName);
        //cal = new Calculation();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IdStr = friendID.getText().toString();
                NameStr = friendName.getText().toString();
                new AsyncTask<String, String, Integer>() {
                    @Override
                    protected Integer doInBackground(String... params) {

                        return NetworkManager.addFriendhd(getApplicationContext(),IdStr,NameStr);
                    }

                    //메인쓰레드로
                    @Override
                    protected void onPostExecute(Integer aBoolean) {
                    }
                }.execute("");

                /*AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());     // 여기서 this는 Activity의 this

                builder.setTitle("알림")        // 제목 설정
                        .setMessage("친구등록이 완료됐어요! 친구도 나를 등록하면 친구정보를 볼 수 있습니다.")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                Intent intent3 = new Intent(getApplicationContext(), RankActivity.class);
                                startActivity(intent3);
                                dialog.cancel();
                                finish();
                            }
                        });

                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();*/
                Toast toast = Toast.makeText(getApplicationContext(), "친구등록이 완료됐어요! 친구도 나를 등록하면 볼 수 있습니다.", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent3 = new Intent(getApplicationContext(), RankActivity.class);
                startActivity(intent3);
                //dialog.cancel();
                finish();

            }
        });

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
                Intent intent5 = new Intent (this,RankActivity.class);
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
