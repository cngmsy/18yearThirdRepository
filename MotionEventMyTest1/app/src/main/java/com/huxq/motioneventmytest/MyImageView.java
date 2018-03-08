package com.huxq.motioneventmytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView extends ImageView {

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyImageView(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("imageview", "imageview    dispatchTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("imageview", "imageview    dispatchTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("imageview", "imageview    dispatchTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("imageview", "imageview    dispatchTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("imageview", "imageview    onTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("imageview", "imageview    onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("imageview", "imageview    onTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("imageview", "imageview    onTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}
