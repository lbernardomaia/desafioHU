package com.hotelurbano.desafio.dto;

public class ResultadoBuscaAutoCompleteDTO {
    private String label;
    private Boolean cidade = new Boolean(false);
    private Boolean hotel = new Boolean(false);

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
