package io.github.oneincase;

import io.github.oneincase.core.KettleMain;
import io.github.oneincase.core.KettleProperties;
import org.junit.jupiter.api.BeforeAll;
import org.pentaho.di.core.exception.KettleException;

public class BaseTest {

    @BeforeAll
    public static void init() throws KettleException {
        KettleProperties kettleProperties = new KettleProperties();
        kettleProperties.setHomeRoot("E:/apps/var/file-rep");
        KettleMain kettleMain = new KettleMain(kettleProperties);
        kettleMain.run();
    }

}
