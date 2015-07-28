package com.hotelurbano.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelurbano.desafio.ApplicationTests;
import com.hotelurbano.desafio.dto.BuscaAutoCompleteDTO;
import com.hotelurbano.desafio.dto.BuscaDisponibilidadeDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HotelControllerUnitTest {

    private final MockMvc mockMvc = standaloneSetup(new HotelController()).build();

    public void busca_disponibilidade_sem_resultado() throws Exception {
        mockMvc.perform(post("/buscaDisponibilidade"))
                .andExpect(status().isOk());
    }

}
