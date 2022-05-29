package com.example.appmy;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "UserDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table info(name TEXT primary key,email TEXT , phone TEXT,address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        Db.execSQL("drop Table if exists info");
    }

    public Boolean insertuserdata(String name, String email, String phone, String address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("address", address);

            long result = DB.insert("info",null,contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }

    }


    public Boolean updateuserdata(String name, String email, String phone, String address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("address", address);

        Cursor cursor = DB.rawQuery("Select * from info where name = ? " ,new String[]{name});

        if (cursor.getCount()>0){

            long result = DB.update("info",contentValues,"name=?",new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else {
            return false;
        }




    }

    public Boolean deleteuserdata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from info where name = ? " ,new String[]{name});

        if (cursor.getCount()>0){

            long result = DB.delete("info","name=?",new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else {
            return false;
        }


    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from info ",null);
        return cursor;
    }

}