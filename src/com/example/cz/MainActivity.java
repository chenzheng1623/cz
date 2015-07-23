package com.example.cz;

import java.util.ArrayList;
import java.util.List;


import com.example.bottommenu.bottommenu;
import com.example.cz.R;
import com.example.fragment.coursefragment;
import com.example.fragment.gradefragment;
import com.example.fragment.settingfragment;
import com.example.fragment.tablefragment;

import android.R.fraction;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;

public class MainActivity extends ActionBarActivity  {

	private ViewPager pager;
	private List<Fragment> listfragment=new ArrayList<Fragment>();
	private FragmentStatePagerAdapter adapter;
	private AlertDialog dialog;
	private DrawerLayout mDrawerLayout;
	private ListView listView;
	private ActionBar actionBar;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actionBar=getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.TabListener listener=new TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			}
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
			}
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			}
		};
		actionBar.addTab(actionBar.newTab().setText("课表查询").setTabListener(listener));
		actionBar.addTab(actionBar.newTab().setText("选课查询").setTabListener(listener));
		actionBar.addTab(actionBar.newTab().setText("成绩查询").setTabListener(listener));
		initview();
		initdate();
		
	}
	private void initdate() {
		adapter=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return listfragment.size();
			}
			@Override
			public Fragment getItem(int position) {
				return listfragment.get(position);
			}
		};
		pager.setAdapter(adapter);
	}
	
	
	private void initview() {
		//出事话fragment
		pager=(ViewPager) findViewById(R.id.viewpager);
		listfragment.add(new tablefragment());
		listfragment.add(new coursefragment());
		listfragment.add(new gradefragment());
		listfragment.add(new settingfragment());
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			dialog=new AlertDialog.Builder(MainActivity.this).create();
			dialog.show();
			dialog.getWindow().setContentView(R.layout.bacdialog);
			Button button1= (Button) dialog.getWindow().findViewById(R.id.tuichu1);
			button1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			Button button2= (Button) dialog.getWindow().findViewById(R.id.tuichu2);
			button2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}
		return super.onKeyDown(keyCode, event);

	}




}
