package com.cookandroid.myapplication;

import android.content.Context;

/**
 * Created by LGPC on 2015-12-04.
 */
public class ListItems {

    private String healthName;
    private int healthNum;
    private int id;
    private int checked;
    private String date;

    public int getId(){return this.id;}
    public int getChecked(){return this.checked;}
    public String getHealthName(){
        return healthName;
    }
    public int getHealthNum(){
        return healthNum;
    }
    public String getDate() {return date;}
    public void setChecked(int checked){
        this.checked=checked;
    }

    public ListItems(int id, String date, int checked, String healthName, int healthNum){
        this.id=id;
        this.date=date;
        this.checked=checked;
        this.healthName=healthName;
        this.healthNum=healthNum;
    }




}