package com.example.administrator.volleydongnao.http.download;

import android.util.Log;

import com.example.administrator.volleydongnao.http.JsonHttpService;
import com.example.administrator.volleydongnao.http.interfaces.IHttpListener;
import com.example.administrator.volleydongnao.http.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/16 0016.
 * 1
 * DownItemInfo
 * File
 */

public class FileDownHttpService implements IHttpService {
    private static final String TAG = "dongnao";
    /**
     * 即将添加到请求头的信息
     */
    private Map<String ,String> headerMap= Collections.synchronizedMap(new HashMap<String ,String>());
    /**
     * 含有请求处理的 接口
     */
    private IHttpListener httpListener;

    private HttpClient httpClient=new DefaultHttpClient();
    private HttpGet httpPost;
    private String url;

    private byte[] requestDate;
    /**
     * httpClient获取网络的回调
     */
    private  HttpRespnceHandler httpRespnceHandler=new HttpRespnceHandler();

    @Override
    public void setUrl(String url) {
        this.url=url;
    }

    @Override
    public void excute() {
        httpPost=new HttpGet(url);
        constrcutHeader();
//        ByteArrayEntity byteArrayEntity=new ByteArrayEntity(requestDate);
//        httpPost.setEntity(byteArrayEntity);
        try {
            httpClient.execute(httpPost,httpRespnceHandler);
        } catch (IOException e) {
            httpListener.onFail();
        }
    }

    private void constrcutHeader() {
        Iterator iterator=headerMap.keySet().iterator();
        while (iterator.hasNext())
        {
            String key= (String) iterator.next();
            String value=headerMap.get(key);
            Log.i(TAG," 请求头信息  "+key+"  value "+value);
            httpPost.addHeader(key,value);
        }
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener=httpListener;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestDate=requestData;
    }

    @Override
    public void pause() {

    }

    @Override
    public Map<String, String> getHttpHeadMap() {
        return null;
    }

    @Override
    public boolean cancle() {
        return false;
    }

    @Override
    public boolean isCancle() {
        return false;
    }

    @Override
    public boolean isPause() {
        return false;
    }

    private class HttpRespnceHandler extends BasicResponseHandler
    {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            //响应吗
            int code=response.getStatusLine().getStatusCode();
            if(code==200)
            {
                httpListener.onSuccess(response.getEntity());
            }else
            {
                httpListener.onFail();
            }


            return null;
        }
    }
}
