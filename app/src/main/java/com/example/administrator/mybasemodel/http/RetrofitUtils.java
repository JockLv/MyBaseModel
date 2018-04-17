package com.example.administrator.mybasemodel.http;

import com.example.administrator.mybasemodel.http.convert.StringConverterFactory;
import com.example.administrator.mybasemodel.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 09:14
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public abstract class RetrofitUtils {

    private static final long READTIME = 5000;
    private static final long CONNECTTIME = 5000;
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    protected static Retrofit getRetrofit() {
        if(null == mRetrofit) {
            if(null == mOkHttpClient) {
                mOkHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new LogInterceptor())
//                        .addNetworkInterceptor(new CustomNetworkInterceptor())
                        .readTimeout(READTIME, TimeUnit.MILLISECONDS)
                        .connectTimeout(CONNECTTIME, TimeUnit.MILLISECONDS).build();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.API_SERVER + "/")
                    .addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
