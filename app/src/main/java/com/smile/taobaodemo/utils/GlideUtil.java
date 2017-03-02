package com.smile.taobaodemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smile.taobaodemo.R;
import com.smile.taobaodemo.widget.glide.GlideCircleTransform;
import com.smile.taobaodemo.widget.glide.GlideRoundTransform;

/**
 * @author Smile Wei
 * @since 2016/6/7.
 */
public class GlideUtil {

    private static GlideCircleTransform circleTransform;
    private static GlideRoundTransform roundTransform;

    /**
     * 初始化transform
     *
     * @param context context
     * @return transform
     */
    private static GlideCircleTransform transform(Context context) {
        if (circleTransform == null) {
            circleTransform = new GlideCircleTransform(context);
        }
        return circleTransform;
    }

    /**
     * 初始化transform
     *
     * @param context context
     * @return transform
     */
    private static GlideRoundTransform transform(Context context, int dp) {
        if (roundTransform == null) {
            roundTransform = new GlideRoundTransform(context, dp);
        }
        return roundTransform;
    }

    /**
     * 加载圆角缩略图
     *
     * @param activity  activity
     * @param url       头像url
     * @param imageView 头像view
     * @param dp        圆角dp
     */
    public static void load(Context context, Activity activity, String url, ImageView imageView, int dp) {
        Glide.with(activity).load(url == null ? "" : url).error(R.color.bg_main_color)
                .placeholder(R.color.bg_main_color)
                .transform(transform(context, dp))
                .into(imageView);
    }

    /**
     * 加载头像url
     *
     * @param context   context
     * @param activity  activity
     * @param url       头像url
     * @param imageView 头像view
     */
    public static void load(Context context, Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url == null ? "" : url).error(R.color.bg_main_color)
                .placeholder(R.color.bg_main_color)
                .transform(transform(context))
                .into(imageView);
    }

    /**
     * 加载缩略图
     *
     * @param activity  activity
     * @param url       头像url
     * @param imageView 头像view
     */
    public static void load(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url == null ? "" : url).error(R.color.bg_main_color)
                .placeholder(R.color.bg_main_color)
                .into(imageView);
    }

    /**
     * 加载缩略图
     *
     * @param activity  activity
     * @param url       头像url
     * @param imageView 头像view
     */
    public static void loadCategory(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url == null ? "" : url).error(Color.WHITE)
                .placeholder(Color.WHITE)
                .into(imageView);
    }
}
