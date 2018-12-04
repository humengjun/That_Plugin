package com.hmj.demo.sharelibrary.plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BasePluginService extends Service implements IRemoteService {
    protected Service that;
    protected String dexPath;

    @Override
    public void setProxy(Service proxy, String dexPath) {
        this.that = proxy;
        this.dexPath = dexPath;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
