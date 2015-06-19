package com.example.fragmentadapter;

import java.util.List;
import java.util.Map;

import com.example.bean.curse;
import com.example.cz.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class myadapter_course extends BaseAdapter {

	private  List<curse> list;
	private Context context;

	public myadapter_course(List<curse> list,Context context) {
		this.context=context;
		this.list=list;
	}
	public  final  class course{

		public  TextView name;
		public  TextView xuliehao;
		public  TextView bianma;
		public  TextView teacher;
		public  TextView time_adress;

		public  TextView times;
		public  TextView xuefen;
		public  TextView status;
		public  TextView action;

	}


	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	/*
	 * 不用缓冲是考虑到，最后一个item 不能被其他重复用，往回滑动存在bug
	 */
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		LayoutInflater inflater=LayoutInflater.from(context);

		if(arg0==(getCount()-1)){
			View view=inflater.inflate(R.layout.total_course, null);
			TextView count =(TextView) view.findViewById(R.id.count);
			count.setText(list.get(arg0).getTotal());
			convertView=view;
		}else {
			if(arg0==(getCount()-3)){
				convertView=null;
			}
			if(arg0==(getCount()-5)){
				convertView=null;
			}
			if(arg0==(getCount()-4)){
				convertView=null;
			}
			course course=new course();

			if (convertView==null) {
				convertView= inflater.inflate(R.layout.view_course, null);

				course.name=(TextView) convertView.findViewById(R.id.f1);
				course. xuliehao=(TextView) convertView.findViewById(R.id.f2);
				course. bianma=(TextView) convertView.findViewById(R.id.f3);
				course. teacher=(TextView) convertView.findViewById(R.id.f4);
				course. time_adress=(TextView) convertView.findViewById(R.id.f5);
				course. times=(TextView) convertView.findViewById(R.id.f6);
				course. xuefen=(TextView) convertView.findViewById(R.id.f7);
				course. status=(TextView) convertView.findViewById(R.id.f8);
				course. action=(TextView) convertView.findViewById(R.id.f9);
				convertView.setTag(course);
			}else {
				course=(com.example.fragmentadapter.myadapter_course.course) convertView.getTag();
			}
			course.name.setText(list.get(arg0).getName());
			course.xuliehao.setText(list.get(arg0).getXuliehao());
			course.bianma.setText(list.get(arg0).getBianma());
			course.teacher.setText(list.get(arg0).getTeacher());
			course.time_adress.setText(list.get(arg0).getTime_adress());
			course.times.setText(list.get(arg0).getTimes());
			course.xuefen.setText(list.get(arg0).getXuefen());
			course.status.setText(list.get(arg0).getStatus());
			course.action.setText(list.get(arg0).getAction());
		}
		return convertView;
	}

}
