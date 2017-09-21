package com.ordinov.ersp.user.util;

import java.io.UnsupportedEncodingException;

import org.springframework.util.Base64Utils;

public final class MD5DataUtils {
	
	private static final String ENCODING = "utf-8";
	
	private MD5DataUtils(){}
	
	public static String encoderByMd5(final String data) throws UnsupportedEncodingException {
		return Base64Utils.encodeToString(data.getBytes(ENCODING));
	}

}
