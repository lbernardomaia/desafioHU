package com.hotelurbano.desafio.dao;

import com.hotelurbano.desafio.Conexao;
import com.hotelurbano.desafio.extensions.DateTimeExtensions;
import com.hotelurbano.desafio.model.Disponibilidade;
import com.hotelurbano.desafio.model.Hotel;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HotelDAO {

    @Autowired
    private Conexao conexao;

    private String collectionHotel = "hotel";

    public Collection<Hotel> buscarDisponibilidadePorCidade(String busca, DateTime dataInicio, DateTime dataFim){
        String campoBusca = "cidade";
        return buscarDisponilidade(busca, campoBusca, dataInicio, dataFim);
    }

    public Collection<Hotel> buscarDisponibilidadePorHotel(String busca, DateTime dataInicio, DateTime dataFim){
        String campoBusca = "hotel";
        return buscarDisponilidade(busca, campoBusca, dataInicio, dataFim);
    }

    private Collection<Hotel> buscarDisponilidade(String busca, String campoBusca, DateTime dataInicio, DateTime dataFim) {
        Collection<Hotel> hoteis = new ArrayList<>();

        MongoClient mongoClient = conexao.getConexao();
        MongoDatabase mongoDatabase =  conexao.getMongoDatabase(mongoClient);

        MongoCollection collection = mongoDatabase.getCollection(collectionHotel);

        BasicDBObject query = new BasicDBObject();
        query.put(campoBusca, new BasicDBObject("$regex", ".*\\Q" + busca + "\\E.*").append("$options", "i"));

        if (dataInicio != null && dataFim != null){
            query.put("disponibilidade", new BasicDBObject("$elemMatch",
                    new BasicDBObject("disponivel", 1)
                            .append("data", new BasicDBObject("$gte", dataInicio.toDate())
                                    .append("$lte", dataFim.toDate()))));
        }else{
            query.put("disponibilidade", new BasicDBObject("$elemMatch",
                    new BasicDBObject("disponivel", 1)));
        }

        BasicDBObject orderBy = new BasicDBObject();
        orderBy.put("cidade", 1);
        orderBy.append("hotel", 1);

        MongoCursor<Document> cursor = collection.find(query).sort(orderBy).iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                hoteis.add(transformerJsonToHotel(document));
            }
        } finally {
            cursor.close();
            mongoClient.close();
        }
        return hoteis;
    }

    protected Hotel transformerJsonToHotel(Document document) {
        Hotel hotel = new Hotel();

        hotel.set_id(document.getObjectId("_id"));
        hotel.setId(document.getInteger("id"));
        hotel.setCidade(document.getString("cidade"));
        hotel.setHotel(document.getString("hotel"));

        for (Document documentDisponibilidade :(Collection<Document>) document.get("disponibilidade") ){
            Disponibilidade disponibilidade = new Disponibilidade();
            disponibilidade.setData(DateTimeExtensions.dateToDateTime((Date) documentDisponibilidade.get("data")));
            disponibilidade.setDisponibilidade(documentDisponibilidade.get("disponivel").equals("0") ? false : true);
            hotel.addDisponibilidade(disponibilidade);
        }

        return hotel;
    }

    public Collection<String> buscarCidadesDosHoteis() {
        List<String> cidades = new ArrayList<>();
        MongoClient mongoClient = conexao.getConexao();
        MongoDatabase mongoDatabase =  conexao.getMongoDatabase(mongoClient);

        MongoCollection collection = mongoDatabase.getCollection(collectionHotel);
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
        MongoClient mongoClient = conexao.getConexao();
        MongoDatabase mongoDatabase =  conexao.getMongoDatabase(mongoClient);

        MongoCollection collection = mongoDatabase.getCollection(collectionHotel);
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
