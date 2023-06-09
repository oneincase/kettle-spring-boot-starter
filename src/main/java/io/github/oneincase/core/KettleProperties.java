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
     * custom kettle home root
     */
    @NotBlank(message = "kettleHome not blank")
    private String homeRoot;

    /**
     * custom plugins root (default: homeRoot/plugins)
     */
    private String pluginsRoot;

    /**
     * console banner
     */
    private Boolean banner = true;


    /**
     * console plugins status
     */
    private Boolean pluginStatus = false;

    /**
     * kettle-properties options
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

    public Boolean getBanner() {
        return banner;
    }

    public void setBanner(Boolean banner) {
        this.banner = banner;
    }

    public Boolean getPluginStatus() {
        return pluginStatus;
    }

    public void setPluginStatus(Boolean pluginStatus) {
        this.pluginStatus = pluginStatus;
    }
}
