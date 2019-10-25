package com.example.et.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import androidx.annotation.NonNull;

import com.example.et.util.callback.OkCallback;
import com.example.et.util.constant.CacheConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpUtils {
    private static int connectTimeOut = 10;
    private static int readTimeOut = 20;
    private static int writeTimeOut = 20;
    private static int retryCount = 0;

    private static String userAgent;
    static String errorCode = "error_code";

    private static OkHttpClient.Builder builder;

    public static OkHttpClient client() {
        return getInstance().build();
    }

    private static OkHttpClient.Builder init() {
        synchronized (OkHttpUtils.class) {
            if (builder == null) {
                builder = new OkHttpClient.Builder();

                if (retryCount > 0) {
                    builder.addInterceptor(new RetryInterceptor(retryCount));
                }

                if (!TextUtils.isEmpty(userAgent)) {
                    builder.addInterceptor(new HeadInterceptor());
                }

                //if (BuildConfig.DEBUG) {
                if (false) {
                    builder.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts());
                    builder.hostnameVerifier(new TrustAllHostnameVerifier());
                }
            }
        }
        return builder;
    }

    private static OkHttpClient.Builder getInstance() {
        return builder == null ? init() : builder;
    }

    /**
     * get方法
     *
     * @param url      url
     * @param params   参数
     * @param callback 回调函数
     * @return call
     */
    public static Call get(String url, Map<String, Object> params, OkCallback callback) {
        return get(url, params, null, callback);
    }

    /**
     * get方法
     *
     * @param url      url
     * @param params   参数
     * @param tag      标记位
     * @param callback 回调函数
     * @return call
     */
    public static Call get(String url, Map<String, Object> params, Object tag, OkCallback callback) {
        String endUrl = url + "?" + encodeParameters(params);

        Request.Builder builder = new Request.Builder().url(endUrl);
        if (tag != null) {
            builder.tag(tag);
        }

        Request request = builder.build();
        Call call = getInstance()
                .readTimeout(readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .build()
                .newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * post方法
     *
     * @param url      url
     * @param params   参数
     * @param callback 回调函数
     * @return call
     */
    public static Call post(String url, Map<String, Object> params, OkCallback callback) {
        return post(0, url, params, null, callback);
    }

    /**
     * post方法
     *
     * @param url        url
     * @param jsonObject 参数
     * @param callback   回调函数
     * @return call
     */

    public static Call post(String url, JSONObject jsonObject, OkCallback callback) {
        return post(0, url, jsonObject, null, callback);
    }

    /**
     * post方法
     *
     * @param url      url
     * @param params   参数
     * @param callback 回调函数
     * @return call
     */
    public static Call post(int timeOut, String url, Map<String, Object> params, OkCallback callback) {
        return post(timeOut, url, params, null, callback);
    }

    /**
     * post方法
     *
     * @param url      url
     * @param params   参数
     * @param tag      标记位
     * @param callback 回调函数
     * @return call
     */
    public static Call post(int timeOut, String url, @NonNull Map<String, Object> params, Object tag, OkCallback callback) {


        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        if (CacheUtils.getInstance().getString(CacheConstants.TOKEN) != null) {
            builder.addHeader("Cookie", CacheUtils.getInstance().getString(CacheConstants.TOKEN));
        }
        // builder.addHeader("Authorization", "Bearer b572e475-4cfc-484f-b22d-0b19f161ec97");//
        builder.addHeader("Authorization", "Basic cGFzc3dvcmQ6MTIzNDU2");//

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();

        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                Object object = params.get(key);
                if (!(object instanceof File)) {

                    multipartBuilder.addFormDataPart(key, object + "");
                } else {
                    File file = (File) object;
                    if (file.exists()) {

                        multipartBuilder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
                    }
                }
            }
        }

        builder.post(multipartBuilder.build());
        //  builder.addHeader("Connection", "close");

        Request request = builder.build();
        Call call = getInstance()
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build()
                .newCall(request);
        call.enqueue(callback);//176 9629 7728
        //182 9209 9424


        return call;
    }

    /**
     * post方法
     *
     * @param url        url
     * @param jsonObject 参数
     * @param tag        标记位
     * @param callback   回调函数
     * @return call
     */


    public static Call post(int timeOut, String url, @NonNull JSONObject jsonObject, Object tag, OkCallback callback) {

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        if (CacheUtils.getInstance().getString(CacheConstants.TOKEN) != null) {
            builder.addHeader("token", CacheUtils.getInstance().getString(CacheConstants.TOKEN));
             LogUtils.i("token============"+CacheUtils.getInstance().getString(CacheConstants.TOKEN).toString());
        }

        //  MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        FormBody.Builder multipartBuilder = new FormBody.Builder();  ///这里
        JSONArray jsonArray = jsonObject.names();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    String paramsKey = jsonArray.getString(i);
                    Object object = jsonObject.get(paramsKey);
                    if (object instanceof File) {
                        File file = (File) object;
                        if (file.exists()) {
                           // multipartBuilder.addFormDataPart(paramsKey, file.getName(), RequestBody.create(null, file));
                        }
                    } else {
                        LogUtils.i("我是你巴巴变============" + paramsKey + "======" + object);
//                          MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
//                        multipartBuilder.setType(JSON);
                        multipartBuilder.add(paramsKey, object + "");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        builder.post(multipartBuilder.build());


        Request request = builder.build();
        LogUtils.i("我是你巴巴变============" + request);
        Call call = getInstance()
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build()
                .newCall(request);
        call.enqueue(callback);
        return call;
    }


    /**
     * 得到body对象
     */
    private static RequestBody getRequestBody(String mJsonStr) {
        /**
         * 首先判断mJsonStr是否为空，由于mJsonStr与mParamsMap不可能同时存在，所以先判断mJsonStr
         */
      /*  json : application/json
        xml : application/xml
        png : image/png
        jpg : image/jpeg
        gif : imge/gif*/
        if (!TextUtils.isEmpty(mJsonStr)) {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
            return RequestBody.create(JSON, mJsonStr);//json数据，
            /*MediaType XML = MediaType.parse("application/xml; charset=utf-8");//数据类型为xml格式，
            return RequestBody.create(XML, mXmlStr);//json数据，*/
        }

        /**
         * post,put,delete都需要body，但也都有body等于空的情况，此时也应该有body对象，但body中的内容为空
         */
      /*  FormBody.Builder formBody = new FormBody.Builder();
        if (mParamsMap != null) {
            for (String key : mParamsMap.keySet()) {
                formBody.add(key, mParamsMap.get(key)+"");
            }
        }
        return formBody.build();*/
        return null;
    }

    /**
     * 添加参数
     *
     * @param params 参数值
     * @return 参数字符串
     */
    private static String encodeParameters(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }

        StringBuilder sb = new StringBuilder("");
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue() == null ? "" : String.valueOf(entry.getValue()), "UTF-8"));
                sb.append('&');
            }
            return sb.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(
                    "Encoding not supported: " + "UTF-8", uee);
        }
    }

    private static class RetryInterceptor implements Interceptor {

        private int maxRetry;       //最大重试次数
        private int retryNum = 0;   //假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）


        RetryInterceptor(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                response = chain.proceed(request);
            }
            return response;
        }
    }

    private static class TrustAllCerts implements X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @SuppressLint("BadHostnameVerifier")
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 添加头部User Agent
     */
    private static class HeadInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .header("User-Agent", userAgent) // 标明发送本次请求的客户端
                    .build();
            return chain.proceed(request);
        }
    }


    public static void setConnectTimeOut(int connectTimeOut) {
        OkHttpUtils.connectTimeOut = connectTimeOut;
    }

    public static void setReadTimeOut(int readTimeOut) {
        OkHttpUtils.readTimeOut = readTimeOut;
    }

    public static void setWriteTimeOut(int writeTimeOut) {
        OkHttpUtils.writeTimeOut = writeTimeOut;
    }

    public static void setRetryCount(int retryCount) {
        OkHttpUtils.retryCount = retryCount;
    }

    public static void setUserAgent(String userAgent) {
        if (!TextUtils.isEmpty(userAgent)) {
            OkHttpUtils.userAgent = userAgent;
        }
    }

    public static void setErrorCode(String errorCode) {
        if (!TextUtils.isEmpty(errorCode)) {
            OkHttpUtils.errorCode = errorCode;
        }
    }

}
