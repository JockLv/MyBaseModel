package com.example.administrator.mybasemodel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.administrator.mybasemodel.http.dataInterface.IHttpCallBack;
import com.example.administrator.mybasemodel.model.entity.UserEntity;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/10 14:30
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class Common {
    //自定义日志输出级别 低于此级别的日志将不会打印
    static final int LOG_LEVEL = Log.VERBOSE;

    public static final int TAG_USERLOGIN = 01;
    public static final String TAG_USERLOGIN_URL = "Logon/UserLogin";


    /**
     * 判断网络连接状态是否正常
     *
     * @param context 上下文环境
     * @return true 正常 false 断开
     */
    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.logi("TAP", "检测网络出现问题");
            Log.v("error", e.toString());
        }
        return false;
    }
}
