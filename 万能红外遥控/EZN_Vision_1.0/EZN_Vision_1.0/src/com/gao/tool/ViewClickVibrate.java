package com.gao.tool;

import android.view.View;
import android.view.View.OnClickListener;

public  class ViewClickVibrate implements OnClickListener{
	/** ��ť��ʱ�� */
	private final int VIBRATE_TIME = 60;
	
	
	@Override
	public  void onClick(View v) {
		// TODO ���������еı���ж��Ƿ�ִ����
		VibrateHelp.vSimple(v.getContext(), VIBRATE_TIME);
	}
}
