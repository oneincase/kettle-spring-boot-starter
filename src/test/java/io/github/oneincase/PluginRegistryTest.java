package io.github.oneincase;


import io.github.oneincase.service.PluginService;
import io.github.oneincase.service.impl.PluginServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.plugins.PluginRegistry;

public class PluginRegistryTest extends BaseTest {

    private final static PluginService pluginService = new PluginServiceImpl();

    @Test
    public void getInstance() {
        PluginRegistry pluginRegistryInstance = pluginService.pluginRegistryInstance();
        Assert.assertNotNull(pluginRegistryInstance);
    }


}
