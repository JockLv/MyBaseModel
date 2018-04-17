package com.example.administrator.mybasemodel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.mybasemodel.base.MyApp;

/**
 * 读取本地文件的sharePreference
 *
 * @author by Sunqk
 * @date 2016/6/17
 */
public class SharePreferenceUtils {

    private static SharedPreferences mSharedPreferences;

    private SharePreferenceUtils() {
    }
    /**
     * 读取本地文件
     *
     */
    public static SharedPreferences getInstance(){
        if(mSharedPreferences == null) {
            synchronized (SharePreferenceUtils.class) {
                if(mSharedPreferences == null) {
                    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance());
                }
            }
        }
        return mSharedPreferences;
    }

    /**
     * 读取sharePreference文件中的值
     *
     * @param key   键值
     * @param value 默认值
     * @return String值
     */
    public String getSharePreference(String key, String value){
        return mSharedPreferences.getString(key, value);
    }

    /**
     * 读取sharePreference文件中的值
     *
     * @param key   键值
     * @param value 默认值
     * @return int值
     */
    public int getSharePreference(String key, int value){
        return mSharedPreferences.getInt(key, value);
    }

    /**
     * 读取sharePreference文件中的值
     *
     * @param key   键值
     * @param value 默认值
     * @return Boolean值
     */
    public boolean getSharePreference(String key, boolean value){
        return mSharedPreferences.getBoolean(key, value);
    }

    /**
     * 保存布尔值到缓存文件
     *
     * @param key   键值
     * @param value 保存的值
     */
    public void putSharePreference(String key, boolean value){
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 保存String到缓存文件
     *
     * @param key   键值
     * @param value 保存的值
     */
    public void putSharePreference(String key, String value){
        mSharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 保存String到缓存文件
     *
     * @param key   键值
     * @param value 保存的值
     */
    public void putSharePreference(String key, int value){
        mSharedPreferences.edit().putInt(key, value).apply();
    }

}
