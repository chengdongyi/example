package com.example.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author chengdongyi
 * @description: SHA 加密
 * @date 2020/7/27 14:45
 */
public class SHAUtil {

    private static String HMAC_MD5 = "HmacMD5";
    private static String HMAC_SHA1 = "HmacSHA1";
    private static String HMAC_SHA256 = "HmacSHA256";
    private static String HMAC_SHA512 = "HmacSHA512";

    public static String sha1(String data) {
        return DigestUtils.sha1Hex(data);
    }

    public static String sha256(String data) {
        return DigestUtils.sha1Hex(data);
    }

    public static String sha512(String data) {
        return DigestUtils.sha512Hex(data);
    }

    public static String hmacMd5(String data, String key) throws Exception {
        return hmac(data, key, HMAC_MD5);
    }

    public static String hmacSha1(String data, String key) throws Exception {
        return hmac(data, key, HMAC_SHA1);
    }

    public static String hmacSha256(String data, String key) throws Exception {
        return hmac(data, key, HMAC_SHA256);
    }

    public static String hmacSha512(String data, String key) throws Exception {
        return hmac(data, key, HMAC_SHA512);
    }

    public static String hmac(String data, String key, String keyType) throws Exception {

        String result = null;
        if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
            return result;
        }
        byte[] bytesKey = key.getBytes("utf-8");
        SecretKeySpec secretKey = new SecretKeySpec(bytesKey, keyType);
        Mac mac = Mac.getInstance(keyType);
        mac.init(secretKey);
        byte[] macData = mac.doFinal(data.getBytes());
        byte[] hex = new Hex().encode(macData);
        result = new String(hex, "ISO-8859-1");
        return result;
    }

    public static void main(String[] args) throws Exception {

        // sha 在线加密： http://encode.chahuo.com
        // hmac在线加密：https://1024tools.com/hmac
        System.out.println(SHAUtil.hmacMd5("123456", "123456"));
        System.out.println(SHAUtil.hmacSha1("123456", "123456"));
        System.out.println(SHAUtil.hmacSha256("123456", "123456"));
        System.out.println(SHAUtil.hmacSha512("123456", "123456"));
    }

}
