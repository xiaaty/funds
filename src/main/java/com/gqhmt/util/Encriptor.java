package com.gqhmt.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public  class Encriptor {	
	private static Logger logger = Logger.getLogger(Encriptor.class);

	public static String getMD5(String plainText) {
		String md5 = null;
		try {
			if(plainText!=null){
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0) i += 256;
					if (i < 16) buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				md5 = buf.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return md5;
	}

	/**
	 * Encodes hex octects into Base64.
	 * 
	 * @param binaryData
	 *            Array containing binary data to encode.
	 * @return Base64-encoded data.
	 */
	public static byte[] encodeBase64(byte[] binaryData) {
		int lengthDataBits = binaryData.length * EIGHTBIT;
		int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
		int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
		byte encodedData[] = null;

		if (fewerThan24bits != 0) {
			// data not divisible by 24 bit
			encodedData = new byte[(numberTriplets + 1) * 4];
		}
		else {
			// 16 or 8 bit
			encodedData = new byte[numberTriplets * 4];
		}

		byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;

		int encodedIndex = 0;
		int dataIndex = 0;
		int i = 0;
		// log.debug("number of triplets = " + numberTriplets);
		for (i = 0; i < numberTriplets; i++) {
			dataIndex = i * 3;
			b1 = binaryData[dataIndex];
			b2 = binaryData[dataIndex + 1];
			b3 = binaryData[dataIndex + 2];

			// log.debug("b1= " + b1 +", b2= " + b2 + ", b3= " + b3);

			l = (byte) (b2 & 0x0f);
			k = (byte) (b1 & 0x03);

			encodedIndex = i * 4;
			byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
			byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);
			byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6) : (byte) ((b3) >> 6 ^ 0xfc);

			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			// log.debug( "val2 = " + val2 );
			// log.debug( "k4   = " + (k<<4) );
			// log.debug( "vak  = " + (val2 | (k<<4)) );
			encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
			encodedData[encodedIndex + 2] = lookUpBase64Alphabet[(l << 2) | val3];
			encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 0x3f];
		}

		// form integral number of 6-bit groups
		dataIndex = i * 3;
		encodedIndex = i * 4;
		if (fewerThan24bits == EIGHTBIT) {
			b1 = binaryData[dataIndex];
			k = (byte) (b1 & 0x03);
			// log.debug("b1=" + b1);
			// log.debug("b1<<2 = " + (b1>>2) );
			byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
			encodedData[encodedIndex + 2] = PAD;
			encodedData[encodedIndex + 3] = PAD;
		}
		else if (fewerThan24bits == SIXTEENBIT) {

			b1 = binaryData[dataIndex];
			b2 = binaryData[dataIndex + 1];
			l = (byte) (b2 & 0x0f);
			k = (byte) (b1 & 0x03);

			byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
			byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);

			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
			encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
			encodedData[encodedIndex + 3] = PAD;
		}

		return encodedData;
	}

	static private final int BASELENGTH = 255;
	static private final int LOOKUPLENGTH = 64;
	static private final int TWENTYFOURBITGROUP = 24;
	static private final int EIGHTBIT = 8;
	static private final int SIXTEENBIT = 16;
	// static private final int SIXBIT = 6;
	static private final int SIGN = -128;
	static private final byte PAD = (byte) '=';
	static private byte[] base64Alphabet = new byte[BASELENGTH];
	static private byte[] lookUpBase64Alphabet = new byte[LOOKUPLENGTH];
	// static private final Log log =
	// LogSource.getInstance("org.apache.commons.util.Base64");

	static {
		for (int i = 0; i < BASELENGTH; i++) {
			base64Alphabet[i] = -1;
		}
		for (int i = 'Z'; i >= 'A'; i--) {
			base64Alphabet[i] = (byte) (i - 'A');
		}
		for (int i = 'z'; i >= 'a'; i--) {
			base64Alphabet[i] = (byte) (i - 'a' + 26);
		}
		for (int i = '9'; i >= '0'; i--) {
			base64Alphabet[i] = (byte) (i - '0' + 52);
		}

		base64Alphabet['+'] = 62;
		base64Alphabet['/'] = 63;

		for (int i = 0; i <= 25; i++)
			lookUpBase64Alphabet[i] = (byte) ('A' + i);

		for (int i = 26, j = 0; i <= 51; i++, j++)
			lookUpBase64Alphabet[i] = (byte) ('a' + j);

		for (int i = 52, j = 0; i <= 61; i++, j++)
			lookUpBase64Alphabet[i] = (byte) ('0' + j);

		lookUpBase64Alphabet[62] = (byte) '+';
		lookUpBase64Alphabet[63] = (byte) '/';

	}

	private static Key getDesKey(byte[] keyByte) {
		Key key = null;
		// 创建一个空的八位数组,默认情况下为0
		byte[] byteTemp = new byte[8];
		// 将用户指定的规则转换成八位数组
		for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
			byteTemp[i] = keyByte[i];
		}
		key = new SecretKeySpec(byteTemp, "DES");
		return key;
	}

	/**
	 * 第二种产生key的方法
	 * 
	 * @return
	 */
	public Key getDesKey2() {
		Key key = null;
		// 创建一个可信任的随机数源，DES算法需要
		SecureRandom sr = new SecureRandom();

		try {
			// 用DES算法创建一个KeyGenerator对象
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			// 初始化此密钥生成器,使其具有确定的密钥长度
			kg.init(sr);
			// 生成密匙
			key = kg.generateKey();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return key;
	}

	/**
	 * @param keystr
	 *            加密使用的密钥！
	 * @return 生成密文的字符串表示形式
	 */
	public static String encriptDES(String keystr, String srcCode) {
		Key key = getDesKey(keystr.getBytes());
		StringBuffer sb = null;

		try {
			/*
			 * Cipher类无构造方法，调用getInstance()方法将所请求转换的名称传递给它 参数为 转换的名称，例如
			 * DES/CBC/PKCS5Padding，这里我们使用DES转换。
			 */
			Cipher encriptCipher = Cipher.getInstance("DES");
			// 用密钥初始化此 Cipher
			encriptCipher.init(Cipher.ENCRYPT_MODE, key);
			// 按单部分操作加密数据
			byte[] desCode = encriptCipher.doFinal(srcCode.getBytes());
			// 将加密后的数据转换成16进制的字符串返回
			sb = new StringBuffer(desCode.length * 2);
			for (int i = 0; i < desCode.length; i++) {
				int temp = desCode[i];
				// 把负数转换为正数
				if (temp < 0) {
					temp = temp + 256;// byte的最小值为-256,最大值为255
				}
				// 小于 0F 的数需要在前面补0
				if (temp < 16) {
					sb.append("0");
				}
				sb.append(Integer.toString(temp, 16));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	// 解密算法
	public static String decriptDES(String encriptCode, String keystr) {
		if (encriptCode == null || encriptCode.trim().length() == 0 || keystr == null
				|| keystr.length() == 0) { return null; }
		Key key = getDesKey(keystr.getBytes());
		Cipher decriptCipher = null;
		String decriptString = null;
		byte[] encriptByte = encriptCode.getBytes();

		byte[] decriptByte = new byte[encriptByte.length / 2];
		for (int i = 0; i < encriptByte.length; i += 2) {
			String strTmp = new String(encriptByte, i, 2);
			decriptByte[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}

		try {
			decriptCipher = Cipher.getInstance("DES");
			decriptCipher.init(Cipher.DECRYPT_MODE, key);

			byte[] outByte = decriptCipher.doFinal(decriptByte);
			decriptString = new String(outByte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return decriptString;
	}
}
