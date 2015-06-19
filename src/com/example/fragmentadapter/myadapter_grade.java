package com.example.fragmentadapter;

import java.util.List;
import java.util.Map;

import com.example.cz.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class myadapter_grade extends BaseAdapter {

	private  List<com.example.bean.grade> list;
	private Context context;

	public myadapter_grade(List<com.example.bean.grade> list,Context context) {
		this.context=context;
		this.list=list;
	}
	public  final  class grade{

		public  TextView name;
		public  TextView type;
		public  TextView normal;
		public  TextView end;
		public  TextView total;
		public  TextView xuefen;

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

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		LayoutInflater inflater=LayoutInflater.from(context);

		if(arg0==(getCount()-1)){
			View view=inflater.inflate(R.layout.total_course, null);
			TextView textView =(TextView) view.findViewById(R.id.count);
			textView.setText(list.get(arg0).getAll());
			textView.setTextColor(Color.parseColor("#000000"));
			convertView=view;
		}else {
			//最后一个convertview 不能被重复利用  所以在向上滑动的时候  置空。
			if(arg0==(getCount()-5)){
				convertView=null;
			}
			if(arg0==(getCount()-4)){
				convertView=null;
			}
			if(arg0==(getCount()-6)){
				convertView=null;
			}
			grade grade=new grade();
			if(convertView==null){
				convertView= inflater.inflate(R.layout.view_grade, null);

				grade.name=(TextView) convertView.findViewById(R.id.t1);
				grade. type=(TextView) convertView.findViewById(R.id.t2);
				grade. normal=(TextView) convertView.findViewById(R.id.t3);
				grade. end=(TextView) convertView.findViewById(R.id.t4);
				grade. total=(TextView) convertView.findViewById(R.id.t5);
				grade. xuefen=(TextView) convertView.findViewById(R.id.t6);

				convertView.setTag(grade);
			}else {
				grade=(com.example.fragmentadapter.myadapter_grade.grade) convertView.getTag();
			}
			grade.name.setText(list.get(arg0).getName());
			grade.type.setText(list.get(arg0).getType());
			grade.normal.setText(list.get(arg0).getNormal());
			grade.end.setText(list.get(arg0).getEnd());
			grade.total.setText(list.get(arg0).getTotal());
			grade.xuefen.setText(list.get(arg0).getXuefen());
		}
		return convertView;
	}

}
