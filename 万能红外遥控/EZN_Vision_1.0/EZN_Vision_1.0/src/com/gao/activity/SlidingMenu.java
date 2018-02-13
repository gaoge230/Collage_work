package com.gao.activity;

import com.gao.ezn_vision.R;
import com.gao.tool.ViewClickVibrate;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class SlidingMenu extends FrameLayout {
	private Scroller mScroller;
	private Boolean open = false;
	private float downX;
	private float downY;
	private View menuView;
	private View mainView;

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public SlidingMenu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	// 初始化数据
	public void init() {
		// 创建Scroller对象
		mScroller = new Scroller(getContext());
		

	}


	/**
	 * 设置事件分发
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		// 设置按下事件
		case MotionEvent.ACTION_DOWN:
			// 获取向下移动的X轴坐标
			downX = ev.getX();
			// 获取向下移动的Y轴坐标
			downY = ev.getY();
			break;

		// 鼠标移动事件
		case MotionEvent.ACTION_MOVE:
			// 获取向下移动的X轴坐标
			float moveX = ev.getX();
			// 获取向下移动的Y轴坐标
			float moveY = ev.getY();
			// 计算X/Y轴手指移动的距离
			float deltaX = moveX - downX;
			float deltaY = moveY - downY;
			// 判断手指移动的方向是偏向于水平还是垂直
			if (Math.abs(deltaX) > Math.abs(deltaY)) {
				// 表示偏向于水平，那么slidingMenu应该拦截并处理事件
				return true;
			}

			break;
		// 鼠标抬起事件
		case MotionEvent.ACTION_UP:
			break;

		}
		return super.onInterceptTouchEvent(ev);
	}

	/**
	 * 设置滑动事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 获取向下 移动的X轴坐标
			downX = event.getX();

			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = event.getX();
			// 获取手指移动的距离
			float deltaX = moveX - downX;
			// 2.让SlidingMenu移动
			int newScrollx = (int) (getScrollX() - deltaX);
			// 限制右边
			if (newScrollx > 0) {
				newScrollx = 0;

			}
			// 如果小于负的侧滑菜单的宽度
			if (newScrollx < -menuView.getMeasuredWidth()) {
				// 限制左边的最小值
				newScrollx = -menuView.getMeasuredWidth();
			}
			scrollTo(newScrollx, 0);
			// 更新downX
			downX = moveX;
			break;
		case MotionEvent.ACTION_UP:
			if (getScrollX() < -menuView.getMeasuredWidth() / 2) {
				// 在左半边，应该打开
				// 瞬间滚动
				int dx = -menuView.getMeasuredWidth() - getScrollX();
				mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
				// 刷新
				invalidate();
				open = true;
			} else {
				// 在右边，关闭菜单
				int dx = 0 - getScrollX();
				mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
				// 刷新
				invalidate();
				open = false;
			}
			break;
		default:
			break;
		}

		return true;
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		// 当前滚动的X/Y轴坐标，在动画过程中获取
		// true表示动画没有结束
		if (mScroller.computeScrollOffset()) {
			// 获取当前滚动的x/y轴坐标
			int currX = mScroller.getCurrX();
			int currY = mScroller.getCurrY();
			// 手动去滚动
			scrollTo(currX, currY);
			// 刷新
			invalidate();

		}
	}

	// 用来摆放子View在自己中的位置
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// 获取子View的第0个条目，侧滑菜单
		menuView = getChildAt(0);
		// 获取子View的第1个条目，主界面
		mainView = getChildAt(1);
		// 获取layout子View的位置
		menuView.layout(-menuView.getMeasuredWidth(), 0, 0,
				menuView.getMeasuredHeight());
		mainView.layout(0, 0, mainView.getMeasuredWidth(),
				mainView.getMeasuredHeight());

	}

	public void onClickMenu() {
		if (!open) {
			// 在左半边，应该打开
			// 瞬间滚动
			int dx = -menuView.getMeasuredWidth() - getScrollX();
			mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
			// 刷新
			invalidate();
			open = true;

		} else {
			// 在右边，关闭菜单
			int dx = 0 - getScrollX();
			mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
			// 刷新
			invalidate();
			open = false;
		}
	}

}
