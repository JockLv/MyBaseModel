package com.example.administrator.mybasemodel.utils;

import android.util.Log;

/**
 * @author po.
 * @date 2016/6/4.
 * <p/>
 * 项目中的日志 尽量用logx(String msg)   TAG = "DZKJ"
 * 注意log级别 正常显示的用info 例如 请求成功后的日志打印  重要逻辑开始结束的日志
 * 调试的用debug   例如 请求开始时 逻辑跟踪
 * 局部调试的用verbose 例如 请求进行中进度跟踪 局部的逻辑调试打印
 * 自己调试的日志  用logx(String tag, String msg) TAG 自定义
 * 提交时 注意删掉自定义日志
 */
public class LogUtils{

    //一行log最大4K字节 在此做截断  保证完整输出
    private static final int MAX_WORDS_INLINE = 1024 * 3;
    private static String TAG = "QSD";

    private static String getStackTraceElementinfo(StackTraceElement element){
        return "*" + element.getClassName() + "(" + element.getLineNumber() + "):" + element.getMethodName() + "** ";
    }

    /**
     * 获取当前堆栈信息
     *
     * @return
     */
    public static StackTraceElement getCurrentStackTraceElement(){
        return Thread.currentThread().getStackTrace()[3];
    }

    /**
     * 获取调用者堆栈信息
     *
     * @return
     */
    public static StackTraceElement getCallerStackTraceElement(){
        return Thread.currentThread().getStackTrace()[4];
    }

    public static String getStackTrace(){
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StringBuilder stringBuilder = new StringBuilder("");
        for (StackTraceElement element : elements) {
            String ClassName = element.getClassName();
            //屏蔽内部方法
            if (ClassName.startsWith("cn.aichuxing.car.android")) {
                stringBuilder.append(element.getClassName()).append("(").append(element.getLineNumber()).append("):")
                        .append(element.getMethodName()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static void logv(String tag, String msg){
        log(Log.VERBOSE, tag, msg);
    }

    public static void logd(String tag, String msg){
        log(Log.DEBUG, tag, msg);
    }

    public static void logi(String tag, String msg){
        log(Log.INFO, tag, msg);
    }

    public static void logw(String tag, String msg){
        log(Log.WARN, tag, msg);
    }

    public static void loge(String tag, String msg){
        log(Log.ERROR, tag, msg);
    }

    public static void logv(String msg){
        logv(TAG, msg);
    }

    public static void logd(String msg){
        logd(TAG, msg);
    }

    public static void logi(String msg){
        logi(TAG, msg);
    }

    public static void logw(String msg){
        logw(TAG, msg);
    }

    public static void loge(String msg){
        loge(TAG, msg);
    }

    public static void log(int level, String msg){
        log(level, TAG, msg);
    }

    public static void log(int level, String tag, String msg){
        if (level >= Common.LOG_LEVEL) {
            int l = msg.length() / MAX_WORDS_INLINE;
            for (int i = 0; i <= l; i++) {
                int start = MAX_WORDS_INLINE * i;
                int end = Math.min(MAX_WORDS_INLINE * (i + 1), msg.length());
                String s = msg.substring(start, end);
                Log.println(level, tag, s);
            }
        }
    }

}
