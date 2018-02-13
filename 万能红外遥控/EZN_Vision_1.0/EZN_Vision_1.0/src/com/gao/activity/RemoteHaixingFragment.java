package com.gao.activity;

import com.gao.connectServer.ConnectServer;
import com.gao.ezn_vision.R;
import com.gao.launch.OwnWaveService;
import com.gao.launch.WaveService;
import com.gao.launch.WaveService1;
import com.gao.tool.ViewClickVibrate;

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

public class RemoteHaixingFragment extends Fragment {
	private int userCode;
	private int dataCode;
	private OwnWaveService ownWaveService = null;
	private WaveService1 mWaveService1 = null;
	private WaveService mWaveService = null;
	private TextView sign;
	public boolean isMusic = false;
	// 创建Handler
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 区分是哪个msg。 关键
			String reCode = (String) msg.obj;
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
		View view = inflater.inflate(R.layout.remote_center2_fragment, null);
		((Button) view.findViewById(R.id.btn0)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn1)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn2)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn3)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn4)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn5)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn6)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn7)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn8)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.btn9)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.mute)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.return_tv)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.volumn_tv_add)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.volumn_tv_minus)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.channel_add)).setOnClickListener(mListenner);
		((Button) view.findViewById(R.id.channel_minus)).setOnClickListener(mListenner);
		sign = (TextView) view.findViewById(R.id.sign);

		ownWaveService = new OwnWaveService(getActivity());
		mWaveService1 = new WaveService1();
		mWaveService = new WaveService();

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
			case R.id.btn0:
				userCode = 0xff02;
				dataCode = 0xff00;
				break;
			case R.id.btn1:
				userCode = 0xff02;
				dataCode = 0x7f80;
				break;
			case R.id.btn2:
				userCode = 0xff02;
				dataCode = 0xbf40;
				break;
			case R.id.btn3:
				userCode = 0xff02;
				dataCode = 0x3fc0;
				break;
			case R.id.btn4:
				userCode = 0xff02;
				dataCode = 0xdf20;
				break;
			case R.id.btn5:
				userCode = 0xff02;
				dataCode = 0x5fa0;
				break;
			case R.id.btn6:
				userCode = 0xff02;
				dataCode = 0x9f60;
				break;
			case R.id.btn7:
				userCode = 0xff02;
				dataCode = 0x1fe0;
				break;
			case R.id.btn8:
				userCode = 0xff02;
				dataCode = 0xef10;
				break;
			case R.id.btn9:
				userCode = 0xff02;
				dataCode = 0x6f90;
				break;
			case R.id.mute:
				userCode = 0xff02;
				dataCode = 0x4fb0;
				break;
			case R.id.return_tv:
				userCode = 0xff02;
				dataCode = 0x2fd0;
				break;
			case R.id.volumn_tv_add:
				userCode = 0xff02;
				dataCode = 0xe718;
				break;

			case R.id.volumn_tv_minus:
				userCode = 0xff02;
				dataCode = 0x6798;
				break;
			case R.id.channel_add:
				userCode = 0xff02;
				dataCode = 0x9768;
				break;
			case R.id.channel_minus:
				userCode = 0xff02;
				dataCode = 0x17e8;
				break;
			default:
			}

			// 发送信号。
			if (!isMusic) {
				ownWaveService.sendSignal(userCode, dataCode);
				setTextViewDarwable();
			}
			isMusic = false;
		}

	};


	public void setTextViewDarwable() {

		Drawable drawable = getResources().getDrawable(R.drawable.have_sign);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight()); // 设置边界
		sign.setCompoundDrawables(null, null, drawable, null);// 画在右边

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {

						// TODO Auto-generated method stub
						Drawable drawable = getResources().getDrawable(
								R.drawable.no_sign);
						drawable.setBounds(0, 0, drawable.getMinimumWidth(),
								drawable.getMinimumHeight()); // 设置边界
						sign.setCompoundDrawables(null, null, drawable, null);// 画在右边
					}
				}, 300);
			}
		}).start();
	}
}
