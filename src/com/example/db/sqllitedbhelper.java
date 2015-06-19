package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqllitedbhelper extends SQLiteOpenHelper {

	private static final String DB_NAME="cz_search";
	private static final int version=5;

	public sqllitedbhelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	public sqllitedbhelper(Context context) {
		super(context, DB_NAME, null, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("------------------------", "数据库创建成功");
		
		String sql="create table selectcourse(" +
				"_id integer primary key autoincrement," +
				"name varchar(20)," +
				"xuliehao varchar(20)," +
				"bianma varchar(20)," +
				"teacher varchar(20)," +
				"time_adress varchar(20)," +
				"times varchar(20)," +
				"xuefen varchar(20)," +
				"status varchar(20), " +
				"action varchar(20)," +
				"total varchar(20))";
		
		db.execSQL(sql);
		Log.i("------------------------", "selectcourse表创建成功");
		String sql1="create table grade(" +
				"_id integer primary key autoincrement," +
				"name varchar(20)," +
				"type varchar(20)," +
				"normal varchar(20)," +
				"end varchar(20)," +
				"total varchar(20)," +
				"xuefen varchar(20)," +
				"gall varchar(20))" ;
		
		db.execSQL(sql1);
		Log.i("------------------------", "grade表创建成功");
		
		String sql2="create table coursetable(" +
				"_id integer primary key autoincrement," +
				"one varchar(20)," +
				"two varchar(20)," +
				"three varchar(20)," +
				"four varchar(20)," +
				"five varchar(20))";
		
		db.execSQL(sql2);
		Log.i("------------------------", "table表创建成功");
		
	//	db.execSQL("DROP TABLE IF EXISTS course"); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table selectcourse"); 
		db.execSQL("drop table grade"); 
		db.execSQL("drop table coursetable"); 
		onCreate(db);
	}

}
