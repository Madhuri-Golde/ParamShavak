//package com.ParamShavak.ParamShavak.Services;
//
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSDownloadStream;
//import com.mongodb.client.gridfs.model.GridFSFile;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Service
//public class FileService {
//
//    @Autowired
//    private GridFSBucket gridFSBucket;
//
//
//
//    public InputStream downloadFile(String fileId) throws IOException {
//        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(fileId));
//        return downloadStream;
//    }
//}


package com.ParamShavak.ParamShavak.Services;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {

    // Autowired GridFSBucket for handling file operations in MongoDB GridFS
    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     * Downloads a file from GridFS based on the provided file ID.
     *
     * @param fileId the ID of the file to download
     * @return an InputStream for reading the file data
     * @throws IOException if an I/O error occurs while downloading the file
     */
    public InputStream downloadFile(String fileId) throws IOException {
        // Open a download stream for the specified file ID
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(fileId));
        // Return the InputStream to read the file content
        return downloadStream;
    }
}
