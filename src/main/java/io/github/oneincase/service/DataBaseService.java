package io.github.oneincase.service;

import io.github.oneincase.core.KV;

import java.util.List;

/**
 * database service
 */
public interface DataBaseService {

    /**
     * Database Support List (pluginName to K,pluginIds to V)
     */
    List<KV<String, String[]>> supportList();

    /**
     * Access Type List
     */
    List<KV<String, String>> accessTypeList(String pluginId);

    /**
     * Read File database-ui.json
     */
    String jsonStrForDatabaseUi();

}
