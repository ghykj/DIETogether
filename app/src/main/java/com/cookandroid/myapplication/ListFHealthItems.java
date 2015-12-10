package com.cookandroid.myapplication;

/**
 * Created by LGPC on 2015-12-11.
 */
public class ListFHealthItems {

    private String healthName;
    private int healthNum;
    private int id;

    public int getId(){return this.id;}
    //public int getChecked(){return this.checked;}
    public String getHealthName(){
        return healthName;
    }
    public int getHealthNum(){
        return healthNum;
    }
    //public String getDate() {return date;}
    //public void setChecked(int checked){
       // this.checked=checked;
   // }

    public ListFHealthItems(int id, String healthName, int healthNum){
        this.id=id;
        //this.date=date;
        //this.checked=checked;
        this.healthName=healthName;
        this.healthNum=healthNum;
    }
}
