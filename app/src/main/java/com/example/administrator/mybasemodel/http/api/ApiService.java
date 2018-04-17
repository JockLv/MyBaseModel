package com.example.administrator.mybasemodel.http.api;


import com.example.administrator.mybasemodel.model.entity.UserEntity;
import com.example.administrator.mybasemodel.utils.Common;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 09:35
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public interface ApiService {

    @POST(Common.TAG_USERLOGIN_URL)
    @FormUrlEncoded
    Observable<UserEntity> getLogin(@Field("Mobile") String mobile,
                                    @Field("Code") String code,
                                    @Field("WxRegFlag") String wxRegFlag,
                                    @Field("Inviterld") String inviterld,
                                    @Field("cityCode") String cityCode);

    @POST(Common.TAG_USERLOGIN_URL)
    @FormUrlEncoded
    Observable<String> getLogin1(@Field("Mobile") String mobile,
                                    @Field("Code") String code,
                                    @Field("WxRegFlag") String wxRegFlag,
                                    @Field("Inviterld") String inviterld,
                                    @Field("cityCode") String cityCode);
}
