package com.bas.petclinic.mongo.dao;

import com.bas.petclinic.mongo.config.MongoDBConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Tests for ImageService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoDBConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class ImageDAOTest {

    private MongodExecutable mongodExec;
    private MongoClient mongo;
    private MongoDatabase db;
    private InputStream is;
    private GridFSFile inputFile;
    
    private final static Long ID = 1L;
    private final static String DATABASE_NAME = "petclinic";

    @Autowired
    ImageDAO imageDAO;

    @Before
    public void setUp() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        String bindIp = "localhost";
        int port = 27017;
        IMongodConfig config = new MongodConfigBuilder()
                .version(Version.V3_2_1)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();
        mongodExec = null;
        mongodExec = starter.prepare(config);
        MongodProcess mongod = mongodExec.start();
        mongo = new MongoClient(bindIp, port);
        db = mongo.getDatabase(DATABASE_NAME);
        is = new FileInputStream("src/test/resources/test.jpg");
    }

    @After
    public void tearDown() throws Exception {
        if (mongodExec != null) {
            mongodExec.stop();
        }
    }

    @Test
    public void testCreateImage() throws Exception {
        inputFile = imageDAO.createImage(is, "test.jpg", "image/jpg", ID);
        assertNotNull(inputFile.getId().toString());
        System.out.println(db.getCollection("fs.files").find().first().toJson());
    }

    @Test
    public void testGetImageByMetadataId() throws Exception {
        inputFile = imageDAO.createImage(is, "test.jpg", "image/jpg", ID);
        GridFSDBFile outputFile = imageDAO.getImageByMetadataId(ID);
        assertEquals(ID, outputFile.getMetaData().get("id"));
    }

    @Test
    public void testDeleteImageByMetadataId() throws Exception {
        inputFile = imageDAO.createImage(is, "test.jpg", "image/jpg", ID);
        imageDAO.deleteImageByMetadataId(ID);
        assertNull(db.getCollection("fs.files").find().first());
    }

    @Test
    public void testUpdateImage() throws Exception {
        GridFSFile oldFile = imageDAO.createImage(is, "test.jpg", "image/jpg", ID);
        InputStream isNew = new FileInputStream("src/test/resources/test_update.jpg");
        GridFSFile newFile = imageDAO.updateImage(isNew, "test_update.jpg", "image/jpg", ID);
        assertNotEquals(oldFile.getFilename(), newFile.getFilename());
        System.out.println(newFile);
    }

}