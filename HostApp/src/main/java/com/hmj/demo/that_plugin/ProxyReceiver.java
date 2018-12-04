package com.hmj.demo.that_plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hmj.demo.sharelibrary.AppConstants;
import com.hmj.demo.sharelibrary.helper.PluginHelper;
import com.hmj.demo.sharelibrary.plugin.IRemoteReceiver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;

public class ProxyReceiver extends BroadcastReceiver {

    private IRemoteReceiver iRemoteReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        int index = intent.getIntExtra(AppConstants.EXTRA_PLUGIN_INDEX, 0);
        DexClassLoader classLoader = PluginHelper.plugins.get(index).getClassLoader();
        String receiverName = PluginHelper.plugins.get(index).getPluginInfo().packageName + ".PluginReceiver";

        try {
            loadReceiver(classLoader, receiverName);
            iRemoteReceiver.onReceive(context, intent);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void loadReceiver(DexClassLoader classLoader, String receiverName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //反射出插件的Receiver对象
        Class<?> localClass = classLoader.loadClass(receiverName);
        Constructor<?> localConstructor = localClass.getConstructor(new Class[]{});
        Object instance = localConstructor.newInstance(new Object[]{});
        iRemoteReceiver = (IRemoteReceiver) instance;

        iRemoteReceiver.setProxy(this);
    }
}
