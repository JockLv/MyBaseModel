package com.example.administrator.mybasemodel.http;

import com.example.administrator.mybasemodel.http.api.ApiService;
import com.example.administrator.mybasemodel.model.entity.UserEntity;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 09:21
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class HttpData extends RetrofitUtils {

    protected static final ApiService apiService = getRetrofit().create(ApiService.class);

    public void onLogin1(String mobile, String code, String wxRegFlag, String inviterld, String cityCode, Observer<String> observer) {
        Observable observable = apiService.getLogin1(mobile, code, wxRegFlag, inviterld, cityCode);
        setSubscribe(observable, observer);
    }


    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void onLogin(String mobile, String code, String wxRegFlag, String inviterld, String cityCode, Observer<UserEntity> observer) {
        Observable observable = apiService.getLogin(mobile, code, wxRegFlag, inviterld, cityCode);
        setSubscribe(observable, observer);
    }

    /**
     * 插入观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
