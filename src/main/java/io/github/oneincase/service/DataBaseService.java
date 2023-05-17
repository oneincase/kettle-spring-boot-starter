package io.github.oneincase.service;

import io.github.oneincase.core.CodeDesc;

import java.util.List;

/**
 * 数据库服务
 */
public interface DataBaseService {

    /**
     * 数据库支持列表(code插件id,desc为插件名称)
     */
    List<CodeDesc<String>> supportList();

    /**
     * 获取访问类型列表
     */
    List<CodeDesc<String>> getAccessTypeList(String pluginId);

}
