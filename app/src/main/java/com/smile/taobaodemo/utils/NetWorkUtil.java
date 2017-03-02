package com.smile.taobaodemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检测网络工具类
 * 包含检测网络的相关方法
 *
 * @author Smile Wei
 * @since 2014.4.4
 */
public class NetWorkUtil {
    /**
     * 检测当的网络（WIFI、4G/3G/2G）状态
     *
     * @param context context
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return !(networkInfo == null || !networkInfo.isConnected()) && networkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    /**
     * 检测当的网络（4G/3G/2G）状态
     *
     * @param context context
     * @return boolean
     */
    public static boolean isGprsAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) == null) {
            return false;
        }
        NetworkInfo.State state = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).getState();
        return NetworkInfo.State.CONNECTED == state;
    }

    /**
     * 检测当的网络（WIFI）状态
     *
     * @param context context
     * @return boolean
     */
    public static boolean isWifiAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        if (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) == null) {
            return false;
        }
        NetworkInfo.State state = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState();
        return NetworkInfo.State.CONNECTED == state;
    }
}
