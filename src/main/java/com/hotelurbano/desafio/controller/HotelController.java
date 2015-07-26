package com.hotelurbano.desafio.controller;

import com.hotelurbano.desafio.dto.BuscaAutoCompleteDTO;
import com.hotelurbano.desafio.dto.BuscaDisponibilidadeDTO;
import com.hotelurbano.desafio.dto.ResultadoBuscaAutoCompleteDTO;
import com.hotelurbano.desafio.model.Hotel;
import com.hotelurbano.desafio.service.HotelService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class HotelController implements InitializingBean {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value="/buscaDisponibilidade", method = RequestMethod.POST)
    @ResponseBody
    public Collection<Hotel> buscaDisponibilidade(@RequestBody BuscaDisponibilidadeDTO buscaDisponibilidadeDTO) {
        return hotelService.buscarDisponibilidade(buscaDisponibilidadeDTO);
    }

    @RequestMapping(value="/buscaAutoComplete", method = RequestMethod.POST)
    @ResponseBody
    public Collection<ResultadoBuscaAutoCompleteDTO> buscaAutoComplete(@RequestBody BuscaAutoCompleteDTO buscaAutoCompleteDTO) {
        return hotelService.buscarParaAutoComplete(buscaAutoCompleteDTO.getBusca());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        hotelService.popularCacheCidadeHoteis();
    }
}
