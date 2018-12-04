package com.hmj.demo.plugin1;

import android.content.Intent;
import android.os.IBinder;

import com.hmj.demo.sharelibrary.helper.ToastUtils;
import com.hmj.demo.sharelibrary.plugin.BasePluginService;

public class PluginService extends BasePluginService {
    public PluginService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.shortToast(that,"Plugin1 Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ToastUtils.shortToast(that,"Plugin1 Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
