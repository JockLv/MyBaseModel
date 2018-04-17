package com.example.administrator.mybasemodel.base.view;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.View;

import com.example.administrator.mybasemodel.base.CrashHandler;
import com.example.administrator.mybasemodel.utils.SharePreferenceUtils;

import java.util.ArrayList;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/10 15:26
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class AppManager extends MultiDexApplication {

    private ArrayList<Activity> list = new ArrayList<>();
    public static View appMainLayout = null;
    private Application.ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            list.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            list.remove(activity);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        if(!checkCurrentProcess(this)) {
            return;
        }
        registerActivityLifecycleCallbacks(callbacks);
        //捕获异常
//        CrashHandler.getInstance().init(this);
    }

    /**
     * 检查是否主线程
     * @param context
     * @return
     */
    private boolean checkCurrentProcess(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid && getPackageName().equalsIgnoreCase(appProcess.processName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(callbacks);
        for (Activity activity:list) {
            activity.finish();
        }
        list.clear();
        list = null;
        callbacks = null;
        super.onTerminate();
    }
    public ArrayList<Activity> getActivityList() {
        return list;
    }
}
