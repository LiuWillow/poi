package com.lwl.bishe.service.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * date  2018/4/13
 * author liuwillow
 **/
@Configuration
public class Config {
    private static final String DB_NAME = "test";
    @Value("${mongodb.dbname}")
    private String dbName;
    @Value("${mongodb.host1}")
    private String host1;
    @Value("${mongodb.host2}")
    private String host2;
    @Value("${mongodb.host3}")
    private String host3;
    @Value("${mongodb.port}")
    private int port;
    @Bean
    public MongoDatabase getMongoDatabase(){
        ServerAddress serverAddress1 = new ServerAddress(host1, port);
        ServerAddress serverAddress2 = new ServerAddress(host2, port);
        ServerAddress serverAddress3 = new ServerAddress(host3, port);

        List<ServerAddress> serverAddressList = Arrays.asList(
                serverAddress1, serverAddress2, serverAddress3
        );
        MongoClient mongoClient = new MongoClient(serverAddressList);
        return mongoClient.getDatabase(DB_NAME);
    }
}
