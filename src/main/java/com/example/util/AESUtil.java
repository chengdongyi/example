package com.example.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @description: AES对称加密
 * @author chengdongyi
 * @date 2019/11/28 14:47
 */
public class AESUtil {


    /**
     * 功能描述: 初始化 AES Cipher
     * @author chengdongyi
     * @date 2020/5/19 13:06
     * @param key 秘钥
     * @param cipherMode
     * @return javax.crypto.Cipher
     */
    public static Cipher initAESCipher(String key, int cipherMode) {
        //创建Key gen
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(codeFormat, "AES");
            cipher = Cipher.getInstance("AES");
            //初始化
            cipher.init(cipherMode, secretKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return cipher;
    }


    /**
     * 功能描述: 对文件进行 AES 加密
     * @author chengdongyi
     * @date 2020/5/19 11:15
     * @param sourceFile 源文件
     * @param path 加密后生成的文件路径
     * @param suffix 机密后的文件后缀
     * @param key 秘钥
     * @return java.io.File
     */
    public static File encryptFile(File sourceFile, String path, String suffix, String key, String logUniqueFlag) {

        File encrypFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            String encrypFileName = sourceFile.getName() + suffix;
            encrypFile = new File(path + encrypFileName);
            outputStream = new FileOutputStream(encrypFile);
            Cipher cipher = initAESCipher(key, Cipher.ENCRYPT_MODE);
            //以加密流写入文件
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            cipherInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encrypFile;
    }


    /**
     * 功能描述: AES方式解密文件
     * @author chengdongyi
     * @date 2020/5/19 13:05
     * @param sourceFile 源文件（待解密的文件）
     * @param path 解密后的文件路径
     * @param suffix 需要去掉的文件后缀
     * @param key 秘钥
     * @return java.io.File
     */
    public static File decryptFile(File sourceFile, String path, String suffix, String key) {
        File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String decryptFileame = null;
            if (sourceFile.getName().endsWith(suffix)) {
                decryptFileame = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf(suffix));
            }
            decryptFile = new File(path + decryptFileame);
            Cipher cipher = initAESCipher(key, Cipher.DECRYPT_MODE);
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(decryptFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, r);
            }
            cipherOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return decryptFile;
    }

    public static void main(String[] args) {

        // 秘钥
        String key = "a0f79a9714d63f505a6e8fb3aef2e073";

        String path = "D:\\test\\iop\\20200509\\";
        // 文件后缀
        String suffix = ".encrypt";

        // 加密
        File file = new File(path + "a_10000_20200608_JZYY-RWK-50101_00.verf");
        encryptFile(file, path, suffix, key, "");

        // 解密
//        File file = new File(path + "a_10000_20200608_JZYY-RWK-50101_00.verf.encrypt");
//        decryptFile(file, path, suffix, key);

    }

}
