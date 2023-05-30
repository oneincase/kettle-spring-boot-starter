package io.github.oneincase.service.impl;

import io.github.oneincase.core.KV;
import io.github.oneincase.service.DataBaseService;
import io.github.oneincase.service.PluginService;
import io.github.oneincase.service.dto.DataBaseDTO;
import io.github.oneincase.utils.IOUtil;
import org.pentaho.di.core.database.BaseDatabaseMeta;
import org.pentaho.di.core.database.DatabaseInterface;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.platform.util.beans.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class DataBaseServiceImpl implements DataBaseService {

    private final static String DATABASE_UI_FILE_NAME = "database-ui.json";

    private final PluginService pluginService;

    private static final PluginRegistry pluginRegistry = PluginRegistry.getInstance();

    public DataBaseServiceImpl(PluginService pluginService) {
        this.pluginService = pluginService;
    }

    @Override
    public DatabaseMeta dataBaseMetaSetValues(DataBaseDTO dataBaseDTO) {
        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setAccessType(dataBaseDTO.getAccessType());
        databaseMeta.setDatabaseType(dataBaseDTO.getDatabaseType());
        DatabaseInterface databaseInterface = databaseMeta.getDatabaseInterface();
        BeanUtil beanUtil = new BeanUtil(databaseInterface);
        try {
            beanUtil.setValues(dataBaseDTO.getProps());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return databaseMeta;
    }

    /**
     * Database Support List (pluginName to K,pluginIds to V)
     */
    @Override
    public List<KV<String, String[]>> supportList() {
        return pluginService.pluginsIds(DatabasePluginType.class);
    }

    /**
     * Access Type List
     */
    @Override
    public List<KV<String, String>> accessTypeList(String pluginId) {
        PluginInterface pluginInterface = pluginRegistry.getPlugin(DatabasePluginType.class, pluginId);
        String name = pluginInterface.getClassMap().get(DatabaseInterface.class);
        BaseDatabaseMeta baseDatabaseMeta;
        try {
            Class<?> css;
            if (pluginInterface.isNativePlugin()) {
                css = Class.forName(name);
            } else {
                css = Class.forName(name, true, pluginRegistry.getClassLoader(pluginInterface));
            }
            baseDatabaseMeta = (BaseDatabaseMeta) css.newInstance();
        } catch (KettlePluginException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        int[] indexes = baseDatabaseMeta.getAccessTypeList();
        List<KV<String, String>> res = new ArrayList<>();
        for (int index : indexes) {
            KV<String, String> codeDesc =
                    new KV<>(DatabaseMeta.dbAccessTypeCode[index], DatabaseMeta.dbAccessTypeDesc[index]);
            res.add(codeDesc);
        }
        return res;
    }

    @Override
    public String jsonStrForDatabaseUi() {
        return IOUtil.resourceFileToString(DATABASE_UI_FILE_NAME);
    }

}
