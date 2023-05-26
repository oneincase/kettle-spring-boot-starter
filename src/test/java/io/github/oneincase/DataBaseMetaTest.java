package io.github.oneincase;

import io.github.oneincase.core.KV;
import io.github.oneincase.service.DataBaseService;
import io.github.oneincase.service.impl.DataBaseServiceImpl;
import io.github.oneincase.service.impl.PluginServiceImpl;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.database.*;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.util.Assert;

import java.util.List;
import java.util.Map;


public class DataBaseMetaTest extends BaseTest {


    @Test
    public void databaseDialog() throws KettlePluginException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PluginRegistry pluginRegistry = PluginRegistry.getInstance();
        List<PluginInterface> dataBasePlugins = pluginRegistry.getPlugins(DatabasePluginType.class);
        for (PluginInterface dataBasePlugin : dataBasePlugins) {
            Map<Class<?>, String> classMap = dataBasePlugin.getClassMap();
            String cn = classMap.get(DatabaseInterface.class);
            ClassLoader classLoader;
            if (!dataBasePlugin.isNativePlugin()) {
                classLoader = pluginRegistry.getClassLoader(dataBasePlugin);
            } else {
                classLoader = this.getClass().getClassLoader();
            }
            Class<?> aClass = classLoader.loadClass(cn);
            BaseDatabaseMeta baseDatabaseMeta = (BaseDatabaseMeta) aClass.newInstance();
            boolean b = baseDatabaseMeta.supportsRepository();
            if (b) {
                System.out.println(aClass + "  " + dataBasePlugin.isNativePlugin());
            }
        }
    }


    @Test
    public void DataBaseServiceImplTest() {
        DataBaseService dataBaseService = new DataBaseServiceImpl(new PluginServiceImpl());
        List<KV<String, String[]>> kvs = dataBaseService.supportList();
        for (KV<String, String[]> kv : kvs) {
            System.out.println(kv.getK() + "=====" + kv.getV()[0]);
        }
    }

    @Test
    public void localConnection() throws KettleDatabaseException {
        DatabaseMeta dataBaseMeta = new DatabaseMeta();
        OracleDatabaseMeta oracleDatabaseMeta = new OracleDatabaseMeta();
        dataBaseMeta.setAccessType(4);
        dataBaseMeta.setDatabaseType("MySQL");
//        dataBaseMeta.setHostname("localhost");
        dataBaseMeta.setDBName("test");
//        dataBaseMeta.setDBPort("3306");
//        dataBaseMeta.setUsername("root");
//        dataBaseMeta.setPassword("123456");
//        dataBaseMeta.addExtraOption("MYSQL","useSSL","false");
        String xml = dataBaseMeta.getXML();
        System.out.println(xml);
//        String s = dataBaseMeta.testConnection();
//        System.out.println(s);
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
        DatabaseMeta dataBaseMeta = new DatabaseMeta();
        DatabaseTestResults databaseTestResults = dataBaseMeta.testConnectionSuccess();
        boolean success = databaseTestResults.isSuccess();
        Assert.assertTrue(success);
    }

}
