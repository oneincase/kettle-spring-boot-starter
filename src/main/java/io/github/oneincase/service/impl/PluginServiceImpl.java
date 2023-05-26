package io.github.oneincase.service.impl;

import io.github.oneincase.core.KV;
import io.github.oneincase.service.PluginService;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.PluginTypeInterface;

import java.util.ArrayList;
import java.util.List;

public class PluginServiceImpl implements PluginService {

    private static final PluginRegistry pluginRegistry = PluginRegistry.getInstance();

    /**
     * description of type plugin list
     */
    @Override
    public List<KV<String, String>> pluginsDesc(Class<? extends PluginTypeInterface> type) {
        List<PluginInterface> plugins = pluginRegistry.getPlugins(type);
        List<KV<String, String>> resList = new ArrayList<>();
        for (PluginInterface plugin : plugins) {
            KV<String, String> kv = new KV<>(plugin.getName(), plugin.getDescription());
            resList.add(kv);
        }
        return resList;
    }

    /**
     * ids of type plugin list
     */
    @Override
    public List<KV<String, String[]>> pluginsIds(Class<? extends PluginTypeInterface> type) {
        List<PluginInterface> plugins = pluginRegistry.getPlugins(type);
        List<KV<String, String[]>> resList = new ArrayList<>();
        for (PluginInterface plugin : plugins) {
            KV<String, String[]> kv = new KV<>(plugin.getName(), plugin.getIds());
            resList.add(kv);
        }
        return resList;
    }


}
