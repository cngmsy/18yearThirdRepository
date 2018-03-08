package com.huxq.motioneventmytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class OutLinearLayout extends LinearLayout {

	public OutLinearLayout(Context context) {
		super(context);
	}

	public OutLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public OutLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("out", "out    dispatchTouchEvent ACTION_DOWN");
			break;
	//		return true;
		case MotionEvent.ACTION_MOVE:
			Log.i("out", "out    dispatchTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("out", "out    dispatchTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("out", "out    dispatchTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
	return true;
//		return super.dispatchTouchEvent(ev);
//		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("out", "out    onInterceptTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("out", "out    onInterceptTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("out", "out    onInterceptTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("out", "out    onInterceptTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
//		return false;
	//	return true;
	return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("out", "out    onTouchEvent ACTION_DOWN");
//			return true;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("out", "out    onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("out", "out    onTouchEvent ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.i("out", "out    onTouchEvent ACTION_POINTER_UP");
			break;

		default:
			break;
		}
//		return true;
		return super.onTouchEvent(event);
	}
}
