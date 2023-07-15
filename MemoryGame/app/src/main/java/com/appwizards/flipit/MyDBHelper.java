package com.appwizards.flipit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GameDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LEADERBOARD = "leaderboard";

    public class MyData {
        private int id;
        private String name;
        private String score;

        public MyData(int id, String name, String score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }
    }

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

    public ArrayList<MyData> getScore() {
        List<MyData> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LEADERBOARD, null);
        if (cursor.moveToFirst()) {
            do {
                // Extract data from the cursor
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String score = cursor.getString(cursor.getColumnIndex("time"));

                // Create a MyData object and add it to the list
                MyData data = new MyData(id, name, score);
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return (ArrayList<MyData>) dataList;
    }
}
