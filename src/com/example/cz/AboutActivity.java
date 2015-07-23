package com.example.cz;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

@SuppressLint("NewApi") public class AboutActivity extends Activity {


	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		//		android.app.ActionBar bar=getActionBar();
		//		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CDC5BF")));
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK ){
			finish();
		}
		return false;
	}
}
