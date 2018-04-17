package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 12:32
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


class CustomResponseConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private static final String CODE = "code";
    private static final String DATA = "data";

    CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String originalBody = value.string();
            // 先 AES 解密
//            String body = AESCryptUtils.decrypt(originalBody, AppConstant.getAESKey());
            // 再获取 code
            JSONObject json = new JSONObject(originalBody);
            int code = json.optInt(CODE);
            // 当 code 不为 200 时，设置 data 为 null，这样转化就不会出错了
            if (code != 200) {
                Map<String, String> map = gson.fromJson(originalBody, new TypeToken<Map<String, String>>() {
                }.getType());
                map.put(DATA, null);
                originalBody = gson.toJson(map);
            }
            return adapter.fromJson(originalBody);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}