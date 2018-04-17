package com.example.administrator.mybasemodel.base.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.mybasemodel.utils.DensityUtil;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/12 13:59
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public abstract class BaseRetureCarPager {

    public View rootView;
    public int index;
    public Context mContext;
    public int windowHeight;
    public int windowWidth;

    public BaseRetureCarPager(String title, int index, Context context) {
        this.mContext = context;
        this.index = index;
        windowHeight = DensityUtil.getWindowHeight((Activity) mContext);
        windowWidth = DensityUtil.getWindowWidth((Activity) mContext);
        rootView = View.inflate(context, getLayoutId(), null);
        initView(rootView);
    }

    protected abstract int getLayoutId();

    public abstract void initView(View root);

    public abstract boolean beginAnimation(OnReturnCarAnimationListener listener);

    public abstract boolean exitAnimation(OnReturnCarAnimationListener listener);

    public abstract boolean killAnimation();


    public interface OnReturnCarAnimationListener {
        void onReturnCarAnimationStart();
        void onReturnCarAnimationEnd();
    }

    public interface OnPagerLoadListener {
        void onLoadComplete(int index);
    }
}
