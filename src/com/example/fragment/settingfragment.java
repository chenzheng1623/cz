package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.cz.AboutActivity;
import com.example.cz.LoginActivity;
import com.example.cz.R;
import com.example.cz.ShowActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class settingfragment extends Fragment {

	private Context context;
	private ListView listsetting;
	private SimpleAdapter adapter;
	private TextView textView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.setting, null,false);
		
		
		textView=(TextView) view.findViewById(R.id.people_name);
		context=this.getActivity();
		SharedPreferences preferences=context.getSharedPreferences("cz", 1);
	    textView.setText(preferences.getString("name", ""));
		
		listsetting=(ListView) view.findViewById(R.id.list_setting);
		adapter=new SimpleAdapter(context, getdate(), R.layout.setting_item,
				new String[]{"c1",},
				new int []{R.id.textView11});
		listsetting.setAdapter(adapter);
		listsetting.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Intent intent=new Intent();
					intent.setClass(getActivity(), LoginActivity.class);
					startActivity(intent);
					getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
					getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
					break;
				case 1:
					Intent intent1=new Intent();
					intent1.setClass(getActivity(), AboutActivity.class);
					startActivity(intent1);
					break;
				default:
					break;
				}
			}
		});

		return view;
	}
	private List<Map<String, String>> getdate() {
		List<Map<String, String>> data=new ArrayList<Map<String, String>>();
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("c1", "切换账号");
		data.add(map);
		
		Map<String, String> map1=new HashMap<String, String>();
		map1.put("c1","关于");
		data.add(map1);
		return data;
	}



}
