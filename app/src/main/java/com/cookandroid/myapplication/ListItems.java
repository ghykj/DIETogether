package com.cookandroid.myapplication;

import android.content.Context;

/**
 * Created by LGPC on 2015-12-04.
 */
public class ListItems {

    private String healthName;
    private String healthNum;

    private boolean check;

    public String getHealthName(){
        return healthName;
    }
    public String getHealthNum(){
        return healthNum;
    }
    public void setHealthName(String healthName){
        this.healthName=healthName;
    }
    public void setHealthNum(String healthNum){
        this.healthNum=healthNum;
    }
    public boolean isCheck(){
        return false;
    }




}