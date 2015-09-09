package com.example.fragment;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bean.curse;
import com.example.bean.grade;
import com.example.cz.MainActivity;
import com.example.cz.R;
import com.example.dao.GradeDao;
import com.example.db.sqllitedbhelper;
import com.example.fragmentadapter.myadapter_grade;
import com.example.util.network;
import com.example.util.praserhtml;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
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
import android.widget.TextView;
import android.widget.Toast;

/**
 * 成绩查询
 */
public class gradefragment extends Fragment implements OnRefreshListener {

	private String gradeUrl="http://jw3.nwnu.edu.cn:7001/WebEducation/studentresultservlet?action=A&ontop=N&stid=";
	private SharedPreferences preferences;
	private SwipeRefreshLayout refreshLayout; 
	private Context context;
	private String number;
	private ListView view_list;
	private	sqllitedbhelper sqllitedbhelper;
	private myadapter_grade adapter_grade;
	private ProgressDialog dialog;
	private List<grade> listgrade=new ArrayList<grade>();
	private GradeDao gradeDao;
	private Handler handler=new Handler(){
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			if (msg.what==3) {
				listgrade=gradeDao.SelectAllgrade();
				adapter_grade =new myadapter_grade(listgrade, context);
				view_list.setAdapter(adapter_grade);
				refreshLayout.setRefreshing(false);
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.activity_grade, null,false);
		view_list=(ListView) view.findViewById(R.id.view_grade);
		refreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.refreshgrade);
		refreshLayout.setOnRefreshListener(this);
		
		context=this.getActivity();
		preferences= context.getSharedPreferences("cz", 0);
		number=	preferences.getString("number", null);

		gradeDao =GradeDao.getinstance(context);
		
		handler.sendEmptyMessage(3);
		return view;
	}
	/*
	 * 联网获取数据
	 */
	private void getgradedate(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(number!=null){
//					network network=new network("http://grad.nwnu.edu.cn/plus/list.php?tid=29");
//					
//					String aa=network.getchengjj(network.getstream());
//					Log.i("人才", aa);
//					
					praserhtml praserhtml=new praserhtml(gradeUrl+number,5000);
					listgrade= praserhtml.praserchengji();
					
					if (gradeDao.SelectAllgrade().isEmpty()){
						gradeDao.AddtoGrade(listgrade);
					} else {
						gradeDao.UpdateGrade(listgrade);
					}
					handler.sendEmptyMessage(3);
				}
			}
		}).start() ;
	}

	@Override
	public void onRefresh() {
		getgradedate();
	}
}
