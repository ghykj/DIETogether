package com.cookandroid.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by LGPC on 2015-12-10.
 */
public class NetworkManager {
    public static String SERVER_URL = "http://54.65.85.60:3333/";
    Calculation cal;

    public static  void setupPOSTConnection(String postParam,HttpURLConnection urlConnection)throws IOException {
        urlConnection.setDoOutput(true);

        try {
            urlConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(postParam.getBytes().length));
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(10000);
        urlConnection.setReadTimeout(10000);
        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        dataOutputStream.writeBytes(postParam);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
    public static  void setupGETConnection(HttpURLConnection urlConnection){
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setConnectTimeout(10000);
        urlConnection.setReadTimeout(10000);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
    }
    public static  void setupDELETEConnection(String postParam,HttpURLConnection urlConnection)throws IOException{
        urlConnection.setDoOutput(true);

        try {
            urlConnection.setRequestMethod("DELETE");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(postParam.getBytes().length));
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(10000);
        urlConnection.setReadTimeout(10000);
        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        dataOutputStream.writeBytes(postParam);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    /*public static boolean checkedID(final Context context){

    }*/

    public static int Join(final Context context){
        final InfoDBManager manager = new InfoDBManager(context, "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from infoTABLE", null);

        cursor.moveToFirst();
            try {
            URL loginUrl = new URL("http://54.65.85.60:3333/user");
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", cursor.getString(1));
            jsonObject.put("name", URLEncoder.encode(cursor.getString(2), "UTF-8"));
            if(cursor.getInt(3)==1){
                jsonObject.put("gender",URLEncoder.encode("m", "UTF-8"));
            }
            else{
                jsonObject.put("gender",URLEncoder.encode("f", "UTF-8"));
            }
            jsonObject.put("age",cursor.getInt(4));
            jsonObject.put("height", (int)cursor.getFloat(5));
            jsonObject.put("weight", (int)cursor.getFloat(6));
            String strJson = jsonObject.toString();
            Log.d("Login Str", strJson);
            setupPOSTConnection(strJson,urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
                Log.d("join result", strResult);
            if(new JSONObject(strResult).getInt("code")==200){
                return 1;
            }else{
                if(new JSONObject(strResult).getString("reason").equals("duplicate-id")) {
                    return 2;
                }
                return 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static int Join(final Context context,JSONObject joinObject){
        final InfoDBManager manager = new InfoDBManager(context, "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from infoTABLE", null);

        cursor.moveToFirst();
        try {
            URL loginUrl = new URL("http://54.65.85.60:3333/user");
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            String strJson = joinObject.toString();
            Log.d("Login Str", strJson);
            setupPOSTConnection(strJson,urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            Log.d("join result", strResult);
            if(new JSONObject(strResult).getInt("code")==200){
                return 1;
            }else{
                if(new JSONObject(strResult).getString("reason").equals("duplicate-id")) {
                    return 2;
                }
                return 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static ArrayList<ListRankItems> getFreindData(final Context context){

        final InfoDBManager manager = new InfoDBManager(context, "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from infoTABLE", null);

        cursor.moveToFirst();
        try {

            URL loginUrl = new URL(SERVER_URL + "walking/friends/id/"+cursor.getString(1));
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            setupGETConnection(urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            JSONObject jsonObject =new JSONObject(strResult);
            JSONArray array = jsonObject.getJSONArray("data");
            ArrayList<ListRankItems> data = new ArrayList<>();
            for(int i=0; i<array.length(); i++){
                JSONObject temp =(JSONObject)array.get(i);
                ListRankItems tempData = new ListRankItems(temp.getString("id"),0,null,temp.getInt("walking_count"),(float)temp.getDouble("calorie"));
                data.add(tempData);
            }
            Collections.sort(data);
            return data;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static int commitPedometer(final Context context){
        Calculation cal = new Calculation();
        final StepDBManager manager = new StepDBManager(context, "step.db", null, 1);
        final InfoDBManager InfoDBManager = new InfoDBManager(context, "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        SQLiteDatabase db2 = InfoDBManager.getReadableDatabase();
        Cursor cursorInfo = db2.rawQuery("select * from infoTABLE", null);
        Cursor cursor = db.rawQuery("select * from stepTABLE where date ='"+cal.currentTime()+"'", null);
        cursor.moveToFirst();
        cursorInfo.moveToFirst();
        try {
            URL loginUrl = new URL("http://54.65.85.60:3333/walking/update");
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",cursorInfo.getString(1));
            jsonObject.put("walking_count", cursor.getInt(2));
            jsonObject.put("calorie", cursor.getInt(3));
            String strJson = jsonObject.toString();
            Log.d("Login Str", strJson);
            setupPOSTConnection(strJson,urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            Log.d("join result", strResult);
            if(new JSONObject(strResult).getInt("code")==200){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static int commitHealth(final Context context, String healthName, int healthNum){
        Calculation cal = new Calculation();
        //final HealthDBManager manager = new HealthDBManager(context, "health.db", null, 1);
        final InfoDBManager InfoDBManager = new InfoDBManager(context, "Info.db", null, 1);
        //SQLiteDatabase db = manager.getReadableDatabase();
        SQLiteDatabase db2 = InfoDBManager.getReadableDatabase();
        Cursor cursorInfo = db2.rawQuery("select * from infoTABLE",null);
        //Cursor cursor = db.rawQuery("select * from healthTABLE where date ='"+cal.currentTime()+"'", null);
        //cursor.moveToFirst();
        cursorInfo.moveToFirst();
        try {
            URL loginUrl = new URL("http://54.65.85.60:3333/health");
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",cursorInfo.getString(1));
            jsonObject.put("health_name", URLEncoder.encode(healthName, "UTF-8"));
            jsonObject.put("health_count",healthNum);
            String strJson = jsonObject.toString();
            Log.d("Login Str", strJson);
            setupPOSTConnection(strJson,urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            Log.d("join result", strResult);
            if(new JSONObject(strResult).getInt("code")==200){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteHealth(final Context context, String healthName, int healthNum){
        Calculation cal = new Calculation();
        //final HealthDBManager manager = new HealthDBManager(context, "health.db", null, 1);
        final InfoDBManager InfoDBManager = new InfoDBManager(context, "Info.db", null, 1);
        //SQLiteDatabase db = manager.getReadableDatabase();
        SQLiteDatabase db2 = InfoDBManager.getReadableDatabase();
        Cursor cursorInfo = db2.rawQuery("select * from infoTABLE",null);
        //Cursor cursor = db.rawQuery("select * from healthTABLE where date ='"+cal.currentTime()+"'", null);
        //cursor.moveToFirst();
        cursorInfo.moveToFirst();
        try {
            URL loginUrl = new URL("http://54.65.85.60:3333/health");
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",cursorInfo.getString(1));
            jsonObject.put("health_name", URLEncoder.encode(healthName, "UTF-8"));
            jsonObject.put("health_count",healthNum);
            String strJson = jsonObject.toString();
            Log.d("Login Str", strJson);
            setupDELETEConnection(strJson, urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            Log.d("join result", strResult);
            if(new JSONObject(strResult).getInt("code")==200){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static int addFriendhd(final Context context, String friendID, String friendName){
        final InfoDBManager InfoDBManager = new InfoDBManager(context, "Info.db", null, 1);
        SQLiteDatabase db2 = InfoDBManager.getReadableDatabase();
        Cursor cursorInfo = db2.rawQuery("select * from infoTABLE", null);
        cursorInfo.moveToFirst();
        try {
            URL loginUrl = new URL("http://54.65.85.60:3333/user/addfriend");
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("my_id",cursorInfo.getString(1));
            jsonObject.put("friend_id", friendID);
            jsonObject.put("friend_name",URLEncoder.encode(friendName, "UTF-8"));
            String strJson = jsonObject.toString();
            Log.d("Login Str", strJson);
            setupPOSTConnection(strJson, urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            Log.d("join result", strResult);
            if(new JSONObject(strResult).getInt("code")==200){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static ArrayList<ListFHealthItems> getFreindHealthData(final Context context, String friendID){

        final InfoDBManager manager = new InfoDBManager(context, "Info.db", null, 1);
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from infoTABLE", null);
        Calculation cal = new Calculation();

        cursor.moveToFirst();
        try {

            URL loginUrl = new URL(SERVER_URL + "health/friends/id/"+cursor.getString(1));
            HttpURLConnection urlConnection = (HttpURLConnection) loginUrl.openConnection();
            setupGETConnection(urlConnection);
            InputStream response = urlConnection.getInputStream();
            String strResult="";
            if(response != null) strResult = convertInputStreamToString(response);
            JSONObject jsonObject =new JSONObject(strResult);
            JSONArray array = jsonObject.getJSONArray("data");
            ArrayList<ListFHealthItems> data = new ArrayList<>();
            for(int i=0; i<array.length(); i++){
                JSONObject temp =(JSONObject)array.get(i);
                //URLEncoder.encode(healthName, "UTF-8")
                ListFHealthItems tempData = new ListFHealthItems(URLDecoder.decode(temp.getString("id"), "UTF-8"), temp.getString("date"), URLDecoder.decode(temp.getString("health_name"), "UTF-8"),temp.getInt("health_count"));

                // ListFHealthItems tempData = new ListFHealthItems(temp.getString("id"),temp.getString("date"),temp.getString("health_name"),temp.getInt("health_count"));
                if(tempData.getId().equals(friendID))
                    data.add(tempData);
            }
            //Collections.sort(data);
            return data;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
