package com.gao.activity;

import com.gao.connectServer.ConnectServer;
import com.gao.ezn_vision.R;
import com.gao.launch.OwnWaveService;
import com.gao.launch.WaveService;
import com.gao.launch.WaveService1;
import com.gao.tool.ViewClickVibrate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RemoteFragment extends Fragment {
	private int userCode;
	private int dataCode;
	private OwnWaveService ownWaveService = null;
	private WaveService1 mWaveService1 = null;
	private WaveService mWaveService = null;
	public boolean isMusic=false;
	private TextView sign;
	/**
	 * 连接服务器
	 */
	private ConnectServer connectServer = null;
	/**
	 * 服务器端获得的userCode
	 */
	private int reCodeInt;
	// 创建Handler
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 区分是哪个msg。 关键
			String reCode = (String) msg.obj;
			reCodeInt = Integer.parseInt(reCode, 16);
			System.out.println("reCodeInt=" + reCodeInt);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 这里的R.layout.right是界面的id
		View view = inflater.inflate(R.layout.remote_center_fragment, null);
		((Button) view.findViewById(R.id.btnMute))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btnPower))
				.setOnClickListener(mListenner);
		
/*		((Button) view.findViewById(R.id.btnProgramA))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btnProgramD))
				.setOnClickListener(mListenner);*/
		
		((Button) view.findViewById(R.id.btnVolumnA))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btnVolumnD))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.button_up))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.button_down))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.button_left))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.button_right))
				.setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.button_click_left))
				.setOnClickListener(mListenner);
		sign = (TextView) view.findViewById(R.id.sign);

		ownWaveService = new OwnWaveService(getActivity());
		mWaveService1 = new WaveService1();
		mWaveService = new WaveService();
		// 获取服务端的数据
		connectServer = new ConnectServer();
		connectServer.getPostServerData(handler, "gao");
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	/*
	 * click事件
	 */
	OnClickListener mListenner = new ViewClickVibrate() {
		@Override
		public void onClick(View v) {
			super.onClick(v);
			switch (v.getId()) {
			case R.id.btnMute:
				userCode = 0x00ff;
				dataCode = 0xff00;
				break;
			case R.id.btnPower:
				System.out.println("click Power");
				userCode = reCodeInt;
				dataCode = 0x8d72;
				break;
			/*case R.id.btnProgramA:
				System.out.println("mute click:");
				// mWaveService.sendSignal((short) 0xff00, (byte) 0x4e);

				 //mWaveService.sendSignal((short) 0x00ff, (byte) 0x28);

				// mWaveService.sendSignal((short) 0x00ff, (byte) 0x4e);
				isMusic=true;
				mWaveService1.sendSignal((short) 0x00ff, (byte) 0x8d);
				break;
			case R.id.btnProgramD:
				

				break;*/
			case R.id.btnVolumnA:
				userCode = 0x00ff;
				dataCode = 0x7d82;
				break;
			case R.id.btnVolumnD:
				userCode = 0x00ff;
				dataCode = 0x7f80;
				break;
			case R.id.button_up:
				userCode = 255;
				dataCode = 0x8d72;
				break;
			case R.id.button_down:
				userCode = 0x00ff;
				dataCode = 0xcf30;
				break;
			case R.id.button_left:
				userCode = 0x00ff;
				dataCode = 0x2fd0;
				break;
			case R.id.button_right:
				userCode = 0x00ff;
				dataCode = 0x6d92;
				break;
			case R.id.button_click_left:
				userCode = 0x00ff;
				dataCode = 0xef10;
				break;
			default:
			}
			
			// 发送信号。
			if (!isMusic) {
				ownWaveService.sendSignal(userCode, dataCode);
				setTextViewDarwable();
			}
			isMusic=false;
		}

	};
	public void setTextViewDarwable(){

		Drawable drawable = getResources().getDrawable(R.drawable.have_sign);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
		sign.setCompoundDrawables(null, null, drawable, null);//画在右边
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						
						// TODO Auto-generated method stub
						Drawable drawable = getResources().getDrawable(R.drawable.no_sign);
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
						sign.setCompoundDrawables(null, null, drawable, null);//画在右边
					}
				},300);
			}
		}).start();
	}
	
}
