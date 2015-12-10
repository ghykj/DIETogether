package com.cookandroid.myapplication;

/**
 * Created by LGPC on 2015-12-04.
 */
public class ListRankItems {

    private String userName;
    private int rank;
    private int id;
    private int walking;
    private float calories;
    //private int checked;
    //private String date;

    public int getId(){return this.id;}
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

    public ListRankItems(int id, int rank, String userName, int walking, float calories){
        this.id=id;
        this.userName=userName;
        this.walking=walking;
        this.calories=calories;
        this.rank = rank;
    }




}