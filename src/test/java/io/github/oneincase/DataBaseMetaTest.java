package io.github.oneincase;

import io.github.oneincase.core.KV;
import io.github.oneincase.service.DataBaseService;
import io.github.oneincase.service.impl.DataBaseServiceImpl;
import io.github.oneincase.service.impl.PluginServiceImpl;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.database.BaseDatabaseMeta;
import org.pentaho.di.core.database.DatabaseInterface;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.database.DatabaseTestResults;
import org.pentaho.di.core.encryption.Encr;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.util.Assert;
import org.pentaho.platform.util.beans.BeanUtil;

import java.util.HashMap;
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
    public void localConnection() throws KettleDatabaseException, KettleXMLException {
        DatabaseMeta dataBaseMeta = new DatabaseMeta();
        dataBaseMeta.setAccessType(0);
        dataBaseMeta.setDatabaseType("MySQL");
        dataBaseMeta.setHostname("localhost");
        dataBaseMeta.setDBName("test");
        dataBaseMeta.setDBPort("3306");
        dataBaseMeta.setUsername("root");
        dataBaseMeta.setPassword("123456");
        dataBaseMeta.addExtraOption("MYSQL", "useSSL", "false");
        String xml = dataBaseMeta.getXML();
        DatabaseMeta dataBaseMetaXml = new DatabaseMeta(xml);
        DatabaseTestResults databaseTestResults = dataBaseMetaXml.testConnectionSuccess();
        boolean success = databaseTestResults.isSuccess();
        Assert.assertTrue(success);
    }

    @Test
    public void localConnection2() throws Exception {
        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setAccessType(0);
        databaseMeta.setDatabaseType("MySQL");
        DatabaseInterface databaseInterface = databaseMeta.getDatabaseInterface();
        BeanUtil beanUtil = new BeanUtil(databaseInterface);
        Map<String, Object> map = new HashMap<>();
        map.put("hostname", "localhost");
        map.put("databaseName", "test");
        map.put("databasePortNumberString", "3306");
        map.put("username", "root");
        map.put("password", "123456");
        beanUtil.setValues(map);
        databaseMeta.addOptions();
        System.out.println(databaseMeta.getURL());
        DatabaseTestResults databaseTestResults = databaseMeta.testConnectionSuccess();
        Assert.assertTrue(databaseTestResults.isSuccess());
    }

    @Test
    public void pass() {
        String encs = Encr.encryptPasswordIfNotUsingVariables("123456@2525DfddfDDDdd334@");
        System.out.println(encs);
        String s = Encr.decryptPassword(encs);
        System.out.println(s);
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
