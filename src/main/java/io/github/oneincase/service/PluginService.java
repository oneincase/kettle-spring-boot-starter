package io.github.oneincase.service;

import io.github.oneincase.core.KV;
import org.pentaho.di.core.plugins.PluginTypeInterface;

import java.util.List;

/**
 * plugin service
 */
public interface PluginService {

    /**
     * description of type plugin list
     */
    List<KV<String, String>> pluginsDesc(Class<? extends PluginTypeInterface> type);

    /**
     * ids of type plugin list
     */
    List<KV<String, String[]>> pluginsIds(Class<? extends PluginTypeInterface> type);

}
