package com.example.administrator.mybasemodel.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.administrator.mybasemodel.base.view.AppManager;
import com.example.administrator.mybasemodel.utils.LogUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/10 13:55
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class MyApp extends AppManager {

    private static Context context;
    private int deviceType = UMConfigure.DEVICE_TYPE_PHONE;
    private String pushSecret ="261df20fddb69aad4b643616c6a47a2f";
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        UMConfigure.init(context, deviceType, pushSecret);
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtils.logd("ljw", "onSuccess："+deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {
                LogUtils.logd("ljw", "onFailure："+s+s1);
            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler(){
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                LogUtils.logd("ljw", "收到推送自定义消息:"+msg.getRaw().toString());
            }

            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                LogUtils.logd("ljw", "收到推送通知:"+uMessage.getRaw().toString());
                return super.getNotification(context, uMessage);
            }
        };
        mPushAgent.setMessageHandler(messageHandler);
        mPushAgent.setAlias("zhangsan", "云杉", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                LogUtils.logd("ljw", "别名："+message);
            }
        });
    }

    public static Context getInstance() {
        return context;
    }

    public String getStringById(int resId) {
        return context.getResources().getString(resId)+"";
    }

    /**
     * 程序进程销毁
     */
    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    private boolean isExistMainActivity(Class<?> activity){
        Intent intent = new Intent(this, activity);
        ComponentName cmpName = intent.resolveActivity(getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);  //获取从栈顶开始往下查找的10个activity
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz    class
     * @param bundle bundle数据
     */
    public void startMyActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    public static Activity getActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}
