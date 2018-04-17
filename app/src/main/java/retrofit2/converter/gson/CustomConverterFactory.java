package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/11 12:27
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public final class CustomConverterFactory extends Converter.Factory {

    private final Gson gson;

    public static CustomConverterFactory create() {
        return create(new Gson());
    }

    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static CustomConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new CustomConverterFactory(gson);
    }

    private CustomConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        // attention here!
        return new CustomResponseConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }



}
