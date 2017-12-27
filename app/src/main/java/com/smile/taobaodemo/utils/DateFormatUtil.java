package com.smile.taobaodemo.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {

    /**
     * 常用时间格式
     */
    public final static String FORMAT_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_yyyyMMdd_HHmm = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_yyyyMMdd = "yyyy-MM-dd";
    public final static String FORMAT_yyyy_S_MM_S_dd_HHmm = "yyyy/MM/dd HH:mm";
    public final static String FORMAT_yyyy_S_MM_dd = "yyyy/MM/dd";
    public final static String FORMAT_MMdd_HHmm = "MM-dd HH:mm";
    public final static String FORMAT_yyyy = "yyyy";
    public final static String FORMAT_M = "M";
    public final static String FORMAT_dd = "dd";
    public final static String FORMAT_HHmmss = "HH:mm:ss";
    public final static String FORMAT_HHMM = "HH:mm";
    public final static String FORMAT_MMddHHmmss = "MM-dd HH:mm:ss";

    /**
     * 时间戳单位（毫秒/秒）大于该值则为毫秒否则为秒
     */
    private final static long SECOND_TIME = 9999999999L;

    /**
     * 根据时间戳获得指定格式日期
     *
     * @param format 时间格式
     * @param time   时间戳 秒
     * @return 日期
     */
    public static String formatDate(final String format, final long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(!TextUtils.isEmpty(format) ? format : FORMAT_yyyyMMdd, Locale.getDefault());
        return sdf.format(new Date(SECOND_TIME < time ? time : time * 1000));
    }

    /**
     * 时间字符串转换成long字符
     *
     * @param time 时间字符串，如2015/01/01
     */
    public static long formatDate(String format, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }


    public static String getNowDateStr(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyyMMdd,
                Locale.getDefault());
        Date dt = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DAY_OF_MONTH, days);

        return sdf.format(calendar.getTime());
    }
}
