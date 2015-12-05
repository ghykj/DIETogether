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

    public int getId(){return this.id;}
    public int getChecked(){return this.checked;}
    public String getHealthName(){
        return healthName;
    }
    public int getHealthNum(){
        return healthNum;
    }

    public ListItems(int id, int checked, String healthName, int healthNum){
        this.id=id;
        this.checked=checked;
        this.healthName=healthName;
        this.healthNum=healthNum;
    }




}