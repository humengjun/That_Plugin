package com.hmj.demo.that_plugin;

import android.app.Application;
import android.content.Context;

import com.hmj.demo.sharelibrary.helper.PluginHelper;

public class HostApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginHelper.initPlugins(this);
    }
}
