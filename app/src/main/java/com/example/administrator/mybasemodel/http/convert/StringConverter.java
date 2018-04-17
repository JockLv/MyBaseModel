package com.example.administrator.mybasemodel.http.convert;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 14:03
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class StringConverter implements Converter<ResponseBody, String> {

    public static final StringConverter INSTANCE = new StringConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
