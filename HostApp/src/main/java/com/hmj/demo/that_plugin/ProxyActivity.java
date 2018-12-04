package com.hmj.demo.that_plugin;

import android.content.Intent;
import android.os.Bundle;

import com.hmj.demo.sharelibrary.AppConstants;
import com.hmj.demo.sharelibrary.helper.PluginHelper;
import com.hmj.demo.sharelibrary.host.BaseHostActivity;
import com.hmj.demo.sharelibrary.plugin.IRemoteActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ProxyActivity extends BaseHostActivity {

    private IRemoteActivity mRemoteActivity;//目标插件的Activity
    private HashMap<String, Method> mActivityLifecircleMethods = new HashMap<String, Method>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index = getIntent().getIntExtra(AppConstants.EXTRA_PLUGIN_INDEX, -1);
        if (index != -1) {
            dexClassLoader = PluginHelper.plugins.get(index).getClassLoader();
            className = PluginHelper.plugins.get(index).getPluginInfo().packageName + ".PluginActivity";
            dexPath = PluginHelper.plugins.get(index).getPluginPath();
        }

        loadResources();

        launchTargetActivity(className);

        //执行onCreate方法
        mRemoteActivity.onCreate(savedInstanceState);
//        try {
//            mActivityLifecircleMethods.get("onCreate").invoke(mRemoteActivity, new Object[]{savedInstanceState});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

    }

    private void launchTargetActivity(String className) {
        try {
            //得到目标插件的Activity对象
            Class<?> loadClass = dexClassLoader.loadClass(className);
            Constructor<?> loadConstructor = loadClass.getConstructor(new Class[]{});
            Object instance = loadConstructor.newInstance(new Object[]{});
            mRemoteActivity = (IRemoteActivity) instance;

            //双向绑定
//            Method setProxy = loadClass.getMethod("setProxy", new Class[] { Activity.class, String.class });
//            setProxy.setAccessible(true);
//            setProxy.invoke(instance, new Object[] { this, dexPath });
            mRemoteActivity.setProxy(this,dexPath);

            //一次性反射整个生命周期
//            loadLifecircleMethods(loadClass);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void loadLifecircleMethods(Class<?> loadClass) throws NoSuchMethodException {
        String[] methodNames = {
                "onStart",
                "onResume",
                "onRestart",
                "onStop",
                "onPause",
                "onDestroy"
        };

        //添加onCreate方法
        Method onCreate = loadClass.getDeclaredMethod("onCreate", new Class[]{Bundle.class});
        onCreate.setAccessible(true);
        mActivityLifecircleMethods.put("onCreate", onCreate);

        //添加无参数生命周期
        for (String methodName : methodNames) {
            Method method = loadClass.getDeclaredMethod(methodName, new Class[]{});
            method.setAccessible(true);
            mActivityLifecircleMethods.put(methodName, method);
        }

        //添加onActivityResult方法
        Method onActivityResult = loadClass.getDeclaredMethod("onActivityResult",
                new Class[]{int.class, int.class, Intent.class});
        onActivityResult.setAccessible(true);
        mActivityLifecircleMethods.put("onActivityResult", onActivityResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRemoteActivity.onStart();
//        try {
//            mActivityLifecircleMethods.get("onStart").invoke(mRemoteActivity, new Object[]{});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRemoteActivity.onResume();
//        try {
//            mActivityLifecircleMethods.get("onResume").invoke(mRemoteActivity, new Object[]{});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mRemoteActivity.onRestart();
//        try {
//            mActivityLifecircleMethods.get("onRestart").invoke(mRemoteActivity, new Object[]{});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRemoteActivity.onPause();
//        try {
//            mActivityLifecircleMethods.get("onPause").invoke(mRemoteActivity, new Object[]{});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRemoteActivity.onStop();
//        try {
//            mActivityLifecircleMethods.get("onStop").invoke(mRemoteActivity, new Object[]{});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRemoteActivity.onDestroy();
//        try {
//            mActivityLifecircleMethods.get("onDestroy").invoke(mRemoteActivity, new Object[]{});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mRemoteActivity.onActivityResult(requestCode,resultCode,data);
//        try {
//            mActivityLifecircleMethods.get("onActivityResult").invoke(mRemoteActivity, new Object[]{requestCode, resultCode, data});
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}
