package com.example.administrator.mybasemodel.mvp.view.impl;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.mybasemodel.R;
import com.example.administrator.mybasemodel.utils.ActivityCollectorUtlis;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.mvp.view.MvpView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/7/24.
 * 将我们的MVP架构集成到我们的Activity中
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public abstract class MvpActivity<P extends MvpBasePresenter> extends AppCompatActivity implements MvpView {

    private final String TAG = this.getClass().getSimpleName();
    //MVP架构是动态的
    private P presenter;
    private Unbinder bind;
    private View mContextView = null;
    private TextView toolbar_right_tv;
    private ImageView toolbar_right_iv;
    private TextView toolbar_title;
    private ImageView toolbar_return_iv;
    private ImageView toolbar_close_iv;
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断版本,如果[4.4,5.0)就设置状态栏和导航栏为透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //设置虚拟导航栏为透明
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        /**
         * 禁止横屏
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContextView = LayoutInflater.from(this)
                .inflate(getLayoutId(), null);
        mContextView.setBackgroundColor(this.getResources().getColor(R.color.all_bacg));
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(mContextView);
        bind = ButterKnife.bind(this);

        ActivityCollectorUtlis.addActivity(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_right_tv = (TextView) findViewById(R.id.toolbar_right_tv);
        toolbar_right_iv = (ImageView) findViewById(R.id.toolbar_right_iv);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_return_iv = (ImageView) findViewById(R.id.toolbar_return_iv);
        toolbar_close_iv = (ImageView) findViewById(R.id.toolbar_close_iv);
        if (getToolbar_Return_Iv() != null)
            getToolbar_Return_Iv().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (toolbar_title != null) {
            //getTitle()的值是activity的android:lable属性值
            toolbar_title.setText(getTitle());
            //设置默认的标题不显示

            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        presenter = bindPresenter();
        if(presenter != null) {
            presenter.attachView(this);
        }
        initData(savedInstanceState);
    }

    protected abstract int getLayoutId();

    public P getPresenter() {
        return presenter;
    }

    public void initData(Bundle savedInstanceState) {
    }

    public abstract P bindPresenter();

    /**
     * 获取头部标题的TextView
     */
    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 获取头部返回iv
     */
    public ImageView getToolbar_Return_Iv() {
        return toolbar_return_iv;
    }

    /**
     * 获取头部关闭iv
     */
    public ImageView getToolbar_Close_Iv() {
        return toolbar_close_iv;
    }

    /**
     * 获取头部标题的TextView
     */
    public TextView getToolbarTitle() {
        return toolbar_title;
    }

    /**
     * 获取头部标题的右面
     */
    public TextView getToolbar_right_Tv() {
        return toolbar_right_tv;
    }

    /**
     * 获取头部右面iv
     */
    public ImageView getToolbarRight_Iv() {
        return toolbar_right_iv;
    }

    @Override
    protected void onResume() {
        /**
         * 隐藏软键盘
         */
        hideIputKeyboard(this);
        super.onResume();
    }

    public void hideIputKeyboard(final Context context) {
        final Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InputMethodManager mInputKeyBoard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (activity.getCurrentFocus() != null) {
                    mInputKeyBoard.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                }
            }
        });
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics() );
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        if(presenter != null) {
            presenter.detachView();
        }
    }

    @SuppressLint("NewApi")
    public void setStatusOrTranslucentColor(View statusBar, int statusBgColor, View bottomNavigationBar, int translucentPrimaryColor){
        //判断版本,如果[4.4,5.0)就设置状态栏和导航栏为透明
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
            if(statusBar!=null){
                //1.先设置toolbar的高度
                ViewGroup.LayoutParams params = statusBar.getLayoutParams();
                int statusBarHeight = getStatusBarHeight(this);
                params.height += statusBarHeight;
                statusBar.setLayoutParams(params);
                //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
                statusBar.setPadding(
                        statusBar.getPaddingLeft(),
                        statusBar.getPaddingTop()+getStatusBarHeight(this),
                        statusBar.getPaddingRight(),
                        statusBar.getPaddingBottom());
                //设置顶部的颜色
                statusBar.setBackgroundColor(statusBgColor);
            }
            if(bottomNavigationBar!=null){
                //解决低版本4.4+的虚拟导航栏的
                if(hasNavigationBarShow(getWindowManager())){
                    ViewGroup.LayoutParams p = bottomNavigationBar.getLayoutParams();
                    p.height += getNavigationBarHeight(this);
                    bottomNavigationBar.setLayoutParams(p);
                    //设置底部导航栏的颜色
                    bottomNavigationBar.setBackgroundColor(translucentPrimaryColor);
                }
            }
        }else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(translucentPrimaryColor);
            getWindow().setStatusBarColor(translucentPrimaryColor);
        }else{
            //<4.4的，不做处理
        }
    }

    private int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(this, "navigation_bar_height");
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        return getSystemComponentDimen(this, "status_bar_height");
    }

    private static int getSystemComponentDimen(Context context, String dimenName){
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    private static boolean hasNavigationBarShow(WindowManager wm){
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取整个屏幕的高度
        display.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;
        //获取内容展示部分的高度
        outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int heightPixels2 = outMetrics.heightPixels;
        int widthPixels2 = outMetrics.widthPixels;
        int w = widthPixels-widthPixels2;
        int h = heightPixels-heightPixels2;
        System.out.println("~~~~~~~~~~~~~~~~h:"+h);
        return  w>0||h>0;//竖屏和横屏两种情况。
    }

}
