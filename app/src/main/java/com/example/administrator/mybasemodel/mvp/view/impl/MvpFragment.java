package com.example.administrator.mybasemodel.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.mvp.view.MvpView;


/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class MvpFragment<P extends MvpBasePresenter> extends Fragment implements MvpView {

    private P presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = bindPresenter();
        if(presenter != null) {
            presenter.attachView(this);
        }
    }

    public abstract P bindPresenter();

    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null) {
            presenter.detachView();
        }
    }
}
