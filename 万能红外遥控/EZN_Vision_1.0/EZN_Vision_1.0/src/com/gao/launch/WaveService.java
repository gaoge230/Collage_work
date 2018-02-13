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

import java.util.ArrayList;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

/**
 * 利用耳机接口来发射红外信号。
 * 
 * @author 高歌
 * @Time 2016.11.11
 *
 */
public class WaveService {
	private final String LOG_TAG = "WaveService";
	private final boolean mDebug = true;
	private final int duration = 10; // seconds
	/**
	 * 音频采样频率，在录音中同样会有类似参数；通俗讲是每秒进行44100次采样。
	 * 详见：http://en.wikipedia.org/wiki/44,100_Hz
	 */
	private final int sampleRate = 44100; // 样本频率 1/44100 周期
	private int numSamples = duration * sampleRate; // 样本大小
	private final double sample[] = new double[numSamples];
	/**
	 * 音频信号的频率，红外需要38kHz的频率。 手机输出的频率一般在人耳听力中的 20~20kHz范围内，所以这里仅仅取最高频率。
	 * 该频率是指最终的信号的频率，采样率仅存在于数字音频信号生成过程中的一个概念。
	 */
	private final double freqOfTone = 20000; // hz 200000=>20khz(50us) 最高

	private final byte generatedSnd[] = new byte[2 * numSamples];
	/** Data "1" 高电平宽度 0.56 */
	private final float INFRARED_1_HIGH_WIDTH = 0.56f;
	/** Data "1" 低电平宽度1.69 */
	private final float INFRARED_1_LOW_WIDTH = 1.69f; // 2.25 - 0.56
	/** Data "0" 高电平宽度0.56 */
	private final float INFRARED_0_HIGH_WIDTH = 0.56f;
	/** Data "0" 低电平宽度 0.565 */
	private final float INFRARED_0_LOW_WIDTH = 0.565f;// 1.125-0.56
	/** Leader code 高电平宽度9.0 */
	private final float INFRARED_LEADERCODE_HIGH_WIDTH = 9.0f;
	/** Leader code 低电平宽度4.50 */
	private final float INFRARED_LEADERCODE_LOW_WIDTH = 4.50f;
	/** Stop bit 高电平宽度 0.56 */
	private final float INFRARED_STOPBIT_HIGH_WIDTH = 0.56f;

	/**
	 * @param time
	 *            unit:ms
	 * @value 1 0
	 * @return
	 */
	private byte[] genTone(double time, float percent) {
		numSamples = (int) (time / 1000 * sampleRate); // 计算样本大小
		double sample[] = new double[numSamples];
		byte generatedSnd[] = new byte[2 * numSamples];

		// fill out the array
		for (int i = 0; i < numSamples; ++i) {
			/**
			 * 这里用到了数学中正弦波知识，其实就是求出图中S(i)值：
			 * http://en.wikipedia.org/wiki/File:Signal_Sampling.png
			 *
			 * 关于正弦波知识，详见：http://en.wikipedia.org/wiki/Sine_wave 其中公式为：y(t) = A
			 * * sin (2πft + φ) A: 振幅，这里为1； f: 频率，这里为freqOfTone; t:
			 * 时间，这里为(i/sampleRate); φ: 初相位，这里为0；
			 */
			// sample[i] = Math.sin(2 * Math.PI * freqOfTone * (i /
			// sampleRate));
			sample[i] = Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));
		}

		// convert to 16 bit pcm sound array 转换成16位数字信号数组
		// assumes the sample buffer is normalised. 假设样本是正常化
		int idx = 0;
		for (final double dVal : sample) {
			// scale to maximum amplitude
			final short val = (short) (dVal * 32767 * percent); // 2的15次 （2字节）
																// ？？？？
			// in 16 bit wav PCM, first byte is the low order byte
			// //short数组转换为byte数组
			generatedSnd[idx++] = (byte) (val & 0x00ff);
			generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
		}

		return generatedSnd;
	}

	/*
	 * private byte[] genTone(){ // fill out the array for (int i = 0; i <
	 * numSamples; ++i) { sample[i] = Math.sin(2 * Math.PI * i /
	 * (sampleRate/freqOfTone));
	 * 
	 * }
	 * 
	 * // convert to 16 bit pcm sound array // assumes the sample buffer is
	 * normalised. int idx = 0; for (final double dVal : sample) { // scale to
	 * maximum amplitude final short val = (short) ((dVal * 32767)); // in 16
	 * bit wav PCM, first byte is the low order byte generatedSnd[idx++] =
	 * (byte) (val & 0x00ff); generatedSnd[idx++] = (byte) ((val & 0xff00) >>>
	 * 8);
	 * 
	 * }
	 * 
	 * return generatedSnd; }
	 */

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
		byte[] dst = getWave(userCode, dataCode);// 获得波后播放
		final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				sampleRate, AudioFormat.CHANNEL_OUT_STEREO,
				AudioFormat.ENCODING_PCM_16BIT, dst.length,
				AudioTrack.MODE_STATIC);
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

			for (int j = 1; j < 4; ++j) { // 取最高位
				wave_list.add(getLittleHigh());
			}

			wave_list.add(genTone(10, 0)); // 10ms 0
		}

		// 合并字节
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

		//
		wave_list.add(getTou());// 获得头
		wave_list.add(getleaderCode());// 获得引导码
		wave_list.add(getUserCodeToWave(userCode));// 获得用户码
		wave_list.add(getDataCodeToWave(dataCode));// 获得数据码
		wave_list.add(getStopBit());// 获得停止字节
		wave_list.add(getRepeatCode());// 获得重复的码（用于长按效果）
		wave_list.add(getTou());

		// 计算字节总长
		for (byte[] byteTmp : wave_list)
			totalLength += byteTmp.length;

		// 将链表中的字节数组合并成一个字节数组
		int currentPosition = 0;
		byte totalWaveArray[] = new byte[totalLength];

		for (byte[] byteArray : wave_list) {
			System.arraycopy(byteArray, 0, totalWaveArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}
		// 返回完全的字节数组（及波的字节数组）
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
		for (int i = 0; i < 16; ++i) { // 取最高位
			if (((userCode >> i) & 0x1) == 1) { // 1
				wave_list.add(getHigh());
				Log.i(LOG_TAG, "1");
			} else { // 0
				Log.i(LOG_TAG, "0");
				wave_list.add(getLow());
			}
			totalLength += wave_list.get(i).length;
		}

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
	 * 3. data code: sign-and-magnitude+ones'complement
	 * 
	 * @param dataCode
	 * @return
	 */
	private byte[] getDataCodeToWave(byte dataCode) {
		ArrayList<byte[]> wave_list = new ArrayList<byte[]>();
		int totalLength = 0;
		// 取最高位
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
		// 取最高位
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
		// INFRARED_STOPBIT_HIGH_WIDTH 0.56
		return genTone(INFRARED_STOPBIT_HIGH_WIDTH, 1);
	}

	/**
	 * 用于长按效果 这个方法中的参数记得是实测得来的。
	 * 
	 * @return
	 */
	private byte[] getRepeatCode() {
		// 9.0ms(high) + 2.25ms(low) + 0.56ms(high)
		ArrayList<byte[]> waveList = new ArrayList<byte[]>();
		int totalLength = 0;

		waveList.add(genTone(110, 0)); // 110ms 0
		waveList.add(genTone(9.00, 1)); // 9.00ms 1
		waveList.add(genTone(2.25, 0)); // 2.25ms 0
		waveList.add(genTone(0.56, 1)); // 0.56ms 1

		for (byte[] byteTmp : waveList)
			totalLength += byteTmp.length;

		int currentPosition = 0;
		byte repeatCodeArray[] = new byte[totalLength];

		for (byte[] byteArray : waveList) {
			System.arraycopy(byteArray, 0, repeatCodeArray, currentPosition,
					byteArray.length);
			currentPosition += byteArray.length;
		}

		return repeatCodeArray;
	}
}
