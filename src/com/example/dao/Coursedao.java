package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.bean.curse;
import com.example.db.sqllitedbhelper;

public class Coursedao {

	private static Coursedao coursedao ;
	private Context context;
	private sqllitedbhelper  sqllitedbhelper;
	private SQLiteDatabase dbReader;
	private SQLiteDatabase dbWriter;
	private Coursedao(Context context){
		this.context=context;
		sqllitedbhelper=new sqllitedbhelper(context);
		dbReader=sqllitedbhelper.getReadableDatabase();
		dbWriter=sqllitedbhelper.getWritableDatabase();
	}
	//单例模式  只有一个coursedao对象
	public static Coursedao getinstance(Context context){

		if (coursedao==null) {
			coursedao=new Coursedao(context);
		}

		return coursedao;
	}
	/**
	 * 添加数据
	 * @param listcourse
	 */
	public  void AddtoCourse(List<curse> listcourse){
		for (curse item : listcourse) {
			ContentValues values=new ContentValues();
			values.put("name", item.getName());
			values.put("xuliehao", item.getXuliehao());
			values.put("bianma", item.getBianma());
			values.put("teacher", item.getTeacher());
			values.put("time_adress", item.getTime_adress());
			values.put("times", item.getTimes());
			values.put("xuefen", item.getXuefen());
			values.put("status", item.getStatus());
			values.put("action", item.getAction());
			values.put("total", item.getTotal());

			dbWriter.insert("selectcourse", null, values);
		}

	}
	/**
	 * 查询全部数据
	 * @return
	 */
	public List<curse>  SelectAllcourse(){
		List<curse>  listcourse=new ArrayList<curse>();
		Cursor cursor= dbReader.query("selectcourse",new String[]{"_id","name","xuliehao","bianma","teacher","time_adress","times","xuefen","status","action","total"},
				null,null,null,null,null);
		if(cursor.getCount()>0)
		{
			while (cursor.moveToNext()) {
				curse curse=new curse();
				curse.setName(cursor.getString(cursor.getColumnIndex("name")));
				curse.setXuliehao(cursor.getString(cursor.getColumnIndex("xuliehao")));
				curse.setBianma(cursor.getString(cursor.getColumnIndex("bianma")));
				curse.setTeacher(cursor.getString(cursor.getColumnIndex("teacher")));
				curse.setTime_adress(cursor.getString(cursor.getColumnIndex("time_adress")));
				curse.setTimes(cursor.getString(cursor.getColumnIndex("times")));
				curse.setXuefen(cursor.getString(cursor.getColumnIndex("xuefen")));
				curse.setStatus(cursor.getString(cursor.getColumnIndex("status")));
				curse.setAction(cursor.getString(cursor.getColumnIndex("action")));
				curse.setTotal(cursor.getString(cursor.getColumnIndex("total")));
				listcourse.add(curse);
			}
		}
		cursor.close();
		return listcourse;
	}

	/**
	 * 更新数据
	 * @param listcourse
	 * @return
	 */
	public boolean Updatecourse(List<curse> listcourse){
		int i=1;
		for (curse item : listcourse) {
			ContentValues values=new ContentValues();
			values.put("name", item.getName());
			values.put("xuliehao", item.getXuliehao());
			values.put("bianma", item.getBianma());
			values.put("teacher", item.getTeacher());
			values.put("time_adress", item.getTime_adress());
			values.put("times", item.getTimes());
			values.put("xuefen", item.getXuefen());
			values.put("status", item.getStatus());
			values.put("action", item.getAction());
			values.put("total", item.getTotal());

			dbWriter.update("selectcourse", values, "_id=?", new String[]{""+i});
			i++;
		}
		return true;
	}
	public boolean DeleteAllcourse(){

		dbWriter.delete("selectcourse", null, null);

		return true;

	}
	public void cloasDB(){
		if (dbReader!=null) {
			dbReader.close();
		}
		if (dbWriter!=null) {
			dbWriter.close();
		}
		if (sqllitedbhelper!=null) {
			sqllitedbhelper.close();
		}
	}

}
