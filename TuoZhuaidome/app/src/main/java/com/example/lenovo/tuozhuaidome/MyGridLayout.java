package com.example.lenovo.tuozhuaidome;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

/**
 *
 * 自定义控件：1、自定义组合已有控件 2、自定义控件(继承已有的控件,继承View,继承ViewGroup)
 *
 *
 * @author Administrator
 *
 */
public class MyGridLayout extends GridLayout implements OnLongClickListener,
		OnDragListener {
	OnItemClickLitener onItemClickLitener;
	public interface OnItemClickLitener {
		void OnItemClickLitener(String strItem, View v);
	}

	public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
		this.onItemClickLitener = onItemClickLitener;
	}

	public MyGridLayout(Context context) {
		this(context, null);
	}


	public MyGridLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}


	public MyGridLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		setColumnCount(4);
		setLayoutTransition(new LayoutTransition());
	}


	public void addItems(List<String> list) {
		for (String strtv : list) {
			addTvItem(strtv);
		}
	}

	private int mMargin = 8;


	private boolean mIsDragedAble;


	private View mDragView;


	public void setGridLayoutItemDrageAble(boolean isDrageAble) {
		this.mIsDragedAble = isDrageAble;
	}
	private int mIndex;

	public void addTvItem(final String strTv) {
		final TextView tv = new TextView(getContext());
		tv.setText(strTv);


		LayoutParams params = new LayoutParams();

		params.width = getResources().getDisplayMetrics().widthPixels / 4
				- mMargin * 2;
		params.height = LayoutParams.WRAP_CONTENT;
		params.setMargins(mMargin, 6, mMargin, 6);
		tv.setLayoutParams(params);
		tv.setBackgroundResource(R.drawable.drag_item_selector);
		tv.setGravity(Gravity.CENTER);
		tv.setPadding(6, 10, 6, 10);

		addView(tv, mIndex);
		mIndex--;
		tv.setClickable(false);
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(onItemClickLitener!=null){
					onItemClickLitener.OnItemClickLitener(strTv,tv);
				}
			}
		});
		if (mIsDragedAble) {
			tv.setOnLongClickListener(this);
		} else
			tv.setOnLongClickListener(null);


		this.setOnDragListener(this);

		Log.i("TAG", mIndex + "==================");

		mIndex++;
	}

	@Override
	public boolean onDrag(View arg0, DragEvent event) {
		// String dragEvent = getDragEvent(arg1.getAction());
		switch (event.getAction()) {
			//点击按下条目
			case DragEvent.ACTION_DRAG_STARTED:
				initRects();
				break;
			//移动item
			case DragEvent.ACTION_DRAG_LOCATION:
				int i = getTouchIndex(event);
				if (i >= 0 && mDragView != null && getChildAt(i) != mDragView) {
					removeView(mDragView);
					addView(mDragView, i);
				}
				break;
			//移动结束
			case DragEvent.ACTION_DRAG_ENDED:
				if (mDragView != null)
					mDragView.setEnabled(true);
				break;
		}

		return true;
	}

	@Override//长按点击的时候创建一阴影效果
	public boolean onLongClick(View v) {
		mDragView = v;
		v.startDrag(null, new DragShadowBuilder(v), null, 0);
		v.setEnabled(false);
		return false;
	}
	//返回手指移动到的那个item
	private int getTouchIndex(DragEvent event) {
		float dragX = event.getX();
		float dragY = event.getY();
		for (int i = 0; i < mRects.length; i++) {
			if (mRects[i].contains((int) dragX, (int) dragY)) {
				return i;
			}
		}
		return -1;
	}

	private Rect[] mRects;
	//画一个矩形
	private void initRects() {
		mRects = new Rect[getChildCount()];
		for (int i = 0; i < mRects.length; i++){
			View childView = getChildAt(i);
			Rect rect = new Rect(childView.getLeft(), childView.getTop(),
					childView.getRight(), childView.getBottom());
			mRects[i] = rect;
		}
	}

}
