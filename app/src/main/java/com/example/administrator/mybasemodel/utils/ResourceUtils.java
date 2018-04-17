package com.example.administrator.mybasemodel.utils;

import com.example.administrator.mybasemodel.base.MyApp;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/10 14:15
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class ResourceUtils {


    public static String getStringById(int resId) {
        return MyApp.getInstance().getString(resId);
    }
}
