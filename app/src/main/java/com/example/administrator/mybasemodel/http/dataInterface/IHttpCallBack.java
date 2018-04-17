package com.example.administrator.mybasemodel.http.dataInterface;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 11:25
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public interface IHttpCallBack<T> {

    void onSuccess(T t, String tag);

    void onFailure(Throwable e, String tag);
}
