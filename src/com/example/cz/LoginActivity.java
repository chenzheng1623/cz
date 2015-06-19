package com.example.cz;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.protocol.HTTP;
import org.jsoup.helper.Validate;

import com.example.util.network;
import com.example.util.parserxml;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi") public class LoginActivity extends Activity {

	private Button login,out;
	private EditText user,word;
	private String cc="";
	private String cz="";
	private SharedPreferences preferences;
	private  ProgressDialog proDialog;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			String cz1=	msg.getData().getString("cz1");
			String cz2=	msg.getData().getString("cz2");
			String cz3=	msg.getData().getString("cz3");
			
			if(cz1.equals("1")){
				new AlertDialog.Builder(LoginActivity.this)
				.setTitle("提示！")
				.setMessage("账号错误")
				.create().show();
			}else if(cz1.equals("2")){
				new AlertDialog.Builder(LoginActivity.this)
				.setTitle("提示！")
				.setMessage("密码错误")
				.create().show();
			}else {
				String number=cz2;
				number=number.substring(2);
				 String aa[]=cz3.split("\\^");
				preferences=getSharedPreferences("cz", 0);
				preferences.edit().putString("number", number).commit();
				preferences.edit().putString("name", "欢迎  "+aa[0]+" ^_^").commit();
				proDialog.dismiss();
				Intent intent=new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		};
	};
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		login=(Button) findViewById(R.id.login);
		user=(EditText) findViewById(R.id.user);
		word=(EditText) findViewById(R.id.password);
		user.setFocusable(true);
		user.setFocusableInTouchMode(true);
		user.requestFocus();
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}

	public void login(View view){
		preferences=getSharedPreferences("cz", 0);
		cc= user.getText().toString();
		cz= word.getText().toString();
		if(cc.equals("")||cz.equals("")){
			new AlertDialog.Builder(this)
			.setTitle("提示！")
			.setMessage("账号或密码不能为空")
			.create().show();
			
		}else{
			 proDialog=ProgressDialog.show(LoginActivity.this, "提示", "请稍等");
			new Thread(new Runnable() {

				@Override
				public void run() {
					String path="http://jw3.nwnu.edu.cn:7001/WebEducation/loginservlet?action=1&ucode="+cc+"&upwd="+cz+"&utype=S";
					network net=new network(path);
					InputStream stream= net.getstream();
					parserxml parserxml=new parserxml(stream);
					List<Map<String, String>> info=parserxml.parserlogin();
					
					Log.i("tag","教务系统返回信息"+info.toString());
					
					Message message=handler.obtainMessage();
					Bundle bundle=new Bundle();
					bundle.putString("cz1", info.get(0).get("cz1"));
					bundle.putString("cz2", info.get(0).get("cz2"));
					bundle.putString("cz3", info.get(0).get("cz3"));
					
					message.setData(bundle);
					handler.sendMessage(message);
					System.out.println(info.toString());

				}
			}).start();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK ){
			finish();
		}
		return false;
	}

}
