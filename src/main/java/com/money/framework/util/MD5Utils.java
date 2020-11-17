package com.money.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5辅助类
 * @version V1.0
 * @author fengjc
 */
public class MD5Utils {

	private static final Logger LOG = LoggerFactory.getLogger(MD5Utils.class);

	private static ThreadLocal<MessageDigest> messageDigestHolder = new ThreadLocal<MessageDigest>();

	// 用来将字节转换成 16 进制表示的字符
	private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	static {
		try {
			MessageDigest message = MessageDigest.getInstance("MD5");
			messageDigestHolder.set(message);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Init java.security.MessageDigest fail", e);
		}
	}

	/**
	 * 计算MD5并转换为32字节明文字符串
	 * @param data 输入源头
	 * @return 32字节明文字符串
	 */
	public static String getMD5(String data) {
		try {
			MessageDigest message = messageDigestHolder.get();
			if (message == null) {
				message = MessageDigest.getInstance("MD5");
				messageDigestHolder.set(message);
			}
			message.update(data.getBytes("UTF-8"));
			byte[] b = message.digest();

			StringBuilder digestHex = new StringBuilder(32);
			for (int i = 0; i < 16; i++) {
				digestHex.append(byteHEX(b[i]));
			}

			return digestHex.toString();
		} catch (Exception e) {
			LOG.error("Exception in MD5 format [{}]:", data, e);
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(getMD5("123456"));
	}

	private static String byteHEX(byte ib) {
		char[] ob = new char[2];
		ob[0] = hexDigits[(ib >>> 4) & 0X0F];
		ob[1] = hexDigits[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
}
