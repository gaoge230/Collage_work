package com.gao.activity;

import com.gao.ezn_vision.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主界面
 * 
 * @author 高歌
 * @Time 2016.10.23
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	final String LOG_TAG = "Test headset";
	private final boolean mDebug = true; // 用于调试用的标记
	int currentVolume = 0; // 记录当前音量 volume 音量 headset 耳机
	// boolean isHeadsetOn; //
	boolean isHeadsetConnected; // 表示是否建立连接
	// WaveService mWaveService = new WaveService();
	TextView mTextViewLength = null;
	Handler handler = new Handler();

	HeadsetPlugReceiver headsetPlugReceiver = null;

	/*
	 * oncreate
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("oncreate");
		// mTextViewLength = (TextView) this.findViewById(R.id.textview_length);
	}

	/**
	 * 获得焦点
	 */
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onresume");
		startPlayback();

	}

	/*
	 * 第一步进行注册广播，监听耳机的状态！！！
	 */
	private void startPlayback() {
		// 注册广播
		registerHeadsetPlugReceiver();
	}

	// 在android系统中，检测耳机的插入和拔出，也是通过广播实现的，可以建立一个Broadcast Receiver
	// 监听"android.intent.action.HEADSET_PLUG" 这个Intent。
	//
	// 在这个Intent中包含以下几个值：
	//
	// state --- 0代表拔出，1代表插入
	// name--- 字符串，代表headset的类型。
	// microphone -- 1代表这个headset有麦克风，0则没有。
	// 在使用的时候发现直接在AndroidManifest,xml文件中静态注册是无效的
	/*
	 * 注册广播
	 */
	private void registerHeadsetPlugReceiver() {

		headsetPlugReceiver = new HeadsetPlugReceiver();
		IntentFilter filter = new IntentFilter();
		// filter.addAction("android.intent.action.HEADSET_PLUG");//
		// 增加权限Intent.ACTION_HEADSET_PLUG
		filter.addAction("Intent.ACTION_HEADSET_PLUG");
		registerReceiver(headsetPlugReceiver, filter);// 注册

		System.out.println("registerHeadsetPlugReceiver");
	}

	/*
	 * 自定义一个广播，用于监视耳机孔是否被插入
	 */
	public class HeadsetPlugReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
				System.out.println("head");
			}
			if (intent.hasExtra("state")) {// 判断耳机是否拔出
				if (intent.getIntExtra("state", 0) == 0) {
					if (mDebug)
						// Toast.makeText(getApplicationContext(),
						// "headset can not connected",Toast.LENGTH_SHORT).show();
						Log.d(LOG_TAG, "headset not connected");
					isHeadsetConnected = false;
					updateSettings(true);
				} else if (intent.getIntExtra("state", 0) == 1) {
					if (mDebug)
						// Toast.makeText(getApplicationContext(),
						// "headset can connected",Toast.LENGTH_SHORT).show();
						Log.d(LOG_TAG, "headset  connected");
					isHeadsetConnected = true;
					updateSettings(true);
				}
			}
		}

	}

	/**
	 * FSM:状态机 front && plugin updateSettings front && unplugin updateSettings
	 * 调节耳机的 音量
	 */
	void updateSettings(boolean isFront) {
		AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); // 得到音频管理器，该类提供访问控制音量和钤声模式的操作

		if (isFront && isHeadsetConnected) {
			/* backup current volume */
			currentVolume = mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);// 取得当前手机的音量，

			/* set headset stream music volume */// 设置耳机音量
			mAudioManager
					.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager
							.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);// 设置耳机音量为最大

		} else if (!isFront && isHeadsetConnected) {
			/* back volume */
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
					currentVolume, 0);

		} else if (isFront && !isHeadsetConnected) {
			// do nothing

		} else if (!isFront && !isHeadsetConnected) {
			// do nothing

		} else {
			Log.e(LOG_TAG, "State error!");
			Toast.makeText(
					this,
					"State error! isFront:" + String.valueOf(isFront)
							+ " isHeadsetConnected:"
							+ String.valueOf(isHeadsetConnected),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 点击事件
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		/*
		 * case R.id.volume_add_button: mWaveService.sendSignal((short) 0x00ff,
		 * (byte) 0x28); break; case R.id.volume_sub_button:
		 * mWaveService.sendSignal((short) 0x00ff, (byte) 0x01); break;
		 */
		default:
			break;
		}

	}

	// 取消广播注册
	private void unregisterReceiver() {
		this.unregisterReceiver(headsetPlugReceiver);
	}

	/*
	 * 最后撤销广播
	 */
	private void stopPlayback() {
		// updateSettings(false);
		unregisterReceiver();
	}

	/*
	 * 结束
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		stopPlayback();
	}

}
