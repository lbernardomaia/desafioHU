package com.hotelurbano.desafio.controller;

import com.hotelurbano.desafio.model.Hotel;
import com.hotelurbano.desafio.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value="/buscaDisponibilidade",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public Collection<Hotel> buscaDisponibilidade(String busca, String dataInicio, String dataFim) {
        return hotelService.buscarDisponibilidade(busca, dataInicio, dataFim);
    }
}
