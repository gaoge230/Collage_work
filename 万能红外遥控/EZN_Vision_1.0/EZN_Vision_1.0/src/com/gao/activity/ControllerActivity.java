package com.gao.activity;

import com.gao.connectServer.ConnectServer;
import com.gao.ezn_vision.R;
import com.gao.launch.OwnWaveService;
import com.gao.launch.WaveService;
import com.gao.launch.WaveService1;
import com.gao.tool.ViewClickVibrate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 遥控电脑的控制界面
 * 
 * @author 高歌
 * @Time 2016.11.11
 *
 */
public class ControllerActivity extends FragmentActivity {
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private RemoteFragment remoteFragment;
	private MyFragment myFragment;
	private RemoteHaixingFragment remoteHaixingFragment;
	private SlidingMenu mSlideMenu;
	private RadioButton remote_RadioButton;
	private RadioButton my_RadioButton;
	private ImageView menu_button;
	private TextView btn_addDevice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		System.out.println("oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controller_activity);
		mSlideMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		remote_RadioButton = (RadioButton) findViewById(R.id.remote);
		my_RadioButton = (RadioButton) findViewById(R.id.my);
		btn_addDevice = (TextView) findViewById(R.id.Btn_addDevice);
		menu_button = (ImageView) findViewById(R.id.menu_button);
		iRCTtrl_computer = (TextView) findViewById(R.id.IRCTtrl_computer);
		haiXin_tv = (TextView) findViewById(R.id.HaiXin_tv);
		
		remoteFragment = new RemoteFragment();
		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fragment_center, remoteFragment);
		fragmentTransaction.commit();
		
		btn_addDevice.setOnClickListener(myListenner);
		menu_button.setOnClickListener(myListenner);
		remote_RadioButton.setOnClickListener(myListenner);
		my_RadioButton.setOnClickListener(myListenner);
		iRCTtrl_computer.setOnClickListener(myListenner);
		haiXin_tv.setOnClickListener(myListenner);
		remote_RadioButton.setTextColor(Color.BLACK);
		my_RadioButton.setTextColor(Color.GRAY);

	}

	OnClickListener myListenner = new ViewClickVibrate() {

		@Override
		public void onClick(View v) {

			super.onClick(v);
			switch (v.getId()) {
			case R.id.remote:

				remote_RadioButton.setTextColor(Color.BLACK);
				my_RadioButton.setTextColor(Color.GRAY);
				remoteFragment = new RemoteFragment();
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.fragment_center,
						remoteFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;
			case R.id.my:
				remote_RadioButton.setTextColor(Color.GRAY);
				my_RadioButton.setTextColor(Color.BLACK);
				myFragment = new MyFragment();
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.fragment_center, myFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;
			case R.id.menu_button:
				mSlideMenu.onClickMenu();
				break;
			case R.id.Btn_addDevice:

				Intent intent = new Intent(ControllerActivity.this,
						AddDeviceActivity.class);
				startActivity(intent);

				break;
			case R.id.IRCTtrl_computer:
				remoteFragment = new RemoteFragment();
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.fragment_center,
						remoteFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;
			case R.id.HaiXin_tv:
				remoteHaixingFragment=new RemoteHaixingFragment();
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.fragment_center,
						remoteHaixingFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;
			default:
				break;
			}

		};
	};
	private TextView iRCTtrl_computer;
	private TextView haiXin_tv;

}
