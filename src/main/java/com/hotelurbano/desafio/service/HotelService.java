package com.hotelurbano.desafio.service;

import com.hotelurbano.desafio.dao.HotelDAO;
import com.hotelurbano.desafio.dto.BuscaDisponibilidadeDTO;
import com.hotelurbano.desafio.dto.ResultadoBuscaAutoCompleteDTO;
import com.hotelurbano.desafio.extensions.DateTimeExtensions;
import com.hotelurbano.desafio.model.Hotel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class HotelService {

    @Autowired
    HotelDAO hotelDAO;

    static Collection<String> cidadeDosHoteis = null;
    static Collection<String> hoteis = null;

    public Collection<Hotel> buscarDisponibilidade(BuscaDisponibilidadeDTO buscaDisponibilidadeDTO){
        DateTime dataInicioDateTime = DateTimeExtensions.stringToISODate(buscaDisponibilidadeDTO.getDataInicio());
        DateTime dataFimDateTime = DateTimeExtensions.stringToISODate(buscaDisponibilidadeDTO.getDataFim());
        if (buscaDisponibilidadeDTO.getCidade()){
            return hotelDAO.buscarDisponibilidadePorCidade(buscaDisponibilidadeDTO.getBusca(),
                    dataInicioDateTime,
                    dataFimDateTime);
        }else if (buscaDisponibilidadeDTO.getHotel()){
            return hotelDAO.buscarDisponibilidadePorHotel(buscaDisponibilidadeDTO.getBusca(),
                    dataInicioDateTime,
                    dataFimDateTime);
        }else{
            return new ArrayList<>();
        }
    }

    public Collection<ResultadoBuscaAutoCompleteDTO> buscarParaAutoComplete(String busca){
        Collection<ResultadoBuscaAutoCompleteDTO> resultadosBuscaAutoCompleteDTO = new ArrayList<>();

        pesquisarPorCidade(busca, resultadosBuscaAutoCompleteDTO);

        pesquisarPorHotel(busca, resultadosBuscaAutoCompleteDTO);

        return resultadosBuscaAutoCompleteDTO;
    }

    private void pesquisarPorCidade(String busca, Collection<ResultadoBuscaAutoCompleteDTO> resultadosBuscaAutoCompleteDTO) {
        for (String cidade : cidadeDosHoteis){
            if (isMatches(busca, cidade)){
                ResultadoBuscaAutoCompleteDTO resultadoBuscaAutoCompleteDTO = preencherCidade(cidade);
                resultadosBuscaAutoCompleteDTO.add(resultadoBuscaAutoCompleteDTO);
                if (isAtingiuMaximoElementos(resultadosBuscaAutoCompleteDTO.size())){
                    break;
                }
            }
        }
    }

    private void pesquisarPorHotel(String busca, Collection<ResultadoBuscaAutoCompleteDTO> resultadosBuscaAutoCompleteDTO) {
        if (!isAtingiuMaximoElementos(resultadosBuscaAutoCompleteDTO.size())){
            for (String hotel : hoteis){
                if (isMatches(busca, hotel)){
                    ResultadoBuscaAutoCompleteDTO resultadoBuscaAutoCompleteDTO = preencherHotel(hotel);
                    resultadosBuscaAutoCompleteDTO.add(resultadoBuscaAutoCompleteDTO);
                    if (isAtingiuMaximoElementos(resultadosBuscaAutoCompleteDTO.size())){
                        break;
                    }
                }
            }
        }
    }

    protected boolean isMatches(String buscaUsuario, String itemParaComparar) {
        String buscaUsuarioNormalizada = Normalizer.normalize(buscaUsuario, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String itemParaCompararNormalizado = Normalizer.normalize(itemParaComparar, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return itemParaCompararNormalizado.matches("(?i)(.*)" + buscaUsuarioNormalizada + "(.*)");
    }

    public void popularCacheCidadeHoteis() {
        if (cidadeDosHoteis == null){
            cidadeDosHoteis = buscarCidadesDosHoteis();
        }

        if (hoteis == null){
            hoteis = buscarHoteis();
        }
    }

    private ResultadoBuscaAutoCompleteDTO preencherCidade(String busca) {
        ResultadoBuscaAutoCompleteDTO resultadoBuscaAutoCompleteDTO = new ResultadoBuscaAutoCompleteDTO();
        resultadoBuscaAutoCompleteDTO.setLabel(busca);
        resultadoBuscaAutoCompleteDTO.setCidade(true);
        return resultadoBuscaAutoCompleteDTO;
    }

    private ResultadoBuscaAutoCompleteDTO preencherHotel(String busca) {
        ResultadoBuscaAutoCompleteDTO resultadoBuscaAutoCompleteDTO = new ResultadoBuscaAutoCompleteDTO();
        resultadoBuscaAutoCompleteDTO.setLabel(busca);
        resultadoBuscaAutoCompleteDTO.setHotel(true);
        return resultadoBuscaAutoCompleteDTO;
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
