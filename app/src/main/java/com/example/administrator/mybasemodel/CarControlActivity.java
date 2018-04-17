package com.example.administrator.mybasemodel;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/16 18:35
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class CarControlActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_openDoor)
    TextView tvOpenDoor;
    @BindView(R.id.tv_closeDoor)
    TextView tvCloseDoor;
    @BindView(R.id.tv_voice)
    TextView tvVoice;
    @BindView(R.id.tv_doubleLight)
    TextView tvDoubleLight;
    @BindView(R.id.ll_car_layout)
    LinearLayout llCarLayout;
    @BindView(R.id.iv_voice_left)
    ImageView ivVoiceLeft;
    @BindView(R.id.iv_voice_right)
    ImageView ivVoiceRight;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_control;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvOpenDoor.setOnClickListener(this);
        tvCloseDoor.setOnClickListener(this);
        tvVoice.setOnClickListener(this);
        tvDoubleLight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_openDoor:
                //开锁
                llCarLayout.setBackgroundResource(R.mipmap.icon_control_car_unlock);
                ivVoiceLeft.setVisibility(View.INVISIBLE);
                ivVoiceRight.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_closeDoor:
                //关锁
                llCarLayout.setBackgroundResource(R.mipmap.icon_control_car_lock);
                ivVoiceLeft.setVisibility(View.INVISIBLE);
                ivVoiceRight.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_voice:
                //鸣笛
                llCarLayout.setBackgroundResource(R.mipmap.icon_control_car_normal);
                ivVoiceLeft.setVisibility(View.VISIBLE);
                ivVoiceRight.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_doubleLight:
                //双闪
                llCarLayout.setBackgroundResource(R.mipmap.icon_control_car_normal);
                ivVoiceLeft.setVisibility(View.INVISIBLE);
                ivVoiceRight.setVisibility(View.INVISIBLE);
                llCarLayout.setBackgroundResource(R.drawable.car_control_double_light_animation);
                AnimationDrawable animationDrawable = (AnimationDrawable) llCarLayout.getBackground();
                animationDrawable.start();
                break;
        }
    }

}
