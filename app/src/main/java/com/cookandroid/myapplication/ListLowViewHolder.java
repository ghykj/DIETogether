package com.cookandroid.myapplication;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by LGPC on 2015-12-04.
 */
public class ListLowViewHolder extends RecyclerView.ViewHolder {

    protected ImageView topLine,bottomLine;
    protected TextView healthName, healthNum;
    protected CheckBox checkBox;
    protected Button delete;
    protected RelativeLayout layout;
    protected int checked;

    public ListLowViewHolder(View view){
        super(view);
        topLine = (ImageView)view.findViewById(R.id.imageView3);
        bottomLine = (ImageView)view.findViewById(R.id.imageView4);
        checkBox = (CheckBox)view.findViewById(R.id.checkBox);
        healthName = (TextView)view.findViewById(R.id.textView14);
        healthNum = (TextView)view.findViewById(R.id.textView15);
        layout = (RelativeLayout)view.findViewById(R.id.relative);
        delete = (Button)view.findViewById(R.id.button3);
        checked = 0;
        view.setClickable(true);
    }


}
