package com.example.util;

import com.example.constant.CharsetName;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @description: Base64 工具类
 * @author chengdongyi
 * @date 2019/12/2 10:45
 */
public class Base64Util {

    /**
     * 功能描述: Base64 编码
     * @param data
     * @return java.lang.String
     * @author chengdongyi
     * @date 2019/12/2 11:07
     */
    public static String encode(String data) {

        if (StringUtils.isBlank(data)) {
            return "";
        }
        Base64 base64 = new Base64();
        try {
            byte[] dataByte = data.getBytes(CharsetName.UTF_8);
            return base64.encodeToString(dataByte);
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }

    /**
     * 功能描述: Base64 解码
     * @param data
     * @return java.lang.String
     * @author chengdongyi
     * @date 2019/12/2 11:09
     */
    public static String decode(String data) {

        if (StringUtils.isBlank(data)) {
            return "";
        }
        Base64 base64 = new Base64();

        try {
            return new String(base64.decode(data), CharsetName.UTF_8);
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }

    /**
     * 功能描述: Base64 编码
     * @param data
     * @return byte[]
     * @author chengdongyi
     * @date 2019/12/2 13:53
     */
    public static byte[] encode(byte[] data) {
        Base64 base64 = new Base64();
        return base64.encode(data);
    }

    /**
     * 功能描述: Base64 解码
     * @param data
     * @return java.lang.String
     * @author chengdongyi
     * @date 2019/12/2 11:09
     */
    public static byte[] decode(byte[] data) {
        Base64 base64 = new Base64();
        return base64.decode(data);
    }

}
