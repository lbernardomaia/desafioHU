package com.hotelurbano.desafio.dao;

import com.hotelurbano.desafio.model.Disponibilidade;
import com.hotelurbano.desafio.model.Hotel;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HotelDAO {

    public Collection<Hotel> buscarTodos() {
        Collection<Hotel> hoteis = new ArrayList<>();
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost:27017"));
        MongoDatabase db = mongoClient.getDatabase("desafiohu");

        MongoCollection collection = db.getCollection("hotel");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                hoteis.add(transformerJsonToObject(document));
            }
        } finally {
            cursor.close();
            mongoClient.close();
        }

        return hoteis;
    }

    public Collection<Hotel> buscarDisponibilidadePorCidade(String busca, DateTime dataInicio, DateTime dataFim){
        long startTime = System.nanoTime();
        Collection<Hotel> hoteis = new ArrayList<>();
       // try {
            MongoClientOptions options = MongoClientOptions.builder()
                    .connectionsPerHost(200)
                    .maxWaitTime(1000 * 60 * 4)
                            //   .maxWaitTime(2000)
                            //                .socketKeepAlive(true)
                            //                .threadsAllowedToBlockForConnectionMultiplier(50)
                            //     .maxConnectionIdleTime(1000 * 30)
                    .build();

            MongoClient mongoClient = new MongoClient(new ServerAddress("localhost:27017"), options);
            MongoDatabase db = mongoClient.getDatabase("desafiohu");

            MongoCollection collection = db.getCollection("hotel");

            BasicDBObject object = new BasicDBObject();
            object.put("cidade", new BasicDBObject("$regex", ".*\\Q" + busca + "\\E.*").append("$options", "i"));
            object.put("disponibilidade", new BasicDBObject("$elemMatch",
                    new BasicDBObject("disponivel", 1).append("data",
                            new BasicDBObject("$gte", dataInicio.toDate())
                                    .append("$lte", dataFim.toDate()))));

            BasicDBObject object1 = new BasicDBObject();
            object1.put("cidade", 1);
            object1.append("hotel", 1);

            MongoCursor<Document> cursor = collection.find(object).sort(object1).iterator();

            try {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    hoteis.add(transformerJsonToObject(document));
                }
            } finally {
                cursor.close();
                mongoClient.close();
            }
       // }catch (Exception e){
      //      System.out.println(e.getMessage());
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(">>>>>>>>>>>>" + duration);
       //     throw new Exception(e);
     //   }
        return hoteis;
    }

    protected Hotel transformerJsonToObject(Document document) {
        Hotel hotel = new Hotel();

        hotel.set_id(document.getObjectId("_id"));
        hotel.setId(document.getInteger("id"));
        hotel.setCidade(document.getString("cidade"));
        hotel.setHotel(document.getString("hotel"));

        for (Document documentDisponibilidade :(Collection<Document>) document.get("disponibilidade") ){
            Disponibilidade disponibilidade = new Disponibilidade();
            disponibilidade.setData(new DateTime((Date) documentDisponibilidade.get("data")));
            disponibilidade.setDisponibilidade(documentDisponibilidade.get("disponivel").equals("0") ? false : true);
            hotel.addDisponibilidade(disponibilidade);
        }

        return hotel;
    }

    public Collection<String> buscarCidadesDosHoteis() {
        List<String> cidades = new ArrayList<>();
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost:27017"));
        MongoDatabase db = mongoClient.getDatabase("desafiohu");

        MongoCollection collection = db.getCollection("hotel");
//        DBObject orderBy = new BasicDBObject("$orderby", new BasicDBObject("TypeB", 1);
//        BasicDBObject object1 = new BasicDBObject();
//        object1.append("$orderby", new BasicDBObject("TypeB", 1));
        MongoCursor<String> cursor = collection.distinct("cidade", String.class).iterator();

        try {
            while (cursor.hasNext()) {
                String cidade = cursor.next();
                cidades.add(cidade);
            }
        } finally {
            cursor.close();
            mongoClient.close();
            Collections.sort(cidades);
        }

        return cidades;
    }

    public Collection<String> buscarHoteis() {
        List<String> hoteis = new ArrayList<>();
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost:27017"));
        MongoDatabase db = mongoClient.getDatabase("desafiohu");

        MongoCollection collection = db.getCollection("hotel");
        MongoCursor<String> cursor = collection.distinct("hotel", String.class).iterator();

        try {
            while (cursor.hasNext()) {
                String hotel = cursor.next();
                hoteis.add(hotel);
            }
        } finally {
            cursor.close();
            mongoClient.close();
            Collections.sort(hoteis);
        }

        return hoteis;
    }
}
