package com.avner.hw4;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLUtils extends SQLiteOpenHelper {

	public static final String DB_NAME = "data.db";
	private static final int DB_VERSION = 4;
	private static final String TABLE = "sheep_table";
	
	public SQLUtils(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DB", "onCreate");
		
		String sql = "create table " + TABLE + "(sheepName VARCHAR( 10 ), age INTEGER, color VARCHAR( 10 ));";
		
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void insertData(SheepData data) {

		Log.d("DB", "onInsert");
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		String sql = "INSERT into " + TABLE + 
			" (sheepName, age, color) VALUES ('" + data.name + "','" + data.age + "','" + data.color + "')";
		
		db.execSQL(sql);
		
		db.close();
	}

    public ArrayList<SheepData> getSheepData() {

        ArrayList<SheepData> sheepData = new ArrayList<SheepData>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            String name = cursor.getString(0);
            String age = cursor.getString(1);
            String color = cursor.getString(2);

            SheepData data = new SheepData(name, age, color);

            sheepData.add(data);

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return sheepData;
    }

}
