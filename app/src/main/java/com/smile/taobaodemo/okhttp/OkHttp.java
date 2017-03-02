package com.smile.taobaodemo.okhttp;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Smile Wei
 * @since 2016/8/2.
 */
public class OkHttp {
    private static OkHttpClient client;

    private static OkHttpClient init() {
        synchronized (OkHttp.class) {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build();
            }
        }
        return client;
    }

    private static OkHttpClient getInstance() {
        return client == null ? init() : client;
    }

    /**
     * get方法
     *
     * @param url      url
     * @param params   参数
     * @param callback 回调函数
     * @return call
     */
    public static Call get(Context context, String url, Map<String, String> params, OkCallback callback) {
        return get(context, url, params, null, callback);
    }

    /**
     * get方法
     *
     * @param url      url
     * @param params   参数
     * @param callback 回调函数
     * @return call
     */
    public static Call get(Context context, String url, Map<String, String> params, boolean isJumpLogin, OkCallback callback) {
        return get(context, url, params, null, isJumpLogin, callback);
    }


    /**
     * get方法
     *
     * @param url      url
     * @param params   参数
     * @param tag      标记位
     * @param callback 回调函数
     * @return call
     */
    public static Call get(Context context, String url, Map<String, String> params, Object tag, OkCallback callback) {
        callback.setContext(context);
        String endUrl = url + "?" + encodeParameters(context, params);
        Log.d("url", endUrl);
        Request.Builder builder = new Request.Builder().url(endUrl);
        if (tag != null) {
            builder.tag(tag);
        }
        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * get方法
     *
     * @param url      url
     * @param params   参数
     * @param tag      标记位
     * @param callback 回调函数
     * @return call
     */
    public static Call get(Context context, String url, Map<String, String> params, Object tag, boolean isJumpLogin, OkCallback callback) {
        callback.setContext(context);
        callback.setJumpLogin(isJumpLogin);
        String endUrl = url + "?" + encodeParameters(context, params);
        Log.d("url", endUrl);
        Request.Builder builder = new Request.Builder().url(endUrl);
        if (tag != null) {
            builder.tag(tag);
        }
        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }


    /**
     * post方法
     *
     * @param url      url
     * @param params   参数
     * @param callback 回调函数
     * @return call
     */
    public static Call post(Context context, String url, Map<String, String> params, OkCallback callback) {
        return post(context, url, params, null, callback);
    }

    /**
     * post方法
     *
     * @param url      url
     * @param params   参数
     * @param tag      标记位
     * @param callback 回调函数
     * @return call
     */
    public static Call post(Context context, String url, Map<String, String> params, Object tag, OkCallback callback) {
        callback.setContext(context);
        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                formBuilder.add(key, params.get(key));
            }
        }
        builder.post(formBuilder.build());

        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }


    /**
     * 上传文件方法
     *
     * @param url      url
     * @param params   参数
     * @param paths    文件路径
     * @param callback 回调函数
     * @return call
     */
    public static Call upload(Context context, String url, Map<String, String> params, List<String> paths, OkCallback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        if (paths != null && paths.size() > 0) {
            if (paths.size() == 1) {
                File file = new File(paths.get(0));
                builder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
            } else {
                for (int i = 0; i < paths.size(); i++) {
                    File file = new File(paths.get(i));
                    builder.addFormDataPart("file[" + i + "]", file.getName(), RequestBody.create(null, file));
                }
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }


    /**
     * 添加参数
     *
     * @param params 参数值
     * @return 参数字符串
     */
    private static String encodeParameters(Context context, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        StringBuilder sb = new StringBuilder("");
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(),
                        "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(),
                        "UTF-8"));
                sb.append('&');
            }
            return sb.toString();
        } catch (Exception e) {
        }
        return sb.toString();
    }

}
