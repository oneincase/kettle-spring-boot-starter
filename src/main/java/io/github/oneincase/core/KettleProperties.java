package io.github.oneincase.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kettle")
public class KettleProperties {

    /**
     * 自定义 kettleHome路径
     */
    @NotBlank(message = "自定义kettleHome路径不能为空")
    private String homeRoot;

    /**
     * 插件文件夹路劲 默认值为homeRoot/plugins
     */
    private String pluginsRoot;


    /**
     * kettle-properties配置
     */
    private List<Map<String, String>> properties;

    public String getHomeRoot() {
        return homeRoot;
    }

    public void setHomeRoot(String homeRoot) {
        this.homeRoot = homeRoot;
    }

    public String getPluginsRoot() {
        return pluginsRoot;
    }

    public void setPluginsRoot(String pluginsRoot) {
        this.pluginsRoot = pluginsRoot;
    }

    public List<Map<String, String>> getProperties() {
        return properties;
    }

    public void setProperties(List<Map<String, String>> properties) {
        this.properties = properties;
    }

}
