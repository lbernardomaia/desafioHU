package com.hotelurbano.desafio.dto;

public class BuscaAutoCompleteDTO {
    private String busca;
    private Boolean cidade = new Boolean(false);
    private Boolean hotel = new Boolean(false);

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;

    }

    public Boolean getCidade() {
       return cidade;

    }

    public void setCidade(Boolean cidade) {
       this.cidade = cidade;
    }

    public Boolean getHotel() {
        return hotel;
    }

    public void setHotel(Boolean hotel) {
        this.hotel = hotel;
    }
}
