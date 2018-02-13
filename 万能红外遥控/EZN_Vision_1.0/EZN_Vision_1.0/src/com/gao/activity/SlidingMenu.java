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

	// ��ʼ������
	public void init() {
		// ����Scroller����
		mScroller = new Scroller(getContext());
		

	}


	/**
	 * �����¼��ַ�
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		// ���ð����¼�
		case MotionEvent.ACTION_DOWN:
			// ��ȡ�����ƶ���X������
			downX = ev.getX();
			// ��ȡ�����ƶ���Y������
			downY = ev.getY();
			break;

		// ����ƶ��¼�
		case MotionEvent.ACTION_MOVE:
			// ��ȡ�����ƶ���X������
			float moveX = ev.getX();
			// ��ȡ�����ƶ���Y������
			float moveY = ev.getY();
			// ����X/Y����ָ�ƶ��ľ���
			float deltaX = moveX - downX;
			float deltaY = moveY - downY;
			// �ж���ָ�ƶ��ķ�����ƫ����ˮƽ���Ǵ�ֱ
			if (Math.abs(deltaX) > Math.abs(deltaY)) {
				// ��ʾƫ����ˮƽ����ôslidingMenuӦ�����ز������¼�
				return true;
			}

			break;
		// ���̧���¼�
		case MotionEvent.ACTION_UP:
			break;

		}
		return super.onInterceptTouchEvent(ev);
	}

	/**
	 * ���û����¼�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��ȡ���� �ƶ���X������
			downX = event.getX();

			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = event.getX();
			// ��ȡ��ָ�ƶ��ľ���
			float deltaX = moveX - downX;
			// 2.��SlidingMenu�ƶ�
			int newScrollx = (int) (getScrollX() - deltaX);
			// �����ұ�
			if (newScrollx > 0) {
				newScrollx = 0;

			}
			// ���С�ڸ��Ĳ໬�˵��Ŀ��
			if (newScrollx < -menuView.getMeasuredWidth()) {
				// ������ߵ���Сֵ
				newScrollx = -menuView.getMeasuredWidth();
			}
			scrollTo(newScrollx, 0);
			// ����downX
			downX = moveX;
			break;
		case MotionEvent.ACTION_UP:
			if (getScrollX() < -menuView.getMeasuredWidth() / 2) {
				// �����ߣ�Ӧ�ô�
				// ˲�����
				int dx = -menuView.getMeasuredWidth() - getScrollX();
				mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
				// ˢ��
				invalidate();
				open = true;
			} else {
				// ���ұߣ��رղ˵�
				int dx = 0 - getScrollX();
				mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
				// ˢ��
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
		// ��ǰ������X/Y�����꣬�ڶ��������л�ȡ
		// true��ʾ����û�н���
		if (mScroller.computeScrollOffset()) {
			// ��ȡ��ǰ������x/y������
			int currX = mScroller.getCurrX();
			int currY = mScroller.getCurrY();
			// �ֶ�ȥ����
			scrollTo(currX, currY);
			// ˢ��
			invalidate();

		}
	}

	// �����ڷ���View���Լ��е�λ��
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// ��ȡ��View�ĵ�0����Ŀ���໬�˵�
		menuView = getChildAt(0);
		// ��ȡ��View�ĵ�1����Ŀ��������
		mainView = getChildAt(1);
		// ��ȡlayout��View��λ��
		menuView.layout(-menuView.getMeasuredWidth(), 0, 0,
				menuView.getMeasuredHeight());
		mainView.layout(0, 0, mainView.getMeasuredWidth(),
				mainView.getMeasuredHeight());

	}

	public void onClickMenu() {
		if (!open) {
			// �����ߣ�Ӧ�ô�
			// ˲�����
			int dx = -menuView.getMeasuredWidth() - getScrollX();
			mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
			// ˢ��
			invalidate();
			open = true;

		} else {
			// ���ұߣ��رղ˵�
			int dx = 0 - getScrollX();
			mScroller.startScroll(getScrollX(), 0, dx, 0, 350);
			// ˢ��
			invalidate();
			open = false;
		}
	}

}
