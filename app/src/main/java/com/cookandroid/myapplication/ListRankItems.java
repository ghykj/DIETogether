package com.cookandroid.myapplication;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by LGPC on 2015-12-04.
 */
public class ListRankItems implements Comparable {
    private String userID;
    private String userName;
    private int rank;
    //private int id;
    private int walking;
    private float calories;
    //private int checked;
    //private String date;


    public String getUserId(){return this.userID;}
    public int getRank(){return this.rank;}
    public String getUserName(){
        return this.userName;
    }
    public int getWalking(){
        return this.walking;
    }
    public float getCalories(){
        return this.calories;
    }
    //public String get() {return date;}
    //public void setChecked(int checked){
    //    this.checked=checked;
    //}

    public ListRankItems(String userID, int rank, String userName, int walking, float calories){
        this.userID=userID;
        this.userName=userName;
        this.walking=walking;
        this.calories=calories;
        this.rank = rank;
    }


    @Override
    public int compareTo(Object another) {
        if(this.getWalking()>((ListRankItems)another).getWalking())return -1;
        else return 1;
    }


}