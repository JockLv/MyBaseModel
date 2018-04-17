package com.example.administrator.mybasemodel.base.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.administrator.mybasemodel.base.model.BaseModel;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class BasePresenter<M extends BaseModel> extends MvpBasePresenter {

    private Context context;
    private Gson gson;
    private M model;
    private Handler mHandler;

    public BasePresenter(Context context) {
        this.context = context;
        this.gson = new Gson();
        this.model = bindModel();
        this.mHandler = new Handler(Looper.myLooper());
    }

    protected abstract M bindModel();

    public M getModel() {
        return model;
    }

    public Context getContext() {
        return context;
    }

    public Gson getGson() {
        return gson;
    }

    public Handler getHandler() {
        return mHandler;
    }
}
