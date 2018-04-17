package com.example.administrator.mybasemodel.http.dataInterface;

import com.example.administrator.mybasemodel.model.entity.UserEntity;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 11:15
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public interface IDataCallBack {
    void onFailure(Throwable e, String tag);

    void onSuccess(Object object, String tag);
}
