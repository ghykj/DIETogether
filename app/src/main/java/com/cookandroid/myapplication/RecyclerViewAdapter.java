package com.cookandroid.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by LGPC on 2015-12-04.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ListLowViewHolder>{

    private Context context;
    private List<ListItems> items;
    int itemLayout;
    HealthDBManager healthDBmanager;
    NetworkManager networkManager;

    public RecyclerViewAdapter(Context context, List<ListItems>  items, int itemLayout){
        this.context = context;
        this.items = items;
        this.itemLayout=itemLayout;
    }
    @Override
    public ListLowViewHolder onCreateViewHolder(final ViewGroup viewGroup,int viewType){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_low,null);
        return new ListLowViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(final ListLowViewHolder listLowViewHolder, int position){


        final ListItems item = items.get(position);

        listLowViewHolder.healthName.setText(item.getHealthName());

        if(item.getHealthNum()==0){
            listLowViewHolder.healthNum.setText("");
        }
        else listLowViewHolder.healthNum.setText("" + item.getHealthNum() + "회");

        final HealthActivity healthActivity = (HealthActivity) HealthActivity.healthActivity;
        healthDBmanager  = new HealthDBManager(healthActivity, "health.db", null, 1);
        SQLiteDatabase db = healthDBmanager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from healthTABLE", null);
//        Log.d("커서",healthDBmanager.PrintData()+"");
        Log.d("밑에",item.getChecked()+"");
//        while(cursor.moveToNext()){
//            if(cursor.getInt(1)==1) {
//                listLowViewHolder.checkBox.setChecked(true);
//            }
//            else if(cursor.getInt(1)==0) listLowViewHolder.checkBox.setChecked(false);
//        }

        listLowViewHolder.checkBox.setChecked(item.getChecked()==1);


        listLowViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                healthDBmanager = new HealthDBManager(v.getContext(), "health.db", null, 1);
                SQLiteDatabase healthDB = healthDBmanager.getReadableDatabase();
                healthDBmanager.delete("delete from healthTable where _id = " + item.getId() + ";");


                HealthActivity healthActivity = (HealthActivity) HealthActivity.healthActivity;
                Intent intent = new Intent(context, HealthActivity.class);
                v.getContext().startActivity(intent);
                healthActivity.finish();

            }
        });
        listLowViewHolder.checkBox.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (listLowViewHolder.checkBox.isChecked()) {

                    final HealthActivity healthActivity = (HealthActivity) HealthActivity.healthActivity;
                    AlertDialog.Builder builder = new AlertDialog.Builder(healthActivity);     // 여기서 this는 Activity의 this

                    builder.setTitle("운동확인메시지")        // 제목 설정
                            .setMessage("정말로 운동 하셨나요?")        // 메세지 설정
                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    healthDBmanager = new HealthDBManager(healthActivity, "health.db", null, 1);
                                    //SQLiteDatabase healthDB = healthDBmanager.getReadableDatabase();
                                    healthDBmanager.modify("UPDATE healthTABLE SET checked = 1 WHERE _id =" + item.getId() + ";");


                                    new AsyncTask<String, String, Integer>() {
                                        @Override
                                        protected Integer doInBackground(String... params) {

                                            return networkManager.commitHealth(context, item.getHealthName(), item.getHealthNum());
                                        }

                                        //메인쓰레드로
                                        @Override
                                        protected void onPostExecute(Integer aBoolean) {
                                        }
                                    }.execute("");



                                    Log.d("체크박스", healthDBmanager.PrintData());
                                    healthDBmanager.close();

                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {


                                    dialog.cancel();
                                    listLowViewHolder.checkBox.setChecked(item.getChecked()==1);
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();
                }
                else if(!listLowViewHolder.checkBox.isChecked()){
                    final HealthActivity healthActivity = (HealthActivity) HealthActivity.healthActivity;
                    AlertDialog.Builder builder = new AlertDialog.Builder(healthActivity);     // 여기서 this는 Activity의 this

                    builder.setTitle("운동취소메시지")        // 제목 설정
                            .setMessage("운동을 취소하시겠어요?")        // 메세지 설정
                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    healthDBmanager = new HealthDBManager(healthActivity, "health.db", null, 1);
                                    //SQLiteDatabase healthDB = healthDBmanager.getReadableDatabase();
                                    healthDBmanager.modify("UPDATE healthTABLE SET checked = 0 WHERE _id =" + item.getId() + ";");
                                    new AsyncTask<String, String, Integer>() {
                                        @Override
                                        protected Integer doInBackground(String... params) {

                                            return networkManager.deleteHealth(context,item.getHealthName(),item.getHealthNum());
                                        }

                                        //메인쓰레드로
                                        @Override
                                        protected void onPostExecute(Integer aBoolean) {
                                        }
                                    }.execute("");

                                    Log.d("d", healthDBmanager.PrintData());
                                    healthDBmanager.close();

                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    listLowViewHolder.checkBox.setChecked(true
                                    );
                                    dialog.cancel();

                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();
                }

            }
        });


    }
 /* public void checkDialog(){
      HealthActivity healthActivity = (HealthActivity) HealthActivity.healthActivity;
                    AlertDialog.Builder builder = new AlertDialog.Builder(healthActivity);     // 여기서 this는 Activity의 this

                    builder.setTitle("운동확인메시지")        // 제목 설정
                            .setMessage("정말로 운동 하셨나요?")        // 메세지 설정
                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                            .setPositiveButton("네", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){


                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();
    }*/


}
