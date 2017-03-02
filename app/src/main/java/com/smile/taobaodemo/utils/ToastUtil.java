package com.smile.taobaodemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author Smile Wei
 * @since 2013-12-13
 */
public class ToastUtil {

    /**
     * 显示短时间的Toast
     *
     * @param context context
     * @param msg     提示内容
     */
    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示短时间的Toast
     *
     * @param context context
     * @param resId   提示内容id
     */
    public static void showShortToast(Context context, int resId) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

}
