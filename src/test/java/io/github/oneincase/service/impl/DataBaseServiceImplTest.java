package io.github.oneincase.service.impl;

import org.junit.jupiter.api.Test;

class DataBaseServiceImplTest {

    @Test
    void jsonStrForDatabaseUi() {
        DataBaseServiceImpl dataBaseService = new DataBaseServiceImpl(new PluginServiceImpl());
        String s = dataBaseService.jsonStrForDatabaseUi();
        System.out.println(s);
    }

}