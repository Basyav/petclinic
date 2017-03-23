package com.bas.petclinic.service.impl;

import com.bas.petclinic.service.ImageService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoGridFSException;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public GridFSFile createImage(InputStream data, String fileName, String fileType, Long id) throws MongoGridFSException {
        DBObject metaData = new BasicDBObject();
        metaData.put("id", id);
        return gridFsTemplate.store(data, fileName, fileType, metaData);
    }

    @Override
        public GridFSDBFile getImageByMetadataId(Long id)  {
        return gridFsTemplate.findOne(new Query(Criteria.where("metadata.id").is(id)));
    }

    @Override
    public void deleteImageByMetadataId(Long id) throws MongoGridFSException {
        gridFsTemplate.delete(new Query(Criteria.where("metadata.id").is(id)));
    }

    @Override
    public GridFSFile updateImage(InputStream data, String fileName, String fileType, Long id) throws MongoGridFSException {
        deleteImageByMetadataId(id);
        return createImage(data, fileName, fileType, id);
    }
}
