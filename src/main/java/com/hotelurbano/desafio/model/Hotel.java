package com.hotelurbano.desafio.model;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;

public class Hotel {
    private ObjectId _id;
    private Integer id;
    private String cidade;
    private String hotel;
    private Collection<Disponibilidade> disponibilidades = new ArrayList<>();

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Collection<Disponibilidade> getDisponibilidade() {
        return disponibilidades;
    }

    public void addDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidades.add(disponibilidade);
    }
}
