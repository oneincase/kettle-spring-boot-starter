package io.github.oneincase.service;

import io.github.oneincase.core.KV;
import io.github.oneincase.service.dto.DataBaseDTO;
import org.pentaho.di.core.database.DatabaseMeta;

import java.util.List;

/**
 * database service
 */
public interface DataBaseService {

    DatabaseMeta dataBaseMetaSetValues(DataBaseDTO dataBaseDTO);

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
