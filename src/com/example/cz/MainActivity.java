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
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

@SuppressLint("NewApi") public class MainActivity extends FragmentActivity implements android.view.View.OnClickListener {

	private ViewPager pager;
	private List<Fragment> listfragment=new ArrayList<Fragment>();
	private FragmentStatePagerAdapter adapter;
	private  List<bottommenu> bottommenulist=new ArrayList<bottommenu>();
	private AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initview();
		initdate();
	}
	private void initdate() {
		adapter=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//			@Override
			//			public Object instantiateItem(View container, int position) {
			//				ViewGroup parent =
			//						(ViewGroup) container.getParent();
			//				//						(ViewGroup) listfragment.get(position).getView().getParent();
			//				if (parent != null) {
			//					parent.removeAllViews();
			//				} 
			//				parent.addView(container);
			//			//得到缓存的fragment
			//			Fragment fragment = (Fragment) super.instantiateItem(container,
			//			position);
			//			//得到tag，这点很重要
			//			String fragmentTag = fragment.getTag();
			//			if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
			//			//如果这个fragment需要更新
			//
			//			FragmentTransaction ft = fm.beginTransaction();
			//			//移除旧的fragment
			//			ft.remove(fragment);
			//			//换成新的fragment
			//			fragment = fragments[position % fragments.length];
			//			//添加新fragment时必须用前面获得的tag，这点很重要
			//			ft.add(container.getId(), fragment, fragmentTag);
			//			ft.attach(fragment);
			//			ft.commit();
			//
			//			//复位更新标志
			//			fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
			//			}
			//			return fragment;
			//				return super.instantiateItem(container, position);
			//			}
			@Override
			public int getCount() {
				//				return listfragment.size();
				return listfragment.size();
			}
			@Override
			public Fragment getItem(int position) {
				return listfragment.get(position);
			}
		};

		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {}
			@Override
			public void onPageScrolled(int position, float positionoffet, int arg2) {
				//					Log.e("Tag","position"+position+"position："+positionoffet);
				//				0>1
				//				p==0, offet:0>>1 ;  
				//				1>2
				//				p=1 , offet:0>1;

				//				3>2
				//				 p=1, offet:1>0;
				//				2>1
				//				p:0 ,offet:1>0;
				if(positionoffet>0){
					bottommenu left=bottommenulist.get(position);
					bottommenu right=bottommenulist.get(position+1);
					left.setimagealpha((1-positionoffet));
					right.setimagealpha( positionoffet);
				}
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}
	private void initview() {
		//初始化按钮
		bottommenu table=(bottommenu) findViewById(R.id.table);
		bottommenulist.add(table);
		bottommenu course=(bottommenu) findViewById(R.id.course);
		bottommenulist.add(course);
		bottommenu grade=(bottommenu) findViewById(R.id.grade);
		bottommenulist.add(grade);
		bottommenu setting=(bottommenu) findViewById(R.id.setting);
		bottommenulist.add(setting);
		
		table.setOnClickListener(this);
		course.setOnClickListener(this);
		grade.setOnClickListener(this);
		setting.setOnClickListener(this);

		table.setimagealpha((int) 1.0f);
		//出事话fragment
		pager=(ViewPager) findViewById(R.id.viewpager);
		listfragment.add(new tablefragment());
		listfragment.add(new coursefragment());
		listfragment.add(new gradefragment());
		listfragment.add(new settingfragment());
	}
	/*
	 * 菜单单击效果
	 */
	public void onClick(View v) {
		bottommenulist.get(0).setimagealpha((int) 0.0f);
		bottommenulist.get(1).setimagealpha((int) 0.0f);
		bottommenulist.get(2).setimagealpha((int) 0.0f);
		bottommenulist.get(3).setimagealpha((int) 0.0f);
		int id=v.getId();

		switch (id) {
		case R.id.table:
			//Toast.makeText(this, "0", 0).show();
			bottommenulist.get(0).setimagealpha(1.0f);
			pager.setCurrentItem(0,false);
			break;
		case R.id.course:
			//Toast.makeText(this, "0", 0).show();
			bottommenulist.get(1).setimagealpha(1.0f);
			pager.setCurrentItem(1,false);
			break;
		case R.id.grade:
			//	Toast.makeText(this, "1", 0).show();
			bottommenulist.get(2).setimagealpha( 1.0f);
			pager.setCurrentItem(2,false);
			break;
		case R.id.setting:
			//	Toast.makeText(this, "2", 0).show();
			bottommenulist.get(3).setimagealpha( 1.0f);
			pager.setCurrentItem(3,false);
			break;
		default:
			break;
		}
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
