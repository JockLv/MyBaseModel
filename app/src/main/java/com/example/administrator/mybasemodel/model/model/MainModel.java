package com.example.administrator.mybasemodel.model.model;

import android.content.Context;

import com.example.administrator.mybasemodel.base.model.BaseModel;
import com.example.administrator.mybasemodel.http.HttpData;
import com.example.administrator.mybasemodel.http.dataInterface.IHttpCallBack;
import com.example.administrator.mybasemodel.model.entity.UserEntity;
import com.example.administrator.mybasemodel.utils.LogUtils;

import rx.Observer;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 09:39
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class MainModel extends BaseModel {
    public MainModel(Context context) {
        super(context);
    }

    public void onLogin(String mobile, String code, String wxRegFlag, String inviterld, String cityCode, final String tag, final IHttpCallBack<UserEntity> httpCallBack) {
        HttpData.getInstance().onLogin(mobile, code, wxRegFlag, inviterld, cityCode, new Observer<UserEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtils.logd(e.getMessage());
                httpCallBack.onFailure(e, tag);
            }

            @Override
            public void onNext(UserEntity userEntity) {
                LogUtils.logd(userEntity.toString());
                httpCallBack.onSuccess(userEntity, tag);
            }
        });
    }

    public void onLogin1(String mobile, String code, String wxRegFlag, String inviterld, String cityCode, final String tag, final IHttpCallBack<String> httpCallBack) {
        HttpData.getInstance().onLogin1(mobile, code, wxRegFlag, inviterld, cityCode, new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtils.logd(e.getMessage());
                httpCallBack.onFailure(e, tag);
            }

            @Override
            public void onNext(String string) {
                LogUtils.logd(string.toString());
                httpCallBack.onSuccess(string, tag);
            }
        });
    }
}
