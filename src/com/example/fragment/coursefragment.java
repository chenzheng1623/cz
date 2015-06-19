package com.example.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.example.bean.curse;
import com.example.cz.R;
import com.example.dao.Coursedao;
import com.example.db.sqllitedbhelper;
import com.example.fragmentadapter.myadapter_course;
import com.example.util.praserhtml;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class coursefragment extends Fragment implements OnRefreshListener {

	//课程url
	public static String courseUrl="http://jw3.nwnu.edu.cn:7001/WebEducation/studentcourseservlet?action=selflist&stid=";
	//系统下啦刷新
	private SwipeRefreshLayout refreshLayout;  
	//账号识别码
	private String number;
	//存储识别码
	private SharedPreferences preferences;
	private ListView listcourse ;
	private Context context;

	private myadapter_course myadapter;
	private Coursedao coursedao;
	private List<curse>  list;
	private AbHttpUtil mabHttpUtil;
	//第一次启动程序
	private boolean flag=true;
	View view;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		context=this.getActivity();
		LayoutInflater inflater=LayoutInflater.from(context);
		view=inflater.inflate(R.layout.activity_course, null, false);
		
		listcourse= (ListView) view.findViewById(R.id.list_course);
		refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh); 
		
		//设置下啦刷新的监听
		refreshLayout.setOnRefreshListener(this);
		
		//获取查询id
		preferences=context.getSharedPreferences("cz", 1);
		number=preferences.getString("number", null);
		
		coursedao=Coursedao.getinstance(context);
		
		//初始化 andbase的http工具类
		mabHttpUtil=AbHttpUtil.getInstance(context);
		mabHttpUtil.setTimeout(10000);
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		refresh();
		return view;
	}

	@Override
	public void onRefresh() {
		mabHttpUtil.get(courseUrl+number, new AbStringHttpResponseListener() {

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				Log.i("andbase", "http开始");
				refreshLayout.setRefreshing(true);
			}
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Log.i("andbase", "http结束");
				refreshLayout.setRefreshing(false);
			}

			@Override
			public void onFailure(int statusCode, String content, Throwable error) {
				// TODO Auto-generated method stub
				Log.i("andbase", "http失败");
				Toast.makeText(context, "加载失败请重试", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(int statusCode, String content) {
				// TODO Auto-generated method stub
				Log.i("andbase", "http成功");
				
				praserhtml praserhtml=new praserhtml(content);
				list=praserhtml.prasercourse();
				if (flag) {
					coursedao.AddtoCourse(list);
					flag=false;
				}else {
					coursedao.Updatecourse(list);
				}
				list.clear();
				refresh();
			}
		});
	}
	public void refresh() {
		list=coursedao.SelectAllcourse();
		myadapter=new myadapter_course(list, context);
		listcourse.setAdapter(myadapter);
		myadapter.notifyDataSetChanged();
	}
}
