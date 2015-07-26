package com.hotelurbano.desafio.service;

import com.hotelurbano.desafio.dao.HotelDAO;
import com.hotelurbano.desafio.dto.BuscaAutoCompleteDTO;
import com.hotelurbano.desafio.dto.BuscaDisponibilidadeDTO;
import com.hotelurbano.desafio.extensions.DateTimeExtensions;
import com.hotelurbano.desafio.model.Hotel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class HotelService {

    @Autowired
    HotelDAO hotelDAO;

    static Collection<String> cidadeDosHoteis = null;
    static Collection<String> hoteis = null;

    public Collection<Hotel> buscarTodos(){
        return hotelDAO.buscarTodos();
    }

    public Collection<Hotel> buscarDisponibilidade(BuscaDisponibilidadeDTO buscaDisponibilidadeDTO){
        DateTime dataInicioDateTime = DateTimeExtensions.StringToISODate(buscaDisponibilidadeDTO.getDataInicio());
        DateTime dataFimDateTime = DateTimeExtensions.StringToISODate(buscaDisponibilidadeDTO.getDataFim());

        return hotelDAO.buscarDisponibilidadePorCidade(buscaDisponibilidadeDTO.getBusca(),
                dataInicioDateTime,
                dataFimDateTime);
    }

    public Collection<BuscaAutoCompleteDTO> buscarParaAutoComplete(String busca){
        int quantidadeElementosRecuperados = 0;

        Collection<BuscaAutoCompleteDTO> buscaAutoCompleteDTOs = new ArrayList<>();
        for (String cidade : cidadeDosHoteis){
            if (isMatches(busca, cidade)){
                BuscaAutoCompleteDTO buscaAutoCompleteDTO = preencherCidade(busca);
                buscaAutoCompleteDTOs.add(buscaAutoCompleteDTO);
                ++quantidadeElementosRecuperados;
                if (isAtingiuMaximoElementos(quantidadeElementosRecuperados)){
                    break;
                }
            }
        }

        for (String hotel : hoteis){
            if (isMatches(busca, hotel)){
                BuscaAutoCompleteDTO buscaAutoCompleteDTO = preencherHotel(busca);
                buscaAutoCompleteDTOs.add(buscaAutoCompleteDTO);
                ++quantidadeElementosRecuperados;
                if (isAtingiuMaximoElementos(quantidadeElementosRecuperados)){
                    break;
                }
            }
        }


        return buscaAutoCompleteDTOs;
    }

    protected boolean isMatches(String busca, String cidade) {
        return cidade.matches("(?i)(.*)" + busca + "(.*)");
    }

    public void popularCacheCidadeHoteis() {
        if (cidadeDosHoteis == null){
            cidadeDosHoteis = buscarCidadesDosHoteis();
        }

        if (hoteis == null){
            hoteis = buscarHoteis();
        }
    }

    private BuscaAutoCompleteDTO preencherCidade(String busca) {
        BuscaAutoCompleteDTO buscaAutoCompleteDTO = new BuscaAutoCompleteDTO();
        buscaAutoCompleteDTO.setBusca(busca);
        buscaAutoCompleteDTO.setCidade(true);
        return buscaAutoCompleteDTO;
    }

    private BuscaAutoCompleteDTO preencherHotel(String busca) {
        BuscaAutoCompleteDTO buscaAutoCompleteDTO = new BuscaAutoCompleteDTO();
        buscaAutoCompleteDTO.setBusca(busca);
        buscaAutoCompleteDTO.setHotel(true);
        return buscaAutoCompleteDTO;
    }

    private boolean isAtingiuMaximoElementos(int quantidadeElementosRecuperados){
        int maximoElementosRecuperados = 5;
        return quantidadeElementosRecuperados == maximoElementosRecuperados;
    }

    public Collection<String> buscarCidadesDosHoteis() {
        cidadeDosHoteis = hotelDAO.buscarCidadesDosHoteis();
        return cidadeDosHoteis;
    }

    public Collection<String> buscarHoteis() {
        hoteis = hotelDAO.buscarHoteis();
        return hoteis;
    }
}
