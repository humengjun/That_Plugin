package com.hmj.demo.sharelibrary.plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public interface IRemoteService {

    void onCreate();

    int onStartCommand(Intent intent,int flags,int startId);

    void setProxy(Service proxy,String dexPath);

    void onDestroy();

    IBinder onBind(Intent intent);

    boolean onUnbind(Intent intent);
}
