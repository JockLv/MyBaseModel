package com.example.administrator.mybasemodel;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.mybasemodel.anim.AnimationOneActivity;
import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/13 13:45
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class AOActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_makeCustomAnimation)
    Button btnMakeCustomAnimation;
    @BindView(R.id.btn_makeScaleUpAnimation)
    Button btnMakeScaleUpAnimation;
    @BindView(R.id.btn_makeThumbnailScaleUpAnimation)
    Button btnMakeThumbnailScaleUpAnimation;
    @BindView(R.id.btn_makeClipRevealAnimation)
    Button btnMakeClipRevealAnimation;
    @BindView(R.id.btn_makeSceneTransitionAnimation)
    Button btnMakeSceneTransitionAnimation;
    @BindView(R.id.trans_img)
    ImageView transImg;


    private ActivityOptionsCompat compat;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aoa;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        btnMakeCustomAnimation.setOnClickListener(this);
        btnMakeScaleUpAnimation.setOnClickListener(this);
        btnMakeThumbnailScaleUpAnimation.setOnClickListener(this);
        btnMakeClipRevealAnimation.setOnClickListener(this);
        btnMakeSceneTransitionAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_makeCustomAnimation:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    compat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_activity_in, R.anim.anim_activity_out);
                    ActivityCompat.startActivity(this, new Intent(this, AnimationOneActivity.class), compat.toBundle());
                }else {
                    startActivity(new Intent(this, AnimationOneActivity.class));
                }
                break;
            case R.id.btn_makeScaleUpAnimation:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    compat = ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                    ActivityCompat.startActivity(this, new Intent(this, AnimationOneActivity.class), compat.toBundle());
                }else {
                    startActivity(new Intent(this, AnimationOneActivity.class));
                }
                break;
            case R.id.btn_makeThumbnailScaleUpAnimation:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    compat = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(v, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 0, 0);
                    ActivityCompat.startActivity(this, new Intent(this, AnimationOneActivity.class), compat.toBundle());
                }else {
                    startActivity(new Intent(this, AnimationOneActivity.class));
                }
                break;
            case R.id.btn_makeClipRevealAnimation:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    compat = ActivityOptionsCompat.makeClipRevealAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                    ActivityCompat.startActivity(this, new Intent(this, AnimationOneActivity.class), compat.toBundle());
                }else {
                    startActivity(new Intent(this, AnimationOneActivity.class));
                }
                break;
            case R.id.btn_makeSceneTransitionAnimation:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, transImg, getString(R.string.app_name));
                    ActivityCompat.startActivity(this, new Intent(this, AnimationOneActivity.class), compat.toBundle());
                }else {
                    startActivity(new Intent(this, AnimationOneActivity.class));
                }
                break;
        }
    }
}
