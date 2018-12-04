package com.hmj.demo.sharelibrary.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BasePluginReceiver extends BroadcastReceiver implements IRemoteReceiver {
    protected BroadcastReceiver that;

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @Override
    public void setProxy(BroadcastReceiver proxy) {
        that = proxy;
    }
}
