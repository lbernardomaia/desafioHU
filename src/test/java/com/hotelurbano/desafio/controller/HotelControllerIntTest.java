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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HotelControllerIntTest extends ApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void busca_disponibilidade() throws Exception {
        BuscaDisponibilidadeDTO buscaDisponibilidadeDTO = new BuscaDisponibilidadeDTO();
        buscaDisponibilidadeDTO.setBusca("Araruama");
        buscaDisponibilidadeDTO.setDataInicio("03/05/2015");
        buscaDisponibilidadeDTO.setDataFim("04/05/2015");
        buscaDisponibilidadeDTO.setCidade(true);
        mockMvc.perform(post("/buscaDisponibilidade")
                 .content(asJsonString(buscaDisponibilidadeDTO))
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void busca_autocomplete() throws Exception {
        BuscaAutoCompleteDTO buscaAutoCompleteDTO = new BuscaAutoCompleteDTO();
        buscaAutoCompleteDTO.setBusca("Araruama");
        mockMvc.perform(post("/buscaAutoComplete")
                .content(asJsonString(buscaAutoCompleteDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
