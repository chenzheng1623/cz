package com.example.fragment;

import java.io.IOException;
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
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

/**
 * 选课查询
 */
public class coursefragment extends Fragment implements OnRefreshListener {

    //课程url
    public static String courseUrl = "http://jw3.nwnu.edu.cn:7001/WebEducation/studentcourseservlet?action=selflist&stid=";
    //系统下啦刷新
    private SwipeRefreshLayout refreshLayout;
    //账号识别码
    private String number;
    //存储识别码
    private SharedPreferences preferences;
    private ListView listcourse;
    private Context context;

    private myadapter_course myadapter;
    private Coursedao coursedao;
    private List<curse> list = null;
    private AbHttpUtil mabHttpUtil;
    //第一次启动程序
    private boolean flag = true;

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = this.getActivity();
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_course, null, false);
        listcourse = (ListView) view.findViewById(R.id.list_course);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        //设置下啦刷新的监听
        refreshLayout.setOnRefreshListener(this);
        //获取查询id
        preferences = context.getSharedPreferences("cz", 1);
        number = preferences.getString("number", null);
        coursedao = Coursedao.getinstance(context);
        list = coursedao.SelectAllcourse();
        myadapter = new myadapter_course(list, context);
        listcourse.setAdapter(myadapter);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onRefresh() {
        OkHttpClient mokhttpclient = new OkHttpClient();
        Request build = new Request.Builder().url(courseUrl + number).build();
        refreshLayout.setRefreshing(true);
        mokhttpclient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "加载选课成绩出错！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                praserhtml praserhtml = new praserhtml(response.body().string());
                list = praserhtml.prasercourse();
                if (flag) {
                    coursedao.AddtoCourse(list);
                    flag = false;
                } else {
                    coursedao.Updatecourse(list);
                }
                list.clear();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    public void refresh() {
        list.clear();
        list = coursedao.SelectAllcourse();
        Log.i("tag", list.toString());
        myadapter.notifyDataSetChanged();
    }
}
