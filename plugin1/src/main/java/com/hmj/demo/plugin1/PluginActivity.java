package com.hmj.demo.plugin1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hmj.demo.sharelibrary.plugin.BasePluginActivity;


public class PluginActivity extends BasePluginActivity {

    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        textView = findViewById(R.id.plugin_text);
        textView.setText("Plugin1：onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("Plugin1：onResume");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        textView.setText("Plugin1：onRestart");
    }
}
