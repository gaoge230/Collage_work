package com.gao.activity;

import com.gao.ezn_vision.R;
import com.gao.tool.ViewClickVibrate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 * 添加遥控设备的界面
 * @author gaoge
 * @time 2017.3.31
 *
 */
public class AddDeviceActivity extends Activity {
	private ImageView return_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_device_activity);
		return_button = (ImageView) findViewById(R.id.return_button);
		findViewById(R.id.div_tv).setOnClickListener(myListener);
		findViewById(R.id.div_computer).setOnClickListener(myListener);
		return_button.setOnClickListener(myListener);
		
	}
	OnClickListener myListener=new ViewClickVibrate(){
		public void onClick(View v) {
			super.onClick(v);
			switch (v.getId()) {
			case R.id.return_button:
				finish();
				break;
			case R.id.div_tv:
				Intent intent=new Intent(AddDeviceActivity.this,TvDeviceActivity.class);
				startActivity(intent);
				break;
			case R.id.div_computer:
				Intent intent1=new Intent(AddDeviceActivity.this,ComputerDeviceActivity.class);
				startActivity(intent1);
				break;
			default:
				break;
			}
			
		};
	};

}
