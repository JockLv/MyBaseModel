package com.example.administrator.mybasemodel.model.presenter;

import android.content.Context;

import com.example.administrator.mybasemodel.MainActivity;
import com.example.administrator.mybasemodel.base.presenter.BasePresenter;
import com.example.administrator.mybasemodel.http.dataInterface.IDataCallBack;
import com.example.administrator.mybasemodel.http.dataInterface.IHttpCallBack;
import com.example.administrator.mybasemodel.model.entity.UserEntity;
import com.example.administrator.mybasemodel.model.model.MainModel;
import com.example.administrator.mybasemodel.utils.Common;
import com.example.administrator.mybasemodel.utils.Constant;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 09:39
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class MainPresenter extends BasePresenter<MainModel> {
    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    protected MainModel bindModel() {
        return new MainModel(getContext());
    }

    public void onLogin(String mobile, String code, String wxRegFlag, String inviterld, String cityCode, final IDataCallBack callBack) {
        getModel().onLogin(mobile, code, wxRegFlag, inviterld, cityCode, Common.TAG_USERLOGIN_URL, new IHttpCallBack<UserEntity>() {
            @Override
            public void onSuccess(UserEntity userEntity, String tag) {
                callBack.onSuccess(userEntity, tag);
            }

            @Override
            public void onFailure(Throwable e, String tag) {
                callBack.onFailure(e, tag);
            }
        });
    }

    public void onLogin1(String mobile, String code, String wxRegFlag, String inviterld, String cityCode, final IDataCallBack callBack) {
        getModel().onLogin1(mobile, code, wxRegFlag, inviterld, cityCode, Common.TAG_USERLOGIN_URL, new IHttpCallBack<String>() {
            @Override
            public void onSuccess(String string, String tag) {
                callBack.onSuccess(string, tag);
            }

            @Override
            public void onFailure(Throwable e, String tag) {
                callBack.onFailure(e, tag);
            }
        });
    }
}
