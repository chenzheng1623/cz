package com.example.bottommenu;

import com.example.cz.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ImageReader;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.OnScrollListener;


/**先在指定矩形内绘制 一个icon  和文字
 * 然后绘制带颜色和alpha的icon（先绘制纯色，设置模式再绘制 icon）
 * 
 * @author cz
 *
 */
public class bottommenu extends View {

	private int mcolor=0x45c01a;
	private Bitmap image;
	private String  text;
	private int textsize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	//绘制纯色的
	public bottommenu(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.bottommenu);

		int n=ta.getIndexCount();

		for (int i = 0; i < n; i++) {
			int attr=ta.getIndex(i);
			switch (attr) {
			case R.styleable.bottommenu_image:
				BitmapDrawable drawable= (BitmapDrawable) ta.getDrawable(attr);
				image= drawable.getBitmap();
				break;
			case R.styleable.bottommenu_color:
				mcolor=ta.getColor(attr, 0x45c01a);
				break;
			case R.styleable.bottommenu_text:
				text= ta.getString(attr);
				break;
			case R.styleable.bottommenu_text_size:
				textsize=(int) ta.getDimension(attr, 
						((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()))
						);
				break;
			}
		}
		ta.recycle();

		textrect=new Rect();
		textpaint=new Paint();
		textpaint.setTypeface(Typeface.DEFAULT_BOLD);
		textpaint.setTextSize(textsize);
		
		//测量字的范围
		textpaint.getTextBounds(text, 0, text.length(), textrect);
	}

	private Canvas colorcanvas;
	private Bitmap colorbitmap;
	private Paint colorpaint;



	private Rect imagerect;
	private Rect textrect;
	private Paint textpaint;


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int imagewidth=Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(), 
				getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-textrect.height());

		int left=getMeasuredWidth()/2-imagewidth/2;
		int top=getMeasuredHeight()/2-(textrect.height()+imagewidth)/2;

		imagerect=new Rect(left,top,left+imagewidth,top+imagewidth);
	}

	private float malpha=0;
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(image, null, imagerect,null);
		int alpha=(int) Math.ceil(255*malpha);
		//内存去准备   bitmap setalpha  纯色  xfermode  图标
		setupbitmap(alpha);
		//原文本
		drawsourtext(canvas );
		//变色的文本
		drawtargettext(canvas ,alpha);
		canvas.drawBitmap(colorbitmap, 0, 0,null);
		super.onDraw(canvas);
	}
	/**
	 * 绘制icon
	 */
	private void setupbitmap(int alpha) {
		colorbitmap =Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Config.ARGB_8888);
		colorcanvas=new Canvas(colorbitmap);
		colorpaint =new Paint();
		colorpaint.setColor(mcolor);
		colorpaint.setAntiAlias(true);
		colorpaint.setDither(true);
		colorcanvas.drawRect(imagerect, colorpaint);
		colorpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		colorpaint.setAlpha(alpha);
		colorcanvas.drawBitmap(image, null,imagerect, colorpaint);
	}
	//绘制带颜色的文本
	private void drawtargettext(Canvas canvas, int alpha) {
		textpaint.setColor(mcolor);
		textpaint.setAlpha(alpha);
		int x=getMeasuredWidth()/2-textrect.width()/2;
		int y=imagerect.bottom+textrect.height();
		canvas.drawText(text, x, y, textpaint);
	}

	//绘制原文本
	private void drawsourtext(Canvas canvas) {
		textpaint.setColor(0xff848584);
		int x=getMeasuredWidth()/2-textrect.width()/2;
		int y=imagerect.bottom+textrect.height();
		canvas.drawText(text, x, y, textpaint);

	}




	public void setimagealpha(float positionoffet){
		this.malpha=positionoffet;
		if(Looper.getMainLooper()==Looper.myLooper()){
			invalidate();
		}else {
			postInvalidate();
		}
	}


}
