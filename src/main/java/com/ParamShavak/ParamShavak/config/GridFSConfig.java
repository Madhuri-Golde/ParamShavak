//
//
//package com.ParamShavak.ParamShavak.config;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSBuckets;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//@Configuration
//public class GridFSConfig {
//
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create("mongodb://localhost:27017");
//    }
//
//    @Bean
//    public GridFSBucket gridFSBucket(MongoClient mongoClient) {
//        return GridFSBuckets.create(mongoClient.getDatabase("paramshavak")); // replace with your database name
//    }
//}

package com.ParamShavak.ParamShavak.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class GridFSConfig {

    // Create a MongoClient bean that connects to the MongoDB instance running on localhost at port 27017.
    // This client will be used to interact with the MongoDB database.
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017"); // Connect to MongoDB on localhost at the default port.
    }

    // Create a GridFSBucket bean, which is used for handling file storage in MongoDB using GridFS.
    // It requires a MongoClient and the name of the database where the files will be stored.
    @Bean
    public GridFSBucket gridFSBucket(MongoClient mongoClient) {
        // Create a GridFS bucket from the "paramshavak" database. This bucket is used for storing large files (e.g., images, videos).
        // GridFS allows storing files that exceed the size limit of MongoDB documents (16MB).
        return GridFSBuckets.create(mongoClient.getDatabase("paramshavak")); // Replace "paramshavak" with your MongoDB database name.
    }
}
