package com.example.administrator.mybasemodel.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author po.
 * @date 2016/6/3.
 * 全部钱 显示两位小数
 */
public class FormatUtils {
    /* 格式化单价 */
    public static String formatPrice(double d){
        return new DecimalFormat("0.00").format(d);
    }

    /* 格式化价格 */
    public static String formatMoney(double d){
        return new DecimalFormat("###,###,###,##0.00").format(d);
    }

    /* 格式化文件大小 */
    public static String formatFileSize(double d){
        return new DecimalFormat("0.##").format(d);
    }

    /* 格式化距离 */
    public static String formatDistance(double d){
        return new DecimalFormat("##0.0").format(d);
    }

    /* 格式化小时 分钟 */
    public static String formatHours(double d){
        return new DecimalFormat("0").format(d);
    }

    /*传入字符串保留，两位小数*/
    public static String formatMoney(String num){
        Double billAmount = Double.parseDouble(num);
        return formatMoney(billAmount);
    }

    public static Date ConverToDate(String strDate) throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.parse(strDate);
    }

    /**
     * 转成yyyy-MM-dd HH:mm格式
     */
    public static String ConverToString(String date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return df.format(ConverToDate(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
