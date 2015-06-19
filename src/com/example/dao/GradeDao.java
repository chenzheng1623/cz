package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.bean.grade;
import com.example.db.sqllitedbhelper;

public class GradeDao {

	private static GradeDao gradedao ;
	private Context context;
	private sqllitedbhelper  sqllitedbhelper;
	private SQLiteDatabase dbReader;
	private SQLiteDatabase dbWriter;
	private GradeDao(Context context){
		this.context=context;
		sqllitedbhelper=new sqllitedbhelper(context);
		dbReader=sqllitedbhelper.getReadableDatabase();
		dbWriter=sqllitedbhelper.getWritableDatabase();
	}
	//单例模式  只有一个coursedao对象
	public static GradeDao getinstance(Context context){

		if (gradedao==null) {
			gradedao=new GradeDao(context);
		}

		return gradedao;
	}
	public  List<grade> SelectAllgrade(){
		List<grade> listgrade=new ArrayList<grade>();
		Cursor cursor= dbReader.query("grade",new String[]{"_id","name","type","normal","end","total","xuefen","gall"},
				null,null,null,null,null);
		if(cursor.getCount()>0){
			while (cursor.moveToNext()) {
				grade grade=new grade();

				grade.setName(cursor.getString(cursor.getColumnIndex("name")));
				grade.setType(cursor.getString(cursor.getColumnIndex("type")));
				grade.setNormal(cursor.getString(cursor.getColumnIndex("normal")));
				grade.setEnd(cursor.getString(cursor.getColumnIndex("end")));
				grade.setTotal(cursor.getString(cursor.getColumnIndex("total")));
				grade.setXuefen(cursor.getString(cursor.getColumnIndex("xuefen")));
				grade.setAll(cursor.getString(cursor.getColumnIndex("gall")));

				listgrade.add(grade);
			}

		}
		return listgrade;
	}
	public void AddtoGrade(List<grade> listgrade){
		if (listgrade.size()>0) {
			for (grade item : listgrade) {
				ContentValues values=new ContentValues();

				values.put("name", item.getName());
				values.put("type", item.getType());
				values.put("normal", item.getNormal());
				values.put("end", item.getEnd());
				values.put("total", item.getTotal());
				values.put("xuefen", item.getXuefen());
				values.put("gall", item.getAll());
				dbWriter.insert("grade", null, values);
			}
		}
	}
	public void UpdateGrade(List<grade> listgrade){
		int i=1;
		for (grade item : listgrade) {
			ContentValues values=new ContentValues();

			values.put("name", item.getName());
			values.put("type", item.getType());
			values.put("normal", item.getNormal());
			values.put("end", item.getEnd());
			values.put("total", item.getTotal());
			values.put("xuefen", item.getXuefen());
			values.put("gall", item.getAll());

			dbWriter.update("grade", values, "_id=?", new String[]{""+i});
			i++;
		}

	}


}
