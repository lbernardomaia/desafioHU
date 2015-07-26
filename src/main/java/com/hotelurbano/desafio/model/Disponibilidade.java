package com.hotelurbano.desafio.model;


import org.joda.time.DateTime;

public class Disponibilidade {
    private DateTime data;
    private Boolean disponibildiade;

    public DateTime getData() {
        return data;
    }

    public void setData(DateTime data) {
        this.data = data;
    }

    public Boolean getDisponibildiade() {
        return disponibildiade;
    }

    public void setDisponibilidade(Boolean disponibildiade) {
        this.disponibildiade = disponibildiade;
    }
}
