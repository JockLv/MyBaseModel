package com.example.administrator.mybasemodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.http.dataInterface.IDataCallBack;
import com.example.administrator.mybasemodel.model.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener, IDataCallBack {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_login_string)
    Button btnLoginString;
    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.btn_notify)
    Button btnNotify;
    @BindView(R.id.btn_activity_options)
    Button btnActivityOptions;
    @BindView(R.id.btn_activity_default)
    Button btnActivityDefault;
    @BindView(R.id.btn_car_control)
    Button btnCarControl;

    private String Mobile = "17512087168";
    private String Code = "2627";
    private String WxRegFlag = "2";
    private String Inviterld = "";
    private String cityCode = "";
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter bindPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        btnLogin.setOnClickListener(this);
        btnLoginString.setOnClickListener(this);
        btnJump.setOnClickListener(this);
        btnNotify.setOnClickListener(this);
        btnActivityOptions.setOnClickListener(this);
        btnActivityDefault.setOnClickListener(this);
        btnCarControl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                showProgressDialog("hello");
                getPresenter().onLogin(Mobile, Code, WxRegFlag, Inviterld, cityCode, this);
                break;
            case R.id.btn_login_string:
                getPresenter().onLogin1(Mobile, Code, WxRegFlag, Inviterld, cityCode, this);
                break;
            case R.id.btn_jump:
                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_notify:
                intent = new Intent(MainActivity.this, NotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_activity_options:
                intent = new Intent(MainActivity.this, AOActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_activity_default:
                intent = new Intent(MainActivity.this, AODefaultActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_car_control:
                intent = new Intent(MainActivity.this, CarControlActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onFailure(Throwable e, String tag) {
        hideProgressDialog();
    }

    @Override
    public void onSuccess(Object object, String tag) {
        hideProgressDialog();
    }

}
