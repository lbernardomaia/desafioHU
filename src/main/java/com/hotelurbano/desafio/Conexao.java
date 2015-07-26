package com.hotelurbano.desafio;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class Conexao {

//    @Value("${mongodb.host}")
    private String host = "localhost";

//    @Value("${mongodb.port}")
    private String port = "27017";

//    @Value("${mongodb.banco}")
    private String banco = "desafiohu";

    public MongoClient getConexao(){
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(200)
                .maxWaitTime(1000 * 60 * 3)
                .minConnectionsPerHost(25)
                .build();

        String addressDB = host + ":" + port;

        MongoClient mongoClient = new MongoClient(new ServerAddress(addressDB), options);
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase(MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(banco);
        return mongoDatabase;
    }
}
