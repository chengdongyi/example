package com.example.util;

import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * @description: OkHttpClient客户端
 * @author chengdongyi
 * @date 2019/11/19 10:06
 */
public class OkHttpClientUtil {

    private static final int READ_TIMEOUT = 15;
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;
    private static final Object local_lock = new Object();
    private static OkHttpClientUtil instance;
    private OkHttpClient clientInstance;

    private OkHttpClientUtil() {
        try {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            // 1. 设置读取超时时间
            clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
            // 2. 设置连接超时时间
            clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
            // 3. 设置写入超时时间
            clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
            //支持HTTPS请求，跳过证书验证
            TrustManager[] trustAllCerts = buildTrustManagers();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            clientInstance = clientBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建线程安全的okHttpClient单例
     */
    public static OkHttpClientUtil getInstance() {
        if (instance == null) {
            synchronized (local_lock) {
                if (instance == null) {
                    instance = new OkHttpClientUtil();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getClientInstance() {
        return this.clientInstance;
    }

    /**
     * 用于信任所有证书
     */
    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }

}
