package io.github.oneincase;

import io.github.oneincase.service.impl.DataBaseServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.database.DatabaseTestResults;
import org.pentaho.di.core.database.MySQLDatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;

public class DataBaseMetaTest extends BaseTest {

    @Test
    public void localConnection() throws KettleDatabaseException {
        DataBaseServiceImpl dataBaseMeta = new DataBaseServiceImpl();
        dataBaseMeta.setAccessType(0);
        dataBaseMeta.setDatabaseType("MySQL");
        dataBaseMeta.setHostname("localhost");
        dataBaseMeta.setDBName("test");
        dataBaseMeta.setDBPort("3306");
        dataBaseMeta.setUsername("root");
        dataBaseMeta.setPassword("123456");
        dataBaseMeta.setShared(false);
        DatabaseTestResults databaseTestResults = dataBaseMeta.testConnectionSuccess();
        boolean success = databaseTestResults.isSuccess();
        Assert.assertTrue(success);
    }

    @Test
    public void JndiTest() {
        DatabaseMeta dataBaseMeta = new DatabaseMeta();
        dataBaseMeta.setAccessType(4);
        dataBaseMeta.setDatabaseType("MySQL");
        dataBaseMeta.setDBName("MySQL");
        DatabaseTestResults databaseTestResults = dataBaseMeta.testConnectionSuccess();
        boolean success = databaseTestResults.isSuccess();
        Assert.assertTrue(success);
    }

    @Test
    public void MysqlMetaTest() {
        MySQLDatabaseMeta meta = new MySQLDatabaseMeta();
        DatabaseMeta dataBaseMeta = new DatabaseMeta();
        DatabaseTestResults databaseTestResults = dataBaseMeta.testConnectionSuccess();
        boolean success = databaseTestResults.isSuccess();
        Assert.assertTrue(success);
    }

}
