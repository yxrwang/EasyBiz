package com.arvis.easybiz.android;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.arvis.easybiz.android.comm.EBHttpClient;

public class EasyBizApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {

        super.onCreate();

        EBHttpClient.init();
    }
}
