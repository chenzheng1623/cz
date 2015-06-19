package com.example.bottommenu;

import com.example.cz.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class refliashlistview  extends ListView implements OnScrollListener {

	View header;
	int headerheight;
	int firstVisibleItem; //当前第一个可见的item的位置
	boolean isremark; //标记再最顶端标记 按下
	int startY; 
	int scrollState;


	final int NONE=0;//正常状态
	final int PULL=1;//提示下啦状态
	final int RELESE=2;//提示释放状态状态
	final int REFLASHING=3;//正在刷新状态状态

	int state =NONE;//当前的状态

	public refliashlistview(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initview(context);
	}


	public refliashlistview(Context context, AttributeSet attrs) {
		super(context, attrs);
		initview(context);
	}

	private void initview(Context context) {
		LayoutInflater inflater=LayoutInflater.from(context);
		header=inflater.inflate(R.layout.list_header, null);
		neasureview(header);
		headerheight=header.getMeasuredHeight();
		topPadding(-headerheight);
		this.addHeaderView(header);
	}
	/**
	 * 通知父布局我站多大地方
	 * @param view
	 */
	private void neasureview(View view){
		ViewGroup.LayoutParams p=	view.getLayoutParams();
		if(p==null){
			p=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
					,ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int width=ViewGroup.getChildMeasureSpec(0, 0, p.width);

		int height;
		if(p.height>0){
			height=MeasureSpec.makeMeasureSpec(p.height, MeasureSpec.EXACTLY);
		}else {
			height=MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}
	
	/**
	 * 设置header布局的上边距
	 * @param toppadding
	 */
	private void topPadding(int toppadding) {
		header.setPadding(header.getPaddingLeft(), toppadding, 
				header.getPaddingRight(), header.getPaddingBottom());
	}


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem=firstVisibleItem;
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState=scrollState;
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (firstVisibleItem==0){
				isremark=true;
				startY= (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			break;
		case MotionEvent.ACTION_UP:
			if (state == RELESE) {
				state = REFLASHING;
				//刷新数据
	//		iReflashListener.onReflash();
				reflashViewByState();
			} else if (state == PULL) {
				state = NONE;
				isremark = false;
				reflashViewByState();
			}
			break;

		}
		return super.onTouchEvent(ev);
	}
	/*
	 * 判断移动过程中的操作
	 */
	private void onMove(MotionEvent e) {
		int tempY = (int) e.getY();
		int space = tempY - startY;
		int topPadding = space -  headerheight;
		if (!isremark) {
			return;
		}
		switch (state) {
		case NONE:
			if (space > 0) {
				state = PULL;
				Log.i("tishi", "此时在顶端");
				reflashViewByState();
			}
			break;
		case PULL:
			topPadding(topPadding);
			Log.i("toppadding",  Integer.toBinaryString( Integer.parseInt(Integer.toBinaryString( space-(headerheight + 30)), 10)));
			
			if (space > headerheight + 30
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				state = RELESE;
				Log.i("tishi", "进入下拉释放状态");
				topPadding(headerheight + 30);
				reflashViewByState();
			}
			break;
		case RELESE:
			topPadding(topPadding);
			if (space < headerheight + 30) {
				state = PULL;
				reflashViewByState();
			} else if (space <= 0) {
				state = NONE;
				isremark = false;
				reflashViewByState();
			}
			break;
		}
	}
	/*
	 * 根据当前状态，改变界面显示
	 */
	private void reflashViewByState() {
		TextView tip = (TextView) header.findViewById(R.id.tip);
		ImageView arrow = (ImageView) header.findViewById(R.id.arrow);
		ProgressBar progress = (ProgressBar) header.findViewById(R.id.progress);
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);
		anim.setFillAfter(true);
		RotateAnimation anim1 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		switch (state) {
		case NONE:
			topPadding(-headerheight);
			arrow.clearAnimation();
			break;
		case PULL:
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("下拉可以刷新");
			arrow.clearAnimation();
			arrow.setAnimation(anim1);
			break;
		case RELESE:
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("松开可以刷新");
			arrow.clearAnimation();
			arrow.setAnimation(anim);
			break;
		case REFLASHING:
			topPadding(50);
			arrow.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			arrow.clearAnimation();
			tip.setText("正在刷新...");
			break;
		}
	}







}
