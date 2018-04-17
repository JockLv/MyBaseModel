package com.example.administrator.mybasemodel.http;

import com.example.administrator.mybasemodel.base.MyApp;
import com.example.administrator.mybasemodel.utils.Common;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 13:17
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class CustomNetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if(Common.isConnect(MyApp.getInstance())) {
            return chain.proceed(chain.request());
        }
        return null;
    }
}
