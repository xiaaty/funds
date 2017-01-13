package com.gqhmt.tyzf.common.frame.util.common;

import com.gqhmt.tyzf.common.frame.exception.FrameException;

import java.util.Random;

/**
 * 随机数工具类
 * @author zhouwd
 *
 */
public class RandomUtil {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}
	
	/**
     * 返回一个只包含数字的定长随机字符串
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateNumberString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }

	/**
	 * 返回一个定长的随机纯小写字母字符串
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机数字和纯小写字母组成的字符串
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机数字和纯大写字母组成的字符串
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个length长度的只包含0字符的字符串
	 * @param length 字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字num生成一个定长的字符串，长度不够前面补fixdlenth个0
	 * @param num 数字
	 * @param fixdlenth 字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(int num, int fixdlenth) throws FrameException {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if ((fixdlenth - strNum.length()) >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new FrameException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 随机生成批次号的方法
	 * @return
	 */
	public static String generateBatchId() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(DateUtil.getCurrentDateTimeAsInt()));
		sb.append(getFixLenthNumString(3));
		return sb.toString();
	}

	private static String getFixLenthNumString(int strLength) {
		Random rm = new Random();
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);
		// 返回固定的长度的随机数
		return fixLenthString.substring(0, strLength + 1);
	}
	
    /**
     * 返回长度为【strLength】的随机数，在前面补0 
     */  
    public static String getFixLenthString(int strLength) {  
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);  
        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 1);  
    }  

	/**
	 * 随机生成三位序列号
	 */
	public static int buildRandow() {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < 3; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
}
