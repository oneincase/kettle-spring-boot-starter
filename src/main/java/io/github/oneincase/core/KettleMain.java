package io.github.oneincase.core;

import io.github.oneincase.utils.IOUtil;
import org.apache.commons.lang3.StringUtils;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.PluginTypeInterface;
import org.pentaho.di.core.util.EnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * kettle init
 */
public class KettleMain {

    public final static String KETTLE_BANNER_NAME = "kettle-banner.txt";

    Logger logger = LoggerFactory.getLogger(KettleMain.class);

    private final KettleProperties kettleProperties;

    public KettleMain(@Validated KettleProperties kettleProperties) {
        this.kettleProperties = kettleProperties;
    }

    /**
     * kettle init
     */
    public void run() throws KettleException {
        envInit();
        String useDir = System.getProperty("user.dir");
        System.setProperty("user.dir", kettleProperties.getHomeRoot());
        KettleEnvironment.init(true); // kettle init
        logger.info("kettle init success");
        System.setProperty("usr.dir", useDir);
        if (kettleProperties.getBanner()) {
            String bannerStr = IOUtil.resourceFileToString(KETTLE_BANNER_NAME);
            System.out.println(bannerStr);
        }
        if (kettleProperties.getPluginStatus()) {
            showPluginStatus();
        }
    }

    /**
     * Show Plugin Status
     */
    private void showPluginStatus() {
        PluginRegistry pluginRegistry = PluginRegistry.getInstance();
        String formatStr = "%-3s %-50s %-7s %-2s" + System.lineSeparator();
        System.out.println("********************* kettle plugins status *********************");
        List<Class<? extends PluginTypeInterface>> pluginTypes = pluginRegistry.getPluginTypes();
        System.out.printf(formatStr,
                "** ", "plugin type", "size", "**");
        System.out.println("*****************************************************************");
        for (Class<? extends PluginTypeInterface> pluginType : pluginTypes) {
            List<PluginInterface> plugins = pluginRegistry.getPlugins(pluginType);
            System.out.printf(formatStr,
                    "** ", pluginType.getSimpleName(), plugins.size(), "**");
        }
        System.out.println("*****************************************************************");
    }

    /**
     * set env
     */
    private void envInit() {
        String homeRoot = kettleProperties.getHomeRoot();
        System.setProperty("KETTLE_HOME", homeRoot);
        System.setProperty("DI_HOME", homeRoot);
        System.setProperty("pentaho.user.dir", homeRoot);
        String pluginsRoot = kettleProperties.getPluginsRoot();
        if (StringUtils.isNotBlank(pluginsRoot)) {
            System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS", pluginsRoot);
        } else {
            System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS", homeRoot + Const.FILE_SEPARATOR + "plugins");
        }
        List<Map<String, String>> properties = this.kettleProperties.getProperties();
        if (properties != null && !properties.isEmpty()) {
            for (Map<String, String> property : properties) {
                EnvUtil.applyKettleProperties((property));
            }
        }
    }


}
