package io.github.oneincase.core;

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
        logger.info("kettle run success");
        System.setProperty("usr.dir", useDir);
        PluginRegistry pluginRegistry = PluginRegistry.getInstance();
        logger.info("********************kettle plugins status********************");
        List<Class<? extends PluginTypeInterface>> pluginTypes = pluginRegistry.getPluginTypes();
        for (Class<? extends PluginTypeInterface> pluginType : pluginTypes) {
            List<PluginInterface> plugins = pluginRegistry.getPlugins(pluginType);
            logger.info("**" + pluginType.getSimpleName() + " size:" + plugins.size());
        }
        logger.info("*************************************************************");
    }

    /**
     * set env
     */
    private void envInit(){
        String homeRoot = kettleProperties.getHomeRoot();
        System.setProperty("KETTLE_HOME", homeRoot);
        System.setProperty("DI_HOME",homeRoot);
        System.setProperty("pentaho.user.dir",homeRoot);
        String pluginsRoot = kettleProperties.getPluginsRoot();
        if(StringUtils.isNotBlank(pluginsRoot)){
            System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS",  pluginsRoot);
        }else {
            System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS",  homeRoot+Const.FILE_SEPARATOR+"plugins");
        }
        List<Map<String, String>> properties =  this.kettleProperties.getProperties();
        if(properties!=null && !properties.isEmpty()) {
            for (Map<String, String> property : properties) {
                EnvUtil.applyKettleProperties((property));
            }
        }
    }


}
