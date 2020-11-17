package com.money.framework.util;

import java.util.Base64;

public final class Base64Utils {

	final static Base64.Decoder decoder = Base64.getDecoder();
	final static Base64.Encoder encoder = Base64.getEncoder();

	private Base64Utils(){}

	public static String encode(byte[] binaryData) {
		return encoder.encodeToString(binaryData);
	}

	public static byte[] decode(String encoded) {
		return decoder.decode(encoded);
	}

}
