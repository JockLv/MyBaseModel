package com.example.administrator.mybasemodel.base.view;

import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.mvp.view.impl.MvpActivity;
import com.example.administrator.mybasemodel.mvp.view.impl.MvpPictureActivity;


/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class BasePictureActivity<P extends MvpBasePresenter> extends MvpPictureActivity<P> {

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
