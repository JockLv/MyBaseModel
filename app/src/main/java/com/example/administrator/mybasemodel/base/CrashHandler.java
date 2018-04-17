package com.example.administrator.mybasemodel.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mybasemodel.R;
import com.example.administrator.mybasemodel.utils.FileUtils;
import com.example.administrator.mybasemodel.utils.LogUtils;
import com.example.administrator.mybasemodel.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/10 13:58
 * 描    述： 统一处理捕获程序异常
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = CrashHandler.class.getSimpleName();
    private static final int RESTART_TIME = 1000;
    //最大日志文件数
    private static final int MAX_LOG_FILE = 3;

    //采用默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler instance;
    private Map<String, String> infos = new HashMap<>();
    private Context mContext;
    private CrashHandler(){}

    public static CrashHandler getInstance() {
        if(instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     * @param context
     */
    public void init(Context context) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t, e);
        }else {
            ((MyApp)mContext).onTerminate();
            try {
                Thread.sleep(RESTART_TIME);
                //退出程序
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 自定义错误处理，收集错误信息
     * @param e
     * @return
     */
    private boolean handleException(Throwable e) {
        if(e ==  null) {
            return false;
        }
        //处理错误
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    ToastUtils.shortMessage(R.string.app_error_message);
                    Looper.loop();
                }
            }).start();
        {
            e.printStackTrace();
            return true;
        }
    }

    private void restartApplication(){
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        //Intent intent = new Intent(mContext, GuidePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //mContext.startActivity(intent);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + RESTART_TIME, pendingIntent);
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx){
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured when collect crash info", e);
        }
        Field[] fields2 = Build.VERSION.class.getDeclaredFields();
        try {
            for (Field field : fields2) {
                field.setAccessible(true);
                infos.put("VERSION-" + field.getName(), field.get(null).toString());
            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured when collect crash info", e);
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCatchInfo2File(Throwable ex){
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        deleteLogFile();
        File logFile = FileUtils.getLogFile(mContext);
        try {
            FileOutputStream fos = new FileOutputStream(logFile);
            fos.write(sb.toString().getBytes());
            fos.close();
            return logFile.getName();
        } catch (IOException e) {
            Log.e(TAG, "an error occured while writing file...", e);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 删除多余日志
     */
    private void deleteLogFile(){
        File file = new File(FileUtils.getLogDir(mContext));
        if (file.exists() && file.listFiles().length >= MAX_LOG_FILE) {
            File[] files = file.listFiles();
            File del = files[0];
            for (int i = 1; i < files.length; i++) {
                if (files[i].lastModified() < del.lastModified()) {
                    del = files[i];
                }
            }
            FileUtils.deleteFile(del);
        }
    }

    /**
     * 将捕获的导致崩溃的错误信息发送给开发人员
     * <p/>
     * 目前只将log日志保存在sdcard 和输出到LogCat中，并未发送给后台。
     */
    private void sendCrashLog2PM(String fileName){
        if (!new File(fileName).exists()) {
            LogUtils.loge("日志文件不存在！");
            //Toast.makeText(mContext, "日志文件不存在！", Toast.LENGTH_SHORT).show();
            return;
        }
        FileInputStream fis = null;
        BufferedReader reader = null;
        String s = null;
        try {
            fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis, "GBK"));
            while (true) {
                s = reader.readLine();
                if (s == null) {
                    break;
                }
                //由于目前尚未确定以何种方式发送，所以先打出log日志。
                Log.i("info", s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {   // 关闭流
            try {
                reader.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
