package com.hmj.demo.sharelibrary.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class BasePluginActivity extends Activity implements IRemoteActivity {
    protected Activity that;
    protected String dexPath;

    /**
     * 设置代理Activity对象，that指向原ProxyActivity的this
     *
     * @param that    代理Activity
     * @param dexPath 插件apk路径
     */
    public void setProxy(Activity that, String dexPath) {
        this.that = that;
        this.dexPath = dexPath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        that.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        that.startActivityForResult(intent, requestCode);
    }
}
