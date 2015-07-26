package com.hotelurbano.desafio.service;

import com.hotelurbano.desafio.Application;
import com.hotelurbano.desafio.dto.BuscaDisponibilidadeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HotelServiceIntTest {

    @Autowired
    HotelService hotelService;

    @Test
    public void busca_disponibilidade_cidade(){
        BuscaDisponibilidadeDTO buscaDisponibilidadeDTO =  new BuscaDisponibilidadeDTO();
        buscaDisponibilidadeDTO.setBusca("Resende");
        buscaDisponibilidadeDTO.setDataInicio("03/05/2015");
        buscaDisponibilidadeDTO.setDataFim("04/05/2015");
        buscaDisponibilidadeDTO.setCidade(true);

        assert hotelService.buscarDisponibilidade(buscaDisponibilidadeDTO).size() == 6;
    }

    @Test
    public void busca_disponibilidade_hotel(){
        BuscaDisponibilidadeDTO buscaDisponibilidadeDTO =  new BuscaDisponibilidadeDTO();
        buscaDisponibilidadeDTO.setBusca("Am Romerweg");
        buscaDisponibilidadeDTO.setDataInicio("03/05/2015");
        buscaDisponibilidadeDTO.setDataFim("04/05/2015");
        buscaDisponibilidadeDTO.setHotel(true);

        assert hotelService.buscarDisponibilidade(buscaDisponibilidadeDTO).size() == 1;
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
    public void busca_auto_complete_por_cidade_itaguai_sem_acento(){
        String busca = "itaguaI";
        assert hotelService.buscarParaAutoComplete(busca).size() == 1;
    }

    @Test
    public void busca_auto_complete_por_hotel(){
        String busca = "Comfort Inn North";
        assert hotelService.buscarParaAutoComplete(busca).size() == 1;
    }

    @Test
    public void busca_auto_complete_por_cidade_e_hotel(){
        String busca = "Mar";
        assert hotelService.buscarParaAutoComplete(busca).size() == 5;
    }
}
