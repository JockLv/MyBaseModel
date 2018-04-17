package com.example.administrator.mybasemodel.base.view;

import android.app.ProgressDialog;
import android.text.TextUtils;

import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.mvp.view.impl.MvpActivity;


/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class BaseActivity<P extends MvpBasePresenter> extends MvpActivity<P> {

    @Override
    public void showProgressDialog(String s) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
