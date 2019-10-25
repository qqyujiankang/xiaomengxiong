package com.example.et.Ustlis.http;


import android.util.Log;

import com.example.et.Constant;
import com.example.et.Ustlis.http.api.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Dogal
 * @date
 */

public class HttpManager {
    private static HttpManager mHttpManager;

    private HttpManager() {
    }

    public static HttpManager getInstence() {
        if (null == mHttpManager) {
            mHttpManager = new HttpManager();
        }
        return mHttpManager;
    }

    /**
     * 获取指定的接口服务
     *
     * @return
     */
    public ApiService getApiService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)// 连接超时时间阈值
                .readTimeout(50, TimeUnit.SECONDS)// 数据获取时间阈值
                .writeTimeout(50, TimeUnit.SECONDS)// 写数据超时时间阈值
                .retryOnConnectionFailure(true)//错误重连
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .client(client)
                .build();
        ApiService api = retrofit.create(ApiService.class);
        return api;
    }
    /**
     * 获取指定的接口服务
     *  获取请求主体
     * @return
     */
    public ApiService getApiServiceBody(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        ApiService api = retrofit.create(ApiService.class);
        return api;
    }

    /**
     * 获取指定的接口服务
     *
     * @return
     */
    public ApiService getApiService(String baseUrl) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("zxdRetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(new AddQueryParameterInterceptor())
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .client(client)
                .build();
        ApiService api = retrofit.create(ApiService.class);
        return api;
    }

    public static Gson buildGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
                .registerTypeAdapter(int.class, new IntegerDefaultAdapter())
                .create();
    }

    private class AddQueryParameterInterceptor implements Interceptor {
        //post 添加签名和公共参数
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request.method().equals("POST")) {
                if (request.body() instanceof FormBody) {
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    FormBody formBody = (FormBody) request.body();
                    //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                    for (int i = 0; i < formBody.size(); i++) {
                        bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    }
                    formBody = bodyBuilder
//                            .addEncoded("noncestr", GongGongParams.NONCESTR)
//                            .addEncoded("version", GongGongParams.VERSION_NUM)
//                            .addEncoded("os", GongGongParams.OS)
//                            .addEncoded("timestamp", GongGongParams.CURRENT_TIME)
//                            .addEncoded("signature", GongGongParams.SIGNATURE)
                            .addEncoded("token",GongGongParams.token)
                            .build();
                    request = request.newBuilder().post(formBody).build();
                }
            }
            return chain.proceed(request);
        }
    }
}
