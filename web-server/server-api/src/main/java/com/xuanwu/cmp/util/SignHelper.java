package com.xuanwu.cmp.util;

import com.google.common.base.Joiner;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

/**
 * Encode or decode the string, used for the request sign
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-05
 * @Version 1.0.0
 */
public class SignHelper {

    /**
     * log for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(SignHelper.class);

    /**
     * do hmacSha1Hex
     *
     * @param privateKey privateKey
     * @param input input string
     * @param algorithm algorithm
     * @return string
     * @throws Exception
     */
    public static String hmacSha1Hex(String privateKey, String input, String algorithm) throws Exception {
        byte[] keyBytes = privateKey.getBytes(Constants.CHARSET_UTF8);
        Key key = new SecretKeySpec(keyBytes, algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(key);
        return byteArrayToHex(mac.doFinal(input.getBytes()));
    }

    /**
     * convert byte array to hex string
     *
     * @param bytes byte array
     * @return hex string
     */
    public static String byteArrayToHex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * encode the string with Base64
     *
     * @param sourceStr sourceStr
     * @return string
     */
    public static String encodeBase64(String sourceStr) {
        byte[] byteArray = null;
        String result = null;
        try {
            byteArray = sourceStr.getBytes(Constants.CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            logger.error("");
        }
        if (byteArray != null) {
            result = new BASE64Encoder().encode(byteArray);
        }
        return result;
    }

    /**
     * decode the string with Base64
     *
     * @param sourceStr
     * @return string
     */
    public static String decodeBase64(String sourceStr) {
        String result = null;
        if (sourceStr != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] byteArray = decoder.decodeBuffer(sourceStr);
                result = new String(byteArray, Constants.CHARSET_UTF8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
