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
 * ������
 * 
 * @author �߸�
 * @Time 2016.10.23
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	final String LOG_TAG = "Test headset";
	private final boolean mDebug = true; // ���ڵ����õı��
	int currentVolume = 0; // ��¼��ǰ���� volume ���� headset ����
	// boolean isHeadsetOn; //
	boolean isHeadsetConnected; // ��ʾ�Ƿ�������
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
	 * ��ý���
	 */
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onresume");
		startPlayback();

	}

	/*
	 * ��һ������ע��㲥������������״̬������
	 */
	private void startPlayback() {
		// ע��㲥
		registerHeadsetPlugReceiver();
	}

	// ��androidϵͳ�У��������Ĳ���Ͱγ���Ҳ��ͨ���㲥ʵ�ֵģ����Խ���һ��Broadcast Receiver
	// ����"android.intent.action.HEADSET_PLUG" ���Intent��
	//
	// �����Intent�а������¼���ֵ��
	//
	// state --- 0����γ���1�������
	// name--- �ַ���������headset�����͡�
	// microphone -- 1�������headset����˷磬0��û�С�
	// ��ʹ�õ�ʱ����ֱ����AndroidManifest,xml�ļ��о�̬ע������Ч��
	/*
	 * ע��㲥
	 */
	private void registerHeadsetPlugReceiver() {

		headsetPlugReceiver = new HeadsetPlugReceiver();
		IntentFilter filter = new IntentFilter();
		// filter.addAction("android.intent.action.HEADSET_PLUG");//
		// ����Ȩ��Intent.ACTION_HEADSET_PLUG
		filter.addAction("Intent.ACTION_HEADSET_PLUG");
		registerReceiver(headsetPlugReceiver, filter);// ע��

		System.out.println("registerHeadsetPlugReceiver");
	}

	/*
	 * �Զ���һ���㲥�����ڼ��Ӷ������Ƿ񱻲���
	 */
	public class HeadsetPlugReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
				System.out.println("head");
			}
			if (intent.hasExtra("state")) {// �ж϶����Ƿ�γ�
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
	 * FSM:״̬�� front && plugin updateSettings front && unplugin updateSettings
	 * ���ڶ����� ����
	 */
	void updateSettings(boolean isFront) {
		AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); // �õ���Ƶ�������������ṩ���ʿ�������������ģʽ�Ĳ���

		if (isFront && isHeadsetConnected) {
			/* backup current volume */
			currentVolume = mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);// ȡ�õ�ǰ�ֻ���������

			/* set headset stream music volume */// ���ö�������
			mAudioManager
					.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager
							.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);// ���ö�������Ϊ���

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
	 * ����¼�
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

	// ȡ���㲥ע��
	private void unregisterReceiver() {
		this.unregisterReceiver(headsetPlugReceiver);
	}

	/*
	 * ������㲥
	 */
	private void stopPlayback() {
		// updateSettings(false);
		unregisterReceiver();
	}

	/*
	 * ����
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		stopPlayback();
	}

}
