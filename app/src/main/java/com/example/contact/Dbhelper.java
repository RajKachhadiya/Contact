package com.example.contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(@Nullable Context context) {
        super(context, "demo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "create table ContactBook (id integer primary key autoincrement,Name Text,Number Text)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertdata(String name, String number) {

        String insert = "insert into ContactBook (Name,Number) values('" + name + "','" + number + "')";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(insert);
    }

    public Cursor viewdata() {

        String select = "select * from ContactBook";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(select, null);

        return cursor;
    }

    public void deletedata(int id) {

        String delet = "delete from ContactBook where id = '" + id + "'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(delet);
    }

    public void onUpgrade1(int id, String namee, String numm) {

        String update = "update ContactBook set Name='" + namee + "',Number='" + numm + "' where id='" + id + "'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(update);

    }
}
