package com.hotelurbano.desafio.service;

import com.hotelurbano.desafio.Application;
import com.hotelurbano.desafio.dto.BuscaDisponibilidadeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HotelServiceIntTest {

    @Autowired
    HotelService hotelService;

    @Test
    public void buscar_todos(){
        assert hotelService.buscarTodos().size() == 500;
    }

    @Test
    public void busca_disponibilidade(){
        String busca = "Resende";

        BuscaDisponibilidadeDTO buscaDisponibilidadeDTO =  new BuscaDisponibilidadeDTO();
        buscaDisponibilidadeDTO.setBusca("Resende");
        buscaDisponibilidadeDTO.setDataInicio("03/05/2015");
        buscaDisponibilidadeDTO.setDataFim("04/05/2015");

        assert hotelService.buscarDisponibilidade(buscaDisponibilidadeDTO).size() == 6;
    }

    @Test
    public void busca_cidades(){
        assert hotelService.buscarCidadesDosHoteis().size() == 46;
    }

    @Test
    public void busca_hoteis(){
        assert hotelService.buscarHoteis().size() == 498;
    }

    @Test
    public void busca_auto_complete_por_cidade_rio_de_janeiro(){
        String busca = "Rio de Janeiro";
        assert hotelService.buscarParaAutoComplete(busca).size() == 1;
    }



}
