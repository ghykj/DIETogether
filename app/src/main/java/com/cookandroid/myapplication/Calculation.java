package com.cookandroid.myapplication;

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

    /*public float manStandardWeight(float height){
        height = height/100;
        float standard = height*height*22;

        return standard;
    }

    public float womanStandardWeight(float height){
        height = height/100;
        float standard = height*height*21;

        return standard;
    }*/

    public String bmiDecision(float bmi) {
        if(bmi<20) return "저체중";
        else if(bmi<25) return "정상";
        else if(bmi<30) return "과체중";
        else return "비만";
    }

}
