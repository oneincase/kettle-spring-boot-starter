package io.github.oneincase;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.AbstractRepository;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.pentaho.di.repository.filerep.KettleFileRepositoryMeta;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;

public class RepositoryTest extends BaseTest {

    @Test
    public void databaseRepTest() throws KettleException {
        AbstractRepository kettleDatabaseRepository = new KettleDatabaseRepository();
        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta = new KettleDatabaseRepositoryMeta();
        DatabaseMeta dataBaseMeta = new DatabaseMeta();
        dataBaseMeta.setAccessType(0);
        dataBaseMeta.setDatabaseType("MySQL");
        dataBaseMeta.setHostname("localhost");
        dataBaseMeta.setDBName("lkr2");
        dataBaseMeta.setDBPort("3306");
        dataBaseMeta.setUsername("root");
        dataBaseMeta.setPassword("123456");
        kettleDatabaseRepositoryMeta.setConnection(dataBaseMeta);
        kettleDatabaseRepositoryMeta.setName("custom");
        kettleDatabaseRepository.init(kettleDatabaseRepositoryMeta);
//        kettleDatabaseRepository.create();
        kettleDatabaseRepository.connect("admin", "admin");
        boolean test = kettleDatabaseRepository.test();
        Assert.assertTrue(test);
    }

    @Test
    public void fileRepTest() throws KettleException {
        AbstractRepository kettleFileRepository = new KettleFileRepository();
        KettleFileRepositoryMeta kettleFileRepositoryMeta = new KettleFileRepositoryMeta();
        kettleFileRepositoryMeta.setName("fileRep");
        kettleFileRepositoryMeta.setBaseDirectory("E:/home");
        kettleFileRepository.init(kettleFileRepositoryMeta);
        boolean test = kettleFileRepository.test();
        Assert.assertTrue(test);
    }


    public void fileRepTest2() throws KettleException {

    }

}
