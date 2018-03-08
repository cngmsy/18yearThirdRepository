package com.huxq.motioneventmytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class InnerLinearLayout extends LinearLayout {

	public InnerLinearLayout(Context context) {
		super(context);
	}

	public InnerLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public InnerLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("inner", "dispatchTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("inner", "dispatchTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("inner", "dispatchTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("inner", "dispatchTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("inner", "onInterceptTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("inner", "onInterceptTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("inner", "onInterceptTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("inner", "onInterceptTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
//		return true;
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("inner", "onTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("inner", "onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("inner", "onTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("inner", "onTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
//		return true;
		return super.onTouchEvent(event);
	}
}
