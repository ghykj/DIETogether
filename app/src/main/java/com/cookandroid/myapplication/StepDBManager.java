package com.cookandroid.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LGPC on 2015-12-05.
 */
public class StepDBManager extends SQLiteOpenHelper{
    public StepDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE stepTABLE (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " date TEXT," +
                " step INTEGER, " +
                "cal FLOAT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS stepTABLE");
        onCreate(db);
    }

    public void modify(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        //db.close();
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        //db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void deleteDB() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop Table stepTABLE");
        db.close();
    }
    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from stepTABLE", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(1)

                    + "\n";
        }

        return str;
    }
}
