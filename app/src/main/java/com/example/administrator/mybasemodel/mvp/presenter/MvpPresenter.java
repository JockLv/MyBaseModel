package com.example.administrator.mybasemodel.mvp.presenter;


import com.example.administrator.mybasemodel.mvp.view.MvpView;

/**
 * Created by Administrator on 2017/7/24.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
