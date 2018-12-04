package com.hmj.demo.that_plugin.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hmj.demo.sharelibrary.AppConstants;
import com.hmj.demo.sharelibrary.helper.PluginItem;
import com.hmj.demo.that_plugin.ProxyService;
import com.hmj.demo.that_plugin.R;
import com.hmj.demo.that_plugin.utils.DLUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PluginAdapter extends RecyclerView.Adapter<PluginAdapter.PluginHolder> {

    private List<PluginItem> items = new ArrayList<>();
    private Context context;

    public PluginAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<PluginItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(PluginItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PluginHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plugin_item, null);
        final PluginHolder pluginHolder = new PluginHolder(view);
        return pluginHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PluginHolder holder, final int position) {
        PluginItem item = items.get(position);
        PackageInfo packageInfo = item.getPluginInfo();
        holder.plugin_image.setImageDrawable(DLUtils.getAppIcon(context, item.getPluginPath()));
        holder.app_name.setText(DLUtils.getAppLabel(context, item.getPluginPath()));
        holder.apk_name.setText(item.getPluginPath().substring(item.getPluginPath().lastIndexOf(File.separatorChar) + 1));
        holder.package_name.setText(packageInfo.applicationInfo.packageName);
        holder.start_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                PluginItem item = items.get(pluginHolder.getAdapterPosition());
                Intent intent = new Intent(AppConstants.PROXY_ACTIVITY_ACTION);
                intent.putExtra(AppConstants.EXTRA_PLUGIN_INDEX, position);
//                intent.putExtra(AppConstants.EXTRA_CLASS, item.getPluginInfo().packageName + ".PluginActivity");
                context.startActivity(intent);
            }
        });
        holder.start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                PluginItem item = items.get(pluginHolder.getAdapterPosition());
                Intent intent = new Intent(context, ProxyService.class);
                intent.putExtra(AppConstants.EXTRA_PLUGIN_INDEX, position);
//                intent.putExtra(AppConstants.EXTRA_CLASS, item.getPluginInfo().packageName + ".PluginActivity");
                context.startService(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class PluginHolder extends RecyclerView.ViewHolder {
        private ImageView plugin_image;
        private TextView app_name, apk_name, package_name;
        private Button start_activity, start_service;


        public PluginHolder(View itemView) {
            super(itemView);
            plugin_image = itemView.findViewById(R.id.image);
            app_name = itemView.findViewById(R.id.app_name);
            apk_name = itemView.findViewById(R.id.apk_name);
            package_name = itemView.findViewById(R.id.package_name);
            start_activity = itemView.findViewById(R.id.startActivity);
            start_service = itemView.findViewById(R.id.startService);
        }
    }
}


