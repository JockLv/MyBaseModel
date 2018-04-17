package com.example.administrator.mybasemodel.base.model;

import android.content.Context;

import com.example.administrator.mybasemodel.mvp.model.MvpModel;


/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class BaseModel implements MvpModel {
    private Context context;

    public BaseModel(Context context) {
        this.context = context;
    }

}
