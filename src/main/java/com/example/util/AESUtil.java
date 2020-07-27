package com.example.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @description: AES对称加密
 * @author chengdongyi
 * @date 2019/11/28 14:47
 */
public class AESUtil {


    public static void main(String[] args) throws Exception {
        System.out.println(encryptAES( "12345678","123"));
    }

        /**
         * 加密
         *
         * @param content
         * @param key
         * @return
         */
        public static String encryptAES(String content, String key) {
            try {
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                byte[] result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
                return parseByte2HexStr(result);
            } catch (Exception e) {
//                log.error(String.format("加密失败:，content：%s, key: %s", content, key));
            }
            return content;
        }
        /**
         * 将二进制转换成16进制
         *
         * @param buf
         * @return
         */
        public static String parseByte2HexStr(byte[] buf) {
            StringBuilder sb = new StringBuilder();
            for (byte b : buf) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        }

}
