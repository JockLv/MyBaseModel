package com.example.administrator.mybasemodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.utils.NotificationsUtils;
import com.example.administrator.mybasemodel.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/13 11:28
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class NotifyActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.btn_openNotify)
    Button btnOpenNotify;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notify;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        btnTest.setOnClickListener(this);
        btnOpenNotify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                ToastUtils.shortMessage("测试");
                break;
            case R.id.btn_openNotify:
                NotificationsUtils.toSetting();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean notificationEnabled = NotificationsUtils.isNotificationEnabled(this);
        tvMessage.setText(notificationEnabled ? "通知权限获取成功" : "通知权限获取失败");
    }
}
