package com.gao.launch;

import java.util.regex.Pattern;

import android.content.Context;
import android.hardware.ConsumerIrManager;

/**
 * 利用手机内置的红外发射器来发射红外信号。
 * 
 * @author 高歌
 * @Time 2016.2.30
 *
 */
public class OwnWaveService {

	private ConsumerIrManager iR;

	public OwnWaveService(Context context) {
		iR = (ConsumerIrManager) context
				.getSystemService(Context.CONSUMER_IR_SERVICE);
	}

	/**
	 * 判断设备是否有内置红外。
	 * 
	 * @return true/false
	 */
	public boolean phoneHasIr() {
		return iR.hasIrEmitter();

	}

	/**
	 * 根据用户码和客户码以及红外协议来发送红外信号。
	 * 
	 * @param userCode
	 *            （16进制）用户码
	 * @param dataCode
	 *            （16进制）数据码
	 */
	public void sendSignal(int userCode, int dataCode) {

		/*
		 * carrierFrequency:载波频率。 pattern:红外编码。
		 */
		int carrierFrequency = 38000;
		int pattern[] = getpattern(userCode, dataCode);

		iR.transmit(carrierFrequency, pattern);

	}

	/**
	 * 通过16进制的数字码来获得按键的编码模式
	 * 
	 * @param userCode
	 *            （16进制）用户码
	 * @param dataCode
	 *            （16进制）数据码
	 * @return 返回按键的编码模式
	 */
	public int[] getpattern(int userCode, int dataCode) {
		/*
		 * 使用NEC协议 0：560,1690 1：560,560 无码：560,39980 重复码：9000,2500,560
		 */

		// int
		// pattern[]={9000,4500,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,
		// 560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,
		// 560,560,560,1690,560,1690,560,1690,560,560,560,560,560,1690,560,560,
		// 560,1690,560,560,560,560,560,560,560,1690,560,1690,560,560,560,1690,
		// 560,39980,9000,2500,560};

		// 编码为：01ff1AE4
		// userCode=0x00ff;
		// dataCode=0x8d72;
		Integer.toHexString(userCode);
		Integer.toHexString(dataCode);
		System.out.println("u:" + Integer.toHexString(userCode));
		System.out.println("u:" + Integer.toHexString(dataCode));
		int pattern[] = new int[71];
		pattern[0] = 9000;
		pattern[1] = 4500;
		int key = 2;
		for (int i = 15; i >= 0; --i) { // ones'complement
			if (((userCode >> i) & 0x1) == 1) { // 1
				pattern[key++] = 560;
				pattern[key++] = 560;
				System.out.println("1");
			} else { // 0
				System.out.println("0");
				pattern[key++] = 560;
				pattern[key++] = 1650;
			}

		}
		for (int i = 15; i >= 0; --i) { // ones'complement
			if (((dataCode >> i) & 0x1) == 1) { // 1
				System.out.println("1");
				pattern[key++] = 560;
				pattern[key++] = 560;
			} else { // 0
				System.out.println("0");
				pattern[key++] = 560;
				pattern[key++] = 1650;
			}

		}

		pattern[66] = 560;
		pattern[67] = 39980;
		pattern[68] = 9000;
		pattern[69] = 2500;
		pattern[70] = 560;

		return pattern;
	}
}
