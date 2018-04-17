package com.example.administrator.volleydongnao;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.volleydongnao.http.Volley;
import com.example.administrator.volleydongnao.http.download.DownFileManager;
import com.example.administrator.volleydongnao.http.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {
    public  static  final String url="http://v.juhe.cn/toutiao/index?type=top&key=29da5e8be9ba88b932394b7261092f71";
    private static final String TAG = "dongnao";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.content);

    }

    /**
     *  1
     *  2
     * @param view
     */
    public  void login(View view)
    {
        Volley.sendRequest(null, url,NewsPager.class, new IDataListener<NewsPager>() {
            @Override
            public void onSuccess(NewsPager loginRespense) {
                Log.i(TAG,loginRespense.toString());
            }

            @Override
            public void onFail() {
                Log.i(TAG,"获取失败");
            }
        });


//        DownFileManager downFileService=new DownFileManager();
//        downFileService.down("http://gdown.baidu.com/data/wisegame/8be18d2c0dc8a9c9/WPSOffice_177.apk");

    }
}
