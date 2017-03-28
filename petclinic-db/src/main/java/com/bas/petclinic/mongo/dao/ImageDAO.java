package com.bas.petclinic.mongo.dao;

import com.mongodb.MongoGridFSException;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import java.io.InputStream;

/**
 * DAO for work with image files of Pet in GridFS
 */
public interface ImageDAO {
    /**
     * Creates file in GridFS with metaData {"id":"pet_id"}
     * @param data image file
     * @param fileName name of image file
     * @param fileType type of file
     * @param id id saved Pet from PosgreSQL
     * @return GridFSFile
     * @throws MongoGridFSException
     */
    GridFSFile createImage(InputStream data, String fileName, String fileType, Long id) throws MongoGridFSException;

    /**
     * Gets image file from GridFS
     * @param id id saved Pet from PosgreSQL
     * @return GridFSDBFile
     */
    GridFSDBFile getImageByMetadataId(Long id);

    /**
     * Deletes image file in GridFS
     * @param id id saved Pet from PosgreSQL
     * @throws MongoGridFSException
     */
    void deleteImageByMetadataId(Long id) throws MongoGridFSException;

    /**
     * Updates file in GridFS
     * @param data image file
     * @param fileName name of image file
     * @param fileType type of file
     * @param id id saved Pet from PosgreSQL
     * @return GridFSFile
     * @throws MongoGridFSException
     */
    GridFSFile updateImage(InputStream data, String fileName, String fileType, Long id) throws MongoGridFSException;

}
