package io.github.oneincase.service.impl;

import io.github.oneincase.service.DataBaseService;
import io.github.oneincase.core.CodeDesc;
import io.github.oneincase.utils.ReflectUtil;
import org.pentaho.di.core.database.DatabaseInterface;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataBaseServiceImpl implements DataBaseService {


    /**
     * 数据库支持列表(code插件id,desc为插件名称)
     */
    @Override
    public List<CodeDesc<String>> supportList() {
        return PluginRegistry.getInstance().getPlugins(DatabasePluginType.class).stream()
                .map(pluginInterface ->
                        new CodeDesc<>(pluginInterface.getIds()[0], pluginInterface.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 获取支持的访问类型列表
     */
    @Override
    public List<CodeDesc<String>> getAccessTypeList(String pluginId) {
        PluginInterface pluginInterface = PluginRegistry.getInstance().getPlugin(DatabasePluginType.class, pluginId);
        String name = pluginInterface.getClassMap().get(DatabaseInterface.class);
        int [] indexes = (int [])ReflectUtil.invoke(name, "getAccessTypeList");
        List<CodeDesc<String>> res = new ArrayList<>();
        for (int index : indexes) {
            CodeDesc<String> codeDesc =
                    new CodeDesc<>(DatabaseMeta.dbAccessTypeCode[index],DatabaseMeta.dbAccessTypeDesc[index]);
            res.add(codeDesc);
        }
        return res;
    }

}
