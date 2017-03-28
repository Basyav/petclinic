package com.bas.petclinic.service.impl;

import com.bas.petclinic.mongo.dao.ImageDAO;
import com.bas.petclinic.service.ImageService;
import com.mongodb.MongoGridFSException;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 *
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public GridFSFile saveImage(InputStream data, String fileName, String fileType, Long id) throws MongoGridFSException {
        return imageDAO.createImage(data, fileName, fileType, id);
    }

    @Override
        public GridFSDBFile getImage(Long id)  {
        return imageDAO.getImageByMetadataId(id);
    }

    @Override
    public void deleteImage(Long id) throws MongoGridFSException {
        imageDAO.deleteImageByMetadataId(id);
    }

    @Override
    public GridFSFile updateImage(InputStream data, String fileName, String fileType, Long id) throws MongoGridFSException {
        deleteImage(id);
        return saveImage(data, fileName, fileType, id);
    }
}
