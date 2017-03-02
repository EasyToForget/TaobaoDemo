package com.smile.taobaodemo.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Smile Wei
 * @since 2016/8/2.
 */
public abstract class OkCallback implements Callback {
    private static final String SUCCESS_OK = "0000";
    public static final String ERROR_0014 = "500014";
    private static final String ERROR_0011 = "500011";
    private static final String ERROR_1000 = "1000";
    private static final String ERROR_1001 = "1001";
    private static final String ERROR = "connect error";
    private static final String STATE = "state";
    private static final String MSG = "msg";
    private static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(ERROR_1000, e.getMessage());
            }
        });
    }


    private Context context;
    private boolean isJumpLogin = true;

    public void setContext(Context context) {
        this.context = context;
    }

    void setJumpLogin(boolean jumpLogin) {
        isJumpLogin = jumpLogin;
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        try {
            final String res = response.body().string();
            final JSONObject jsonObject = new JSONObject(res);
            final String status = jsonObject.getString(STATE);

            if (status.equals(SUCCESS_OK)) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onResponse(res);
                    }
                });
                return;
            }
            //如果state=0011，则是token失效，需要重新跳转到登录界面
            final String msg = jsonObject.getString(MSG);
            if (status.equals(ERROR_0011) && isJumpLogin) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onFailure(status, msg);
                    }
                });

                return;
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(status, msg);
                }
            });
        } catch (JSONException e) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //onFailure(ERROR_1001, ERROR);
                }
            });
            e.printStackTrace();
        }
    }

    public abstract void onResponse(String response);

    public abstract void onFailure(String state, String msg);

}
