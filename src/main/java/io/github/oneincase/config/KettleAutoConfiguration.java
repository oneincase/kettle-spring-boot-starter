package io.github.oneincase.config;

import io.github.oneincase.core.KettleMain;
import io.github.oneincase.core.KettleProperties;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
    @ConditionalOnBean(KettleProperties.class)
    public KettleMain km(KettleProperties kettleProperties) throws KettleException {
        KettleMain kettleMain = new KettleMain(kettleProperties);
        kettleMain.run(); // 启动kettle
        return kettleMain;
    }

}
