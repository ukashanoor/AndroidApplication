package com.appwizards.flipit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GameDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LEADERBOARD = "leaderboard";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LEADERBOARD + "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT, time TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERBOARD);
        onCreate(sqLiteDatabase);
    }

    public void addScore(String name, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("time", time);
        db.insert(TABLE_LEADERBOARD, null, contentValues);
    }
}
