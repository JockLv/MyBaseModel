package com.example.administrator.mybasemodel;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mybasemodel.anim.AnimationThreeActivity;
import com.example.administrator.mybasemodel.anim.AnimationTwoActivity;
import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/13 15:13
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class AODefaultActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_jump1)
    Button btnJump1;
    @BindView(R.id.btn_jump2)
    Button btnJump2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aoa_default;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        btnJump1.setOnClickListener(this);
        btnJump2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 我们这里没有用ActivityCompat转场
                    startActivity(new Intent(this, AnimationTwoActivity.class),
                            ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                } else {
                    startActivity(new Intent(this, AnimationTwoActivity.class));
                }
                break;
            case R.id.btn_jump2:
                startActivity(new Intent(this, AnimationThreeActivity.class));
                break;
        }
    }

}
