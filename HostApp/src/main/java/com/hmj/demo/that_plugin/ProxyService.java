package com.hmj.demo.that_plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hmj.demo.sharelibrary.AppConstants;
import com.hmj.demo.sharelibrary.helper.PluginHelper;
import com.hmj.demo.sharelibrary.plugin.IRemoteService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;

public class ProxyService extends Service {
    private IRemoteService iRemoteService;

    public ProxyService() {
    }

    @Override
    public void onCreate() {
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return iRemoteService.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        iRemoteService.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) return super.onStartCommand(intent, flags, startId);

        int index = intent.getIntExtra(AppConstants.EXTRA_PLUGIN_INDEX, -1);

        try {
            loadPluginService(index);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        //先执行onCreate操作
        iRemoteService.onCreate();
        return iRemoteService.onStartCommand(intent, flags, startId);
    }

    private void loadPluginService(int index) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        if (index < 0) return;
        DexClassLoader dexClassLoader = PluginHelper.plugins.get(index).getClassLoader();
        String className = PluginHelper.plugins.get(index).getPluginInfo().packageName + ".PluginService";
        String dexPath = PluginHelper.plugins.get(index).getPluginPath();

        Class<?> loadClass = dexClassLoader.loadClass(className);
        Constructor<?> loadConstructor = loadClass.getConstructor(new Class[]{});
        Object instance = loadConstructor.newInstance(new Object[]{});
        iRemoteService = (IRemoteService) instance;
        iRemoteService.setProxy(this, dexPath);
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (intent == null) return null;
        int index = intent.getIntExtra(AppConstants.EXTRA_PLUGIN_INDEX, -1);

        try {
            loadPluginService(index);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        //先执行onCreate操作
        iRemoteService.onCreate();
        return iRemoteService.onBind(intent);
    }
}
