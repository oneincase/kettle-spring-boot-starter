package io.github.oneincase.config;

import io.github.oneincase.core.KettleMain;
import io.github.oneincase.core.KettleProperties;
import io.github.oneincase.service.DataBaseService;
import io.github.oneincase.service.PluginService;
import io.github.oneincase.service.RepositoryService;
import io.github.oneincase.service.impl.DataBaseServiceImpl;
import io.github.oneincase.service.impl.PluginServiceImpl;
import io.github.oneincase.service.impl.RepositoryServiceImpl;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * kettle autoConfiguration
 */
@AutoConfiguration
@Import(KettleProperties.class)
public class KettleAutoConfiguration {

    /**
     * kettle init
     */
    @Bean
    public KettleMain km(KettleProperties kettleProperties) throws KettleException {
        KettleMain kettleMain = new KettleMain(kettleProperties);
        kettleMain.run(); // 启动kettle
        return kettleMain;
    }

    /**
     * PluginService
     */
    @Bean
    public PluginService pluginService() {
        return new PluginServiceImpl();
    }

    /**
     * DataBaseService
     */
    @Bean
    public DataBaseService dataBaseService(PluginService pluginService) {
        return new DataBaseServiceImpl(pluginService);
    }

    /**
     * RepositoryService
     */
    @Bean
    public RepositoryService repositoryService() {
        return new RepositoryServiceImpl();
    }

}
