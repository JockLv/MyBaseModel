package com.example.administrator.mybasemodel.base.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.mvp.view.impl.MvpFragment;
import com.example.administrator.mybasemodel.utils.LogUtils;


/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class BaseFragment<P extends MvpBasePresenter> extends MvpFragment<P> {
    private final String TAG = this.getClass().getSimpleName();
    //我们的Fragment需要缓存视图
    public View viewContent;

    protected boolean bIsViewCreated;
    protected boolean bIsDataLoaded;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(viewContent == null) {
            viewContent = inflater.inflate(getContentView(), container, false);
            initContentView(viewContent);
        }
        //判断Fragment对应的Activity是否存在
        ViewGroup parent = (ViewGroup) viewContent.getParent();
        if(parent != null) {
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(viewContent);
        }
        bIsViewCreated = true;
        return viewContent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getUserVisibleHint() && !bIsDataLoaded) {
            initData();
            bIsDataLoaded = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && bIsViewCreated && !bIsDataLoaded) {
            initData();
            bIsDataLoaded = true;
        }
    }
    @Override
    public void showProgressDialog(String s) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bIsViewCreated = false;
        bIsDataLoaded = false;
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }
    protected abstract void initContentView(View viewContent);
    protected abstract int getContentView();
}
