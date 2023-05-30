package io.github.oneincase.service.impl;

import io.github.oneincase.BaseTest;
import io.github.oneincase.core.KV;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataBaseServiceImplTest extends BaseTest {

    public static DataBaseServiceImpl dataBaseService = new DataBaseServiceImpl(new PluginServiceImpl());

    @Test
    void jsonStrForDatabaseUi() {
        String s = dataBaseService.jsonStrForDatabaseUi();
        Assert.assertNotNull(s);
    }

    @Test
    void supportList() {
        List<KV<String, String[]>> kvs = dataBaseService.supportList();
        for (KV<String, String[]> kv : kvs) {
            System.out.println(kv.getK() + "  " + kv.getV()[0]);
        }
    }

    @Test
    void accessTypeList() {
        List<KV<String, String[]>> kvs = dataBaseService.supportList();
        for (KV<String, String[]> kv : kvs) {
            String pluginId = kv.getV()[0];
            List<KV<String, String>> kvs1 = dataBaseService.accessTypeList(pluginId);
            System.out.println(kvs1);
        }
    }

}