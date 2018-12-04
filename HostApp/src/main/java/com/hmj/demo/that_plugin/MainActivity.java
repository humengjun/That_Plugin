package com.hmj.demo.that_plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hmj.demo.sharelibrary.helper.PluginHelper;
import com.hmj.demo.that_plugin.adapter.PluginAdapter;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private PluginAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mAdapter.setItems(PluginHelper.plugins);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new PluginAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
}
