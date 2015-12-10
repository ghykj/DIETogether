package com.cookandroid.myapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LGPC on 2015-12-03.
 */
public class Calculation {

    public float bmiCalculator(float height, float weight){

        if(height==0 || weight==0) return 0;

        height = height/100;
        float bmi = weight/(height*height);
        return bmi;
    }

    public String bmiDecision(float bmi) {
        if(bmi<20) return "저체중";
        else if(bmi<25) return "정상";
        else if(bmi<30) return "과체중";
        else return "비만";
    }

    public float caloriesCalculator(int step){
        double calories = step *0.044*0.001;

        return (float)calories;

    }
    public String currentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(new Date(calendar.getTimeInMillis()));
    }

}
