package com.cookandroid.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LGPC on 2015-12-04.
 */
public class RecyclerRankViewAdapter extends RecyclerView.Adapter<ListRankViewHolder>{

    private Context context;
    private List<ListRankItems> items;


    int itemLayout;
    //HealthDBManager healthDBmanager;

    public RecyclerRankViewAdapter(Context context, List<ListRankItems> items, int itemLayout){
        this.context = context;
        this.items = items;
        this.itemLayout=itemLayout;
    }
    @Override
    public ListRankViewHolder onCreateViewHolder(final ViewGroup viewGroup,int viewType){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_rank,null);
        return new ListRankViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(final ListRankViewHolder listRankViewHolder, int position){


        final ListRankItems item = items.get(position);

        final FriendHealthActivity friendHealthActivity = (FriendHealthActivity) FriendHealthActivity.friendHealthActivity;

        listRankViewHolder.userName.setText(item.getUserId());
        listRankViewHolder.calories.setText(String.format("%.2f", item.getCalories())+"kcal");
        listRankViewHolder.rank.setText(position+1+"위  ");
        listRankViewHolder.walking.setText(String.valueOf(item.getWalking())+"걸음");


        //final HealthActivity healthActivity = (HealthActivity) HealthActivity.healthActivity;
        //healthDBmanager  = new HealthDBManager(healthActivity, "health.db", null, 1);
        //SQLiteDatabase db = healthDBmanager.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select * from healthTABLE", null);


        listRankViewHolder.userName.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //FriendHealthActivity friendHealthActivity = (FriendHealthActivity) FriendHealthActivity.friendHealthActivity;
                Intent intent = new Intent(v.getContext(), FriendHealthActivity.class);
                String friendID=item.getUserId();
                Log.d("friendID인텐트전",""+friendID);
                intent.putExtra("friendID", friendID);
                Log.d("friendIDdlsx엘",""+friendID);

                v.getContext().startActivity(intent);
                //((Activity)context).finish();
            }
        });
        /*listRankViewHolder.checkBox.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (listRankViewHolder.checkBox.isChecked()) {

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
                                    Log.d("체크박스", healthDBmanager.PrintData());
                                    healthDBmanager.close();

                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                    listRankViewHolder.checkBox.setChecked(item.getChecked()==1);
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();
                }
                else if(!listRankViewHolder.checkBox.isChecked()){
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
                                    Log.d("d", healthDBmanager.PrintData());
                                    healthDBmanager.close();

                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    listRankViewHolder.checkBox.setChecked(true
                                    );
                                    dialog.cancel();

                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();
                }

            }
        });*/


    }
}
