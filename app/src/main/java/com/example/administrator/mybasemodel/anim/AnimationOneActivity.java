package com.example.administrator.mybasemodel.anim;

import android.os.Bundle;

import com.example.administrator.mybasemodel.R;
import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/13 14:03
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class AnimationOneActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation_one;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }
}
