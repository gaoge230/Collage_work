package com.gao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gao.ezn_vision.R;

public class ComputerDeviceActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.computer_device_activity);
		findViewById(R.id.computer_device_return_button).setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.computer_device_return_button:
				finish();
			break;

		default:
			break;
		}
	}
}
