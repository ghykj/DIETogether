package com.cookandroid.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LGPC on 2015-12-05.
 */
public class InfoDBManager extends SQLiteOpenHelper {

    public InfoDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE infoTABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, name TEXT, gender INTEGER, age INTEGER, height FLOAT, weight FLOAT, bmi FLOAT, bmi_decision TEXT);") ;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS infoTABLE");
        onCreate(db);
    }

    public void modify(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void deleteDB() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop Table infoTABLE");
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from infoTABLE", null);
        while (cursor.moveToNext()) {
            str += cursor.getInt(4);

        }

        return str;
    }
}
