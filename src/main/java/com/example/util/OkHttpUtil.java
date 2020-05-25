package com.example.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description: OkHttp工具类
 * @author chengdongyi
 * @date 2019/11/19 10:06
 */
@Slf4j
public class OkHttpUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType XML = MediaType.parse("text/xml; charset=utf-8");
    private static final OkHttpClient client = OkHttpClientUtil.getInstance().getClientInstance();

    /**
     * Get请求(同步方式)
     */
    public static String get(String url, String logUniqueFlag) {

        log.error("{}, 请求url: {}", logUniqueFlag, url);
        try {
            Request.Builder builder = new Request.Builder();
            Request request = builder.get().url(url).build();
            Response response =  client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                log.error("{}, 响应报文: {}", logUniqueFlag, data);
                return data;
            } else {
                log.error("{},响应信息: {}",logUniqueFlag, response.toString());
            }
        } catch (SocketTimeoutException e) {
            log.error("URL链接超时, URL: {}", url);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("URL链接异常, URL: {}", url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Post请求(同步方式)
     */
    public static String post(String url, Map<String, String> params, String logUniqueFlag) {

        log.error("{}, 请求url: {}", logUniqueFlag, url);
        log.error("{}, 请求报文: {}", logUniqueFlag, com.alibaba.fastjson.JSON.toJSONString(params));
        try {
            RequestBody body = setRequestBody(params);
            Request.Builder builder = new Request.Builder();
            Request request = builder.post(body).url(url).build();
            Response response =  client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                log.error("{}, 响应报文: {}", logUniqueFlag, data);
                return data;
            } else {
                log.error("{},响应信息: {}", logUniqueFlag,response.toString());
            }
        } catch (SocketTimeoutException e) {
            log.error("URL链接超时, URL: {}", url);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("URL链接异常, URL: {}", url);
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Post请求JSON格式参数(同步方式)
     */
    public static String postJson(String url, String json, String logUniqueFlag) {

        log.error("{}, 请求url: {}", logUniqueFlag, url);
        log.error("{}, 请求报文: {}", logUniqueFlag, json);
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
//            		.newBuilder()
//            		.callTimeout(30, TimeUnit.SECONDS)
//            		.readTimeout(30, TimeUnit.SECONDS)
//            		.build()
            if (response.isSuccessful()) {
                String data = response.body().string();
                log.error("{}, 响应报文: {}", logUniqueFlag, data);
                return data;
            } else {
                log.error("{}响应信息: {}",logUniqueFlag,response.toString());
            }
        } catch (SocketTimeoutException e) {
			log.error("{},URL链接超时, URL: {},超时信息：{}", logUniqueFlag, url);
            e.printStackTrace();
        } catch (Exception e) {
			log.error("{},URL链接异常, URL: {},异常信息：{}", logUniqueFlag, url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Post请求XML格式参数(同步方式)
     */
    public static String postXML(String url, String xml, String logUniqueFlag) {

        log.error("{}, 请求url: {}", logUniqueFlag, url);
        log.error("{}, 请求报文: {}", logUniqueFlag, xml);
        try {
            RequestBody body = RequestBody.create(XML, xml);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                log.error("{}, 响应报文: {}", logUniqueFlag, data);
                return data;
            } else {
                log.error("{},响应信息: {}",logUniqueFlag, response.toString());
            }
        } catch (SocketTimeoutException e) {
            log.error("URL链接超时, URL: {}", url);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("URL链接异常, URL: {}", url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Post请求JSON格式参数(同步方式)添加头部信息
     */
    public static String postAddHeadJson(String url, String json,Map<String, String> head, String logUniqueFlag) {

        log.error("{}, 请求url: {}", logUniqueFlag, url);
        log.error("{}, 请求报文: {}", logUniqueFlag, json);
        String data=null;
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request.Builder builder = new Request.Builder();
            
            Set<String> keys = head.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				builder.header(key, head.get(key));
			}
            Request request = builder.url(url).post(body).build();
            log.error("{}, 请求头: {}", logUniqueFlag, request.headers().toString());
            Response response = client.newCall(request).execute();
            data = response.body().string();
            log.error("{}, 响应头: {}", logUniqueFlag, response.headers().toString());
            if (response.isSuccessful()) {
                log.error("{}, 响应报文: {}", logUniqueFlag, data);
            } else {
                log.error("{}, 响应信息: {}",logUniqueFlag,data);
            }
        } catch (SocketTimeoutException e) {
            log.error("URL链接超时, URL: {}", url);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("URL链接异常, URL: {}", url);
            e.printStackTrace();
        }
        return data;
    }
//    /**
//     * Get请求(异步方式)
//     */
//    public static void getAsync(String url, final NetCall netCall) {
//
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.get().url(url).build();
//        Call call = client.newCall(request);
//        // 执行 Call
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                netCall.failed(call, e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                netCall.success(call, response);
//            }
//        });
//    }
//
//    /**
//     * Post请求(异步方式)
//     */
//    public static void postDataAsync(String url, Map<String, String> params, final NetCall netCall) {
//
//        RequestBody body = setRequestBody(params);
//        buildRequest(url, netCall, body);
//
//    }
//
//
//    /**
//     * Post请求JSON格式参数(异步方式)
//     */
//    public static void postJsonAsync(String url, String json, final NetCall netCall) {
//
//        RequestBody body = RequestBody.create(json, JSON);
//        // 构造 Request
//        buildRequest(url, netCall, body);
//
//    }
//
//    /**
//     * 构造 Request 发起异步请求
//     */
//    private static void buildRequest(String url, NetCall netCall, RequestBody body) {
//        Request.Builder requestBuilder = new Request.Builder();
//        Request request = requestBuilder.post(body).url(url).build();
//        // 将 Request 封装为 Call
//        Call call = client.newCall(request);
//        // 执行 Call
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                netCall.failed(call, e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                netCall.success(call, response);
//            }
//        });
//    }

    /**
     * post的请求参数，构造RequestBody
     */
    private static RequestBody setRequestBody(Map<String, String> params) {
        RequestBody body = null;
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (params != null) {
            Iterator<String> iterator = params.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formBodyBuilder.add(key, params.get(key));
            }
        }
        return formBodyBuilder.build();
    }

}