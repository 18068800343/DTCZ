package com.ldxx.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util {
    /**
     * 编码UTF_8
     */
    public static final String CODING_UTF_8 = "UTF-8";

    /**
     * 编码GB2312
     */
    public static final String CODING_GB2312 = "GB2312";

    /**
     *
     * @方法名：encode
     * @方法描述【方法功能描述】base64编码并指定文本编码
     * @param s 待编码字符串
     * @param charset 字符串编码
     * @return 编码后字符串
     */
    public static String encode(String s, String charset) throws UnsupportedEncodingException {
        return new String(Base64.encodeBase64(s.getBytes(charset)));
    }

    /**
     *
     * @方法名：encode
     * @方法描述【方法功能描述】base64编码
     * @param s 待编码字符串
     * @return 编码后字符串
     */
    public static String encode(String s) {
        return new String(Base64.encodeBase64(s.getBytes()));
    }

    /**
     *
     * @方法名：decode
     * @方法描述【方法功能描述】base64解码
     * @param s 待解码字符串
     * @return 解码后字符串
     */
    public static String decode(String s) {
        return new String(Base64.decodeBase64(s.getBytes()));
    }

    public static void main(String[] args) {
        try {
            System.out.println(Base64Util.encode("cc", Base64Util.CODING_UTF_8));
            System.out.println(Base64Util.decode("Y2M="));
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
