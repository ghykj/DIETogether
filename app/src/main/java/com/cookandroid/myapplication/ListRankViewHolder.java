package com.cookandroid.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by LGPC on 2015-12-04.
 */
public class ListRankViewHolder extends RecyclerView.ViewHolder {

    protected ImageView bottomLine;
    protected TextView userName, rank, walking, calories;
    //protected CheckBox checkBox;
    //protected Button delete;
    protected RelativeLayout layout;
    //protected int checked;

    public ListRankViewHolder(View view){
        super(view);
        bottomLine = (ImageView)view.findViewById(R.id.imageView4);
        userName = (TextView)view.findViewById(R.id.userName);
        walking = (TextView)view.findViewById(R.id.walking);
        calories = (TextView)view.findViewById(R.id.calories);
        rank = (TextView)view.findViewById(R.id.rank);
        layout = (RelativeLayout)view.findViewById(R.id.relative);
        view.setClickable(true);
    }


}
