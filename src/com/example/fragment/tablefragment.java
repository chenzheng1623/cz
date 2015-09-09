package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.coursetableday;
import com.example.bean.curse;
import com.example.cz.R;
import com.example.db.sqllitedbhelper;
import com.example.util.Util;
import com.example.util.praserhtml;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
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
import android.widget.TextView;
import android.widget.Toast;


public class tablefragment extends Fragment {

    private SwipeRefreshLayout refreshLayout;
    private SharedPreferences preferences;
    private Context context;
    private String number;
    private TextView t1_1;
    private TextView t2_1;
    private TextView t3_1;
    private TextView t4_1;
    private TextView t5_1;

    private TextView t1_3;
    private TextView t2_3;
    private TextView t3_3;
    private TextView t4_3;
    private TextView t5_3;

    private TextView t1_5;
    private TextView t2_5;
    private TextView t3_5;
    private TextView t4_5;
    private TextView t5_5;

    private TextView t1_7;
    private TextView t2_7;
    private TextView t3_7;
    private TextView t4_7;
    private TextView t5_7;

    private TextView t1_9;
    private TextView t2_9;
    private TextView t3_9;
    private TextView t4_9;
    private TextView t5_9;
    sqllitedbhelper sqllitedbhelper;
    private List<coursetableday> courselist = new ArrayList<coursetableday>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 2) {
                SQLiteDatabase db = sqllitedbhelper.getWritableDatabase();
                Cursor cursor = db.query("coursetable", new String[]{"_id", "one", "two", "three", "four", "five"},
                        null, null, null, null, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(context, "加载失败，下拉刷新试试！",Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor.moveToNext()) {
                        coursetableday ct = new coursetableday();
                        ct.setOne(cursor.getString(cursor.getColumnIndex("one")));
                        ct.setTwo(cursor.getString(cursor.getColumnIndex("two")));
                        ct.setThree(cursor.getString(cursor.getColumnIndex("three")));
                        ct.setFour(cursor.getString(cursor.getColumnIndex("four")));
                        ct.setFive(cursor.getString(cursor.getColumnIndex("five")));
                        courselist.add(ct);
                    }
                    t1_1.setText(courselist.get(0).getOne());
                    t2_1.setText(courselist.get(0).getTwo());
                    t3_1.setText(courselist.get(0).getThree());
                    t4_1.setText(courselist.get(0).getFour());
                    t5_1.setText(courselist.get(0).getFive());

                    t1_3.setText(courselist.get(1).getOne());
                    t2_3.setText(courselist.get(1).getTwo());
                    t3_3.setText(courselist.get(1).getThree());
                    t4_3.setText(courselist.get(1).getFour());
                    t5_3.setText(courselist.get(1).getFive());

                    t1_5.setText(courselist.get(2).getOne());
                    t2_5.setText(courselist.get(2).getTwo());
                    t3_5.setText(courselist.get(2).getThree());
                    t4_5.setText(courselist.get(2).getFour());
                    t5_5.setText(courselist.get(2).getFive());

                    t1_7.setText(courselist.get(3).getOne());
                    t2_7.setText(courselist.get(3).getTwo());
                    t3_7.setText(courselist.get(3).getThree());
                    t4_7.setText(courselist.get(3).getFour());
                    t5_7.setText(courselist.get(3).getFive());

                    t1_9.setText(courselist.get(4).getOne());
                    t2_9.setText(courselist.get(4).getTwo());
                    t3_9.setText(courselist.get(4).getThree());
                    t4_9.setText(courselist.get(4).getFour());
                    t5_9.setText(courselist.get(4).getFive());

                }
            }
        }

        ;
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = this.getActivity();
        View view = inflater.inflate(R.layout.activity_table, null, false);
        //一二节
        t1_1 = (TextView) view.findViewById(R.id.t1_1);
        t2_1 = (TextView) view.findViewById(R.id.t2_1);
        t3_1 = (TextView) view.findViewById(R.id.t3_1);
        t4_1 = (TextView) view.findViewById(R.id.t4_1);
        t5_1 = (TextView) view.findViewById(R.id.t5_1);
        //34
        t1_3 = (TextView) view.findViewById(R.id.t1_3);
        t2_3 = (TextView) view.findViewById(R.id.t2_3);
        t3_3 = (TextView) view.findViewById(R.id.t3_3);
        t4_3 = (TextView) view.findViewById(R.id.t4_3);
        t5_3 = (TextView) view.findViewById(R.id.t5_3);
        //56
        t1_5 = (TextView) view.findViewById(R.id.t1_5);
        t2_5 = (TextView) view.findViewById(R.id.t2_5);
        t3_5 = (TextView) view.findViewById(R.id.t3_5);
        t4_5 = (TextView) view.findViewById(R.id.t4_5);
        t5_5 = (TextView) view.findViewById(R.id.t5_5);
        //78
        t1_7 = (TextView) view.findViewById(R.id.t1_7);
        t2_7 = (TextView) view.findViewById(R.id.t2_7);
        t3_7 = (TextView) view.findViewById(R.id.t3_7);
        t4_7 = (TextView) view.findViewById(R.id.t4_7);
        t5_7 = (TextView) view.findViewById(R.id.t5_7);
        //9 10
        t1_9 = (TextView) view.findViewById(R.id.t1_9);
        t2_9 = (TextView) view.findViewById(R.id.t2_9);
        t3_9 = (TextView) view.findViewById(R.id.t3_9);
        t4_9 = (TextView) view.findViewById(R.id.t4_9);
        t5_9 = (TextView) view.findViewById(R.id.t5_9);
        //获取查询id
        preferences = context.getSharedPreferences("cz", 1);
        number = preferences.getString("number", null);

        handler.sendEmptyMessage(2);
        sqllitedbhelper = new sqllitedbhelper(context);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.checkNeywork(getActivity())) {
            if (courselist.isEmpty()) {
                getdate();
            } else {
                Message message = handler.obtainMessage();
                message.obj = courselist;
                handler.sendMessage(message);
            }
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("网络异常")
                    .setPositiveButton("退出", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton("去设置", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .show();
        }
    }

    public void getdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (number != null) {
                    String url2 = "http://jw3.nwnu.edu.cn:7001/WebEducation/studentcourseservlet?action=tablelist&stid=" + number;
                    praserhtml praserhtml = new praserhtml(url2, 5000);
                    //解析网址，返回数据list集合
                    List<coursetableday> coursetabledays = praserhtml.getCoursetables();
                    Log.i("tag", coursetabledays.toString());
                    SQLiteDatabase db = sqllitedbhelper.getWritableDatabase();
                    Cursor cursor = db.query("coursetable", new String[]{"_id", "one", "two", "three", "four", "five"},
                            null, null, null, null, null);
                    //查询数据库如果有数据就更新没有就插入
                    if (cursor.getCount() == 0) {
                        for (coursetableday item : coursetabledays) {
                            ContentValues values = new ContentValues();
                            values.put("one", item.getOne());
                            values.put("two", item.getTwo());
                            values.put("three", item.getThree());
                            values.put("four", item.getFour());
                            values.put("five", item.getFive());
                            db.insert("coursetable", null, values);
                        }
                    } else {
                        int i = 1;
                        for (coursetableday item : coursetabledays) {
                            ContentValues values = new ContentValues();
                            values.put("one", item.getOne());
                            values.put("two", item.getTwo());
                            values.put("three", item.getThree());
                            values.put("four", item.getFour());
                            values.put("five", item.getFive());
                            db.update("coursetable", values, "_id=?", new String[]{"" + i});
                            i++;
                        }
                    }
                    db.close();
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
}

