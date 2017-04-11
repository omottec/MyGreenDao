package com.github.omottec.greendao.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by qinbingbing on 11/04/2017.
 */

public class MyGreenDaoApp extends Application {
    private static Context mAppContext;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mAppContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext() {
        return mAppContext;
    }
}
