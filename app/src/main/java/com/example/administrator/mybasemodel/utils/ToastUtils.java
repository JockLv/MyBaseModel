package com.example.administrator.mybasemodel.utils;

import android.widget.Toast;

import com.example.administrator.mybasemodel.base.MyApp;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/10 14:10
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class ToastUtils {

    public static void shortMessage(String message) {
        Toast.makeText(MyApp.getInstance(), message+"", Toast.LENGTH_SHORT).show();
    }

    public static void shortMessage(int resId) {
        Toast.makeText(MyApp.getInstance(), ResourceUtils.getStringById(resId)+"", Toast.LENGTH_SHORT).show();
    }

    public static void longMessage(String message) {
        Toast.makeText(MyApp.getInstance(), message+"", Toast.LENGTH_LONG).show();
    }

    public static void longMessage(int resId) {
        Toast.makeText(MyApp.getInstance(), ResourceUtils.getStringById(resId)+"", Toast.LENGTH_LONG).show();
    }

}
