package com.gao.launch;

import java.util.regex.Pattern;

import android.content.Context;
import android.hardware.ConsumerIrManager;

/**
 * �����ֻ����õĺ��ⷢ��������������źš�
 * 
 * @author �߸�
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
	 * �ж��豸�Ƿ������ú��⡣
	 * 
	 * @return true/false
	 */
	public boolean phoneHasIr() {
		return iR.hasIrEmitter();

	}

	/**
	 * �����û���Ϳͻ����Լ�����Э�������ͺ����źš�
	 * 
	 * @param userCode
	 *            ��16���ƣ��û���
	 * @param dataCode
	 *            ��16���ƣ�������
	 */
	public void sendSignal(int userCode, int dataCode) {

		/*
		 * carrierFrequency:�ز�Ƶ�ʡ� pattern:������롣
		 */
		int carrierFrequency = 38000;
		int pattern[] = getpattern(userCode, dataCode);

		iR.transmit(carrierFrequency, pattern);

	}

	/**
	 * ͨ��16���Ƶ�����������ð����ı���ģʽ
	 * 
	 * @param userCode
	 *            ��16���ƣ��û���
	 * @param dataCode
	 *            ��16���ƣ�������
	 * @return ���ذ����ı���ģʽ
	 */
	public int[] getpattern(int userCode, int dataCode) {
		/*
		 * ʹ��NECЭ�� 0��560,1690 1��560,560 ���룺560,39980 �ظ��룺9000,2500,560
		 */

		// int
		// pattern[]={9000,4500,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,
		// 560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,
		// 560,560,560,1690,560,1690,560,1690,560,560,560,560,560,1690,560,560,
		// 560,1690,560,560,560,560,560,560,560,1690,560,1690,560,560,560,1690,
		// 560,39980,9000,2500,560};

		// ����Ϊ��01ff1AE4
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
