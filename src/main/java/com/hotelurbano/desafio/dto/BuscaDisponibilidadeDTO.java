package com.hotelurbano.desafio.dto;

public class BuscaDisponibilidadeDTO {
    private String busca;
    private String dataInicio;
    private String dataFim;
    private Boolean cidade = new Boolean(false);
    private Boolean hotel = new Boolean(false);

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
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
