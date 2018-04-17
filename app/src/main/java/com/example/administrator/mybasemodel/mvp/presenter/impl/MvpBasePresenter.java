package com.example.administrator.mybasemodel.mvp.presenter.impl;


import com.example.administrator.mybasemodel.mvp.presenter.MvpPresenter;
import com.example.administrator.mybasemodel.mvp.view.MvpView;

/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if(view != null) {
            view = null;
        }
    }
}
