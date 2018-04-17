package com.example.administrator.mybasemodel.http;

import com.example.administrator.mybasemodel.utils.LogUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 13:09
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class LogInterceptor implements Interceptor {
    private static final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        LogUtils.logd(TAG, "begin----------");
        LogUtils.logd(TAG, url.url().toString());
        Response  response = chain.proceed(request);
        LogUtils.logd(TAG, "end------------");
        return response;
    }
}
