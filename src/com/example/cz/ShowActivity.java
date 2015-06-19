package com.example.cz;
import com.example.util.Util;
import com.example.util.network;
import com.example.util.Util.HttpConnectionListener;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShowActivity extends ActionBarActivity {

	int [] wappwer={R.drawable.cz1,R.drawable.cz2,R.drawable.cz3,R.drawable.cz4,R.drawable.cz5,R.drawable.cz6,
			R.drawable.cz7,R.drawable.cz8,R.drawable.cz9,R.drawable.cz10};
	private ImageView show;

	private FrameLayout showlayout;
	private TextView textView;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//设置不要标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_show);

		//设置imageview的背景
		show=(ImageView) findViewById(R.id.imageview);
		show.setImageResource(wappwer[getpostion()]);
		//设置版本号
		textView=(TextView) findViewById(R.id.textView1);
		textView.setText("版本信息："+getVersion());

		showlayout =(FrameLayout) findViewById(R.id.show);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in_scale);
		animation.setDuration(2000);
		animation.setFillAfter(true);
		showlayout.setAnimation(animation);
		
		if (network.checkNetwork(getApplicationContext())) {

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent=new Intent();
					SharedPreferences preferences =getSharedPreferences("cz", 0);
					String number= preferences.getString("number", "").toString().trim();
					if("".equals(number)){
						intent.setClass(getApplicationContext(), LoginActivity.class);
					}else {
						intent.setClass(getApplicationContext(), MainActivity.class);
					}
					startActivity(intent);
					ShowActivity.this.finish();
				}
			}, 2000);
		}else {
			finish();
		}

	}
	private int	getpostion(){
		return (int)Math.floor(Math.random()*10);
	}

	private String getVersion(){
		try {

			PackageManager manager=getPackageManager();
			PackageInfo info=manager.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "版本号未知";
		}

	}

}
