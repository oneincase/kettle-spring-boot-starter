package io.github.oneincase;


import io.github.oneincase.core.KV;
import io.github.oneincase.service.PluginService;
import io.github.oneincase.service.impl.PluginServiceImpl;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.plugins.StepPluginType;

import java.util.List;

public class PluginRegistryTest extends BaseTest {

    private final static PluginService pluginService = new PluginServiceImpl();

    @Test
    public void getInstance() {
        List<KV<String, String>> kvs = pluginService.pluginsDesc(StepPluginType.class);
        kvs.forEach(System.out::println);
    }


}
