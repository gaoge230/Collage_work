package com.gao.activity;

import com.gao.connectServer.ConnectServer;
import com.gao.ezn_vision.R;
import com.gao.launch.OwnWaveService;
import com.gao.launch.WaveService;
import com.gao.launch.WaveService1;
import com.gao.tool.ViewClickVibrate;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MyFragment extends Fragment {

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_center_fragment, null);

        return view;
    }
 
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

}
