package com.example.util;

import com.example.constant.CharsetName;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

/**
 * @description: MD5 工具类
 * @author chengdongyi
 * @date 2019/12/2 14:09
 */
public class Md5Util {

    /**
     * 功能描述: 加密
     * @author chengdongyi
     * @date 2019/12/2 14:30
     * @param data
     * @return java.lang.String
     */
    public static String encode(String data) {

        if (StringUtils.isBlank(data)) {
            return "";
        }
        try {
            return DigestUtils.md5Hex(data.getBytes(CharsetName.UTF_8));
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }

    /**
     * 功能描述: 对文件进行加密
     * @author chengdongyi
     * @date 2019/12/2 14:30
     * @param file
     * @return java.lang.String
     */
    public static String encode(File file) throws Exception {
        return DigestUtils.md5Hex(new FileInputStream(file.getPath()));
    }

}
