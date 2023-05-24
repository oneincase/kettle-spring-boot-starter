package io.github.oneincase.service.impl;

import io.github.oneincase.core.KV;
import io.github.oneincase.service.DataBaseService;
import io.github.oneincase.service.PluginService;
import io.github.oneincase.utils.ReflectUtil;
import org.pentaho.di.core.database.BaseDatabaseMeta;
import org.pentaho.di.core.database.DatabaseInterface;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.plugins.PluginInterface;

import java.util.ArrayList;
import java.util.List;

public class DataBaseServiceImpl implements DataBaseService {

    private final PluginService pluginService;


    public DataBaseServiceImpl(PluginService pluginService) {
        this.pluginService = pluginService;
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
        PluginInterface pluginInterface = pluginService.pluginRegistryInstance().getPlugin(DatabasePluginType.class, pluginId);
        String name = pluginInterface.getClassMap().get(DatabaseInterface.class);
        int[] indexes;
        if (pluginInterface.isNativePlugin()) {
            indexes = (int[]) ReflectUtil.invoke(name, "getAccessTypeList");
        } else {
            try {
                ClassLoader classLoader = pluginService.pluginRegistryInstance().getClassLoader(pluginInterface);
                Class<?> aClass = classLoader.loadClass(name);
                BaseDatabaseMeta baseDatabaseMeta = (BaseDatabaseMeta) aClass.newInstance();
                indexes = baseDatabaseMeta.getAccessTypeList();
            } catch (KettlePluginException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        List<KV<String, String>> res = new ArrayList<>();
        for (int index : indexes) {
            KV<String, String> codeDesc =
                    new KV<>(DatabaseMeta.dbAccessTypeCode[index], DatabaseMeta.dbAccessTypeDesc[index]);
            res.add(codeDesc);
        }
        return res;
    }

}
