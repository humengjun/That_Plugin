package com.hmj.demo.plugin1;

import android.content.Context;
import android.content.Intent;

import com.hmj.demo.sharelibrary.AppConstants;
import com.hmj.demo.sharelibrary.helper.ToastUtils;
import com.hmj.demo.sharelibrary.plugin.BasePluginReceiver;

public class PluginReceiver extends BasePluginReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int index = intent.getIntExtra(AppConstants.EXTRA_PLUGIN_INDEX, 0);
        ToastUtils.shortToast(context, "plugin1:" + index);
    }
}
