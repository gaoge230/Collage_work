package com.gao.launch;

/*
 * Copyright (C) 2014 kangear@163.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * ���ö����ӿ�����������źš�
 * @author �߸�
 * @Time 2016.11.11
 *	
 */
import java.util.ArrayList;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class WaveService1 {
	private final String LOG_TAG = "WaveService";
	private final boolean mDebug = true;
	private final int duration = 10; // seconds
	/**
	 * ��Ƶ����Ƶ�ʣ���¼����ͬ���������Ʋ�����ͨ�׽���ÿ�����44100�β�����
	 * �����http://en.wikipedia.org/wiki/44,100_Hz
	 */
	private final int sampleRate = 44100; // ����Ƶ�� 1/44100 ����
	private int numSamples = duration * sampleRate; // ������С
	private final double sample[] = new double[numSamples];
	/**
	 * ��Ƶ�źŵ�Ƶ�ʣ�������Ҫ38kHz��Ƶ�ʡ� �ֻ������Ƶ��һ�����˶������е� 20~20kHz��Χ�ڣ������������ȡ���Ƶ�ʡ�
	 * ��Ƶ����ָ���յ��źŵ�Ƶ�ʣ������ʽ�������������Ƶ�ź����ɹ����е�һ�����
	 */
	private final double freqOfTone = 20000; // hz 20000=>20khz(50us) ���

	private final byte generatedSnd[] = new byte[2 * numSamples];

	/** Data "1" �ߵ�ƽ��� */
	private final float INFRARED_1_HIGH_WIDTH = 0.56f;
	/** Data "1" �͵�ƽ��� */
	private final float INFRARED_1_LOW_WIDTH = 1.69f; // 2.25 - 0.56
	/** Data "0" �ߵ�ƽ��� */
	private final float INFRARED_0_HIGH_WIDTH = 0.56f;
	/** Data "0" �͵�ƽ��� */
	private final float INFRARED_0_LOW_WIDTH = 0.565f;// 1.125-0.56
	/** Leader code �ߵ�ƽ��� */
	private final float INFRARED_LEADERCODE_HIGH_WIDTH = 9.0f;
	/** Leader code �͵�ƽ��� */
	private final float INFRARED_LEADERCODE_LOW_WIDTH = 4.50f;
	/** Stop bit �ߵ�ƽ��� */
	private final float INFRARED_STOPBIT_HIGH_WIDTH = 0.56f;

	/**
	 * @param time
	 *            unit:ms
	 * @value 1 0
	 * @return
	 */
	private byte[] genTone(double time, float percent) {
		numSamples = (int) (time / 1000 * sampleRate); // ����������С
		double sample[] = new double[numSamples];
		byte generatedSnd[] = new byte[2 * numSamples];

		// fill out the array
		for (int i = 0; i < numSamples; ++i) {
			/**
			 * �����õ�����ѧ�����Ҳ�֪ʶ����ʵ�������ͼ��S(i)ֵ��
			 * http://en.wikipedia.org/wiki/File:Signal_Sampling.png
			 *
			 * �������Ҳ�֪ʶ�������http://en.wikipedia.org/wiki/Sine_wave ���й�ʽΪ��y(t) = A
			 * * sin (2��ft + ��) A: ���������Ϊ1�� f: Ƶ�ʣ�����ΪfreqOfTone; t:
			 * ʱ�䣬����Ϊ(i/sampleRate); ��: ����λ������Ϊ0��
			 */
			sample[i] = Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));

		}

		// convert to 16 bit pcm sound array ת����16λ�����ź�����
		// assumes the sample buffer is normalised. ����������������
		int idx = 0;
		for (final double dVal : sample) {

			// scale to maximum amplitude //���ŵ�������
			final short val = (short) (dVal * 32767 * percent); // 2��15�� ��2�ֽڣ�
																// ��������

			// in 16 bit wav PCM, first byte is the low order byte
			// //short����ת��Ϊbyte����
			generatedSnd[idx++] = (byte) (val & 0x00ff);
			generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

		}

		return generatedSnd;
	}

	//
	// private byte[] genTone(){
	// // fill out the array
	// for (int i = 0; i < numSamples; ++i) {
	// //sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
	// sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
	// }
	//
	// // convert to 16 bit pcm sound array
	// // assumes the sample buffer is normalised.
	// int idx = 0;
	// for (final double dVal : sample) {
	// // scale to maximum amplitude
	// final short val = (short) ((dVal * 32767));
	// // in 16 bit wav PCM, first byte is the low order byte
	// generatedSnd[idx++] = (byte) (val & 0x00ff);
	// generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
	//
	// }
	//
	// return generatedSnd;
	// }

	public void sendSignal(short userCode, byte dataCode) {
		Runnable r = new sendThread(userCode, dataCode);
		new Thread(r).start();
	}

	private class sendThread implements Runnable {
		short userCode;
		byte dataCode;

		public sendThread(short userCode1, byte dataCode1) {
			// store parameter for later user
			userCode = userCode1;
			dataCode = dataCode1;
		}

		@Override
		public void run() {
			playSound(userCode, dataCode);
		}
	}

	private void playSound(short userCode, byte dataCode) {
		// ��ò��󲥷�
		byte[] dst = getWave(userCode, dataCode);
		final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				sampleRate, AudioFormat.CHANNEL_OUT_STEREO,
				AudioFormat.ENCODING_PCM_16BIT, dst.length,
				AudioTrack.MODE_STATIC);
		/*
		 * AudioFormat.CHANNEL_OUT_STEREO ˫���� AudioFormat.CHANNEL_OUT_MONO,������
		 * AudioTrack����MODE_STATIC��MODE_STREAM���ַ���
		 * ��STREAM����˼�����û���Ӧ�ó���ͨ��write��ʽ������һ��һ�ε�д��audiotrack�С�
		 * �����������socket�з�������һ����Ӧ�ò��ĳ���ط���ȡ���ݣ�����ͨ�������õ�PCM���ݣ�Ȼ��write��audiotrack��
		 */
		if (mDebug)
			Log.d(LOG_TAG, "length=" + dst.length);
		audioTrack.write(dst, 0, dst.length);
		audioTrack.play();
	}

	/**
	 * PPM wave 0
	 * 
	 * @return
	 */
	private byte[] getLow() {
		// (1.125-0.56) + 0.56
		// INFRARED_0_HIGH_WIDTH 0.56
		// INFRARED_0_LOW_WIDTH 0.565 // 1.125 - 0.56
		byte[] one = genTone(INFRARED_0_HIGH_WIDTH, 1);
		byte[] two = genTone(INFRARED_0_LOW_WIDTH, 0);
		byte[] combined = new byte[one.length + two.length];

		System.arraycopy(one, 0, combined, 0, one.length);
		System.arraycopy(two, 0, combined, one.length, two.length);
		return combined;
	}

	/**
	 * PPM wave 1
	 * 
	 * @return
	 */
	private byte[] getHigh() {
		// 0.56ms + (2.25 - 0.56)
		// INFRARED_1_HIGH_WIDTH 0.56
		// INFRARED_1_LOW_WIDTH 1.69 // 2.25 - 0.56
		byte[] one = genTone(INFRARED_1_HIGH_WIDTH, 1);
		byte[] two = genTone(INFRARED_1_LOW_WIDTH, 0);
		byte[] combined = new byte[one.length + two.length];

		System.arraycopy(one, 0, combined, 0, one.length);
		System.arraycopy(two, 0, combined, one.length, two.length);
		return combined;
	}

	private byte[] getLittleHigh() {
		byte[] one = genTone(INFRARED_1_LOW_WIDTH, 0.08f);
		byte[] two = genTone(INFRARED_1_HIGH_WIDTH, 0);
		byte[] combined = new byte[one.length + two.length];

		System.arraycopy(one, 0, combined, 0, one.length);
		System.arraycopy(two, 0, combined, one.length, two.length);
		return combined;
	}

	private byte[] getTou() {
		ArrayList<byte[]> wave_list = new ArrayList<byte[]>();
		int totalLength = 0;
		for (int i = 0; i < 3; ++i) {
			wave_list.add(genTone(10, 0)); // 10ms 0

			for (int j = 1; j < 4; ++j) { // ȡ���λ
				wave_list.add(getLittleHigh());
			}

			wave_list.add(genTone(10, 0)); // 10ms 0
		}

		// �ϲ��ֽ�
		for (byte[] byteTmp : wave_list)
			totalLength += byteTmp.length;

		int currentPosition = 0;
		byte userCodeWaveArray[] = new byte[totalLength];

		for (byte[] byteArray : wave_list) {
			System.arraycopy(byteArray, 0, userCodeWaveArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}

		return userCodeWaveArray;
	}

	// byte[] getWave(float leaderCode, float space, int userCode ) {

	// 0x0707 0x05
	private byte[] getWave(short userCode, byte dataCode) {
		if (mDebug)
			Log.d(LOG_TAG, "userCode = 0x" + Integer.toHexString(userCode)
					+ " dataCode = 0x" + Integer.toHexString(dataCode));
		ArrayList<byte[]> wave_list = new ArrayList<byte[]>();
		int totalLength = 0;

		//wave_list.add(getTou());// ���ͷ
		wave_list.add(getleaderCode());// ���������
		wave_list.add(getUserCodeToWave(userCode));// ����û���
		wave_list.add(getDataCodeToWave(dataCode));// ���������
		wave_list.add(getStopBit());// ���ֹͣ�ֽ�
		wave_list.add(getRepeatCode());// ����ظ����루���ڳ���Ч����
		//wave_list.add(getTou());

		// �����ֽ��ܳ�
		for (byte[] byteTmp : wave_list)
			totalLength += byteTmp.length;

		// �������е��ֽ�����ϲ���һ���ֽ�����
		int currentPosition = 0;
		byte totalWaveArray[] = new byte[totalLength];

		for (byte[] byteArray : wave_list) {
			System.arraycopy(byteArray, 0, totalWaveArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}

		// ������ȫ���ֽ����飨�������ֽ����飩
		return totalWaveArray;
	}

	/**
	 * 1.leader code
	 * 
	 * @return
	 */
	private byte[] getleaderCode() {
		// 9.0ms + 4.50ms Infrared
		// INFRARED_LEADERCODE_HIGH_WIDTH 9.0
		// INFRARED_LEADERCODE_LOW_WIDTH 4.50
		byte[] one = genTone(INFRARED_LEADERCODE_HIGH_WIDTH, 1);
		byte[] two = genTone(INFRARED_LEADERCODE_LOW_WIDTH, 0);
		byte[] combined = new byte[one.length + two.length];

		System.arraycopy(one, 0, combined, 0, one.length);
		System.arraycopy(two, 0, combined, one.length, two.length);

		return combined;
	}

	/**
	 * 2. user code
	 * 
	 * @param userCode
	 * @return
	 */
	private byte[] getUserCodeToWave(short userCode) {

		ArrayList<byte[]> wave_list = new ArrayList<byte[]>();
		int totalLength = 0;
		for (int i = 0; i < 16; ++i) { // ȡ���λ
			if (((userCode >> i) & 0x1) == 1) { // 1
				wave_list.add(getHigh());
				Log.i(LOG_TAG, "1");
			} else { // 0
				Log.i(LOG_TAG, "0");
				wave_list.add(getLow());
			}
			totalLength += wave_list.get(i).length;
		}

		// for (int i = 15; i >= 0; --i) { // ȡ���λ
		// if (((userCode >> i) & 0x1) == 1) { // 1
		// wave_list.add(getHigh());
		// Log.i(LOG_TAG, "1");
		// } else { // 0
		// Log.i(LOG_TAG, "0");
		// wave_list.add(getLow());
		// }
		// totalLength += wave_list.get(15 - i).length;
		// }
		int currentPosition = 0;
		byte userCodeWaveArray[] = new byte[totalLength];

		for (byte[] byteArray : wave_list) {
			System.arraycopy(byteArray, 0, userCodeWaveArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}
		System.out.println("userCode:" + userCode);
		return userCodeWaveArray;
	}

	/**
	 * 3. data code: sign-and-magnitude+ones'complement
	 * 
	 * @param dataCode
	 * @return
	 */
	private byte[] getDataCodeToWave(byte dataCode) {
		// ----DateCode
		System.out.println("DateCode-----------------" + dataCode);
		ArrayList<byte[]> wave_list = new ArrayList<byte[]>();
		int totalLength = 0;
		// ȡ���λ
		for (int i = 0; i < 8; ++i) { // sign-and-magnitude
			if (((dataCode >> i) & 0x1) == 1) { // 1
				wave_list.add(getHigh());
				Log.i(LOG_TAG, "1");
			} else { // 0
				wave_list.add(getLow());
				Log.i(LOG_TAG, "0");
			}
			totalLength += wave_list.get(i).length;
		}
		// ȡ���λ
		for (int i = 0; i < 8; ++i) { // ones'complement
			if (((dataCode >> i) & 0x1) == 1) { // 1
				wave_list.add(getLow());
				Log.i(LOG_TAG, "0");
			} else { // 0
				wave_list.add(getHigh());
				Log.i(LOG_TAG, "1");
			}
			totalLength += wave_list.get(8 + i).length;
		}

		//
		// // ȡ���λ
		// for (int i = 7; i >= 0; --i) { // sign-and-magnitude
		// if (((dataCode >> i) & 0x1) == 1) { // 1
		// wave_list.add(getHigh());
		// } else { // 0
		// wave_list.add(getLow());
		// }
		// totalLength += wave_list.get(7 - i).length;
		// }
		// // ȡ���λ
		// for (int i = 7; i >= 0; --i) { // ones'complement
		// if (((dataCode >> i) & 0x1) == 1) { // 1
		// wave_list.add(getLow());
		// } else { // 0
		// wave_list.add(getHigh());
		// }
		// totalLength += wave_list.get((8 + 7 - i)).length;
		// }
		int currentPosition = 0;
		byte userCodeWaveArray[] = new byte[totalLength];
		for (byte[] byteArray : wave_list) {
			System.arraycopy(byteArray, 0, userCodeWaveArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}

		return userCodeWaveArray;
	}

	/**
	 * 4.stop bit
	 * 
	 * @return
	 */
	private byte[] getStopBit() {
		// 0.56ms
		System.out.println("stopBit:-------");
		// INFRARED_STOPBIT_HIGH_WIDTH 0.56
		return genTone(INFRARED_STOPBIT_HIGH_WIDTH, 1);
	}

	/**
	 * ���ڳ���Ч�� ��������еĲ����ǵ���ʵ������ġ�
	 * 
	 * @return
	 */
	private byte[] getRepeatCode() {
		// 9.0ms(high) + 2.25ms(low) + 0.56ms(high)
		ArrayList<byte[]> waveList = new ArrayList<byte[]>();
		int totalLength = 0;

		waveList.add(genTone(39.97f, 0)); // 110ms 0
		waveList.add(genTone(9.00f, 1)); // 9.00ms 1
		waveList.add(genTone(2.50f, 0)); // 2.25ms 0
		waveList.add(genTone(0.56f, 1)); // 0.56ms 1

		for (byte[] byteTmp : waveList)
			totalLength += byteTmp.length;

		int currentPosition = 0;
		byte repeatCodeArray[] = new byte[totalLength];

		for (byte[] byteArray : waveList) {
			System.arraycopy(byteArray, 0, repeatCodeArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}
		System.out.println("RerpeatCode---------");
		return repeatCodeArray;
	}
}
