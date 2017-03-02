package com.smile.taobaodemo.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;


/**
 * AppContext Application
 *
 * @author Smile Wei
 * @since 2014-04-04
 */
public class TomorrowApplication extends MultiDexApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }


}