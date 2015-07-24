package com.hotelurbano.desafio;

import com.hotelurbano.desafio.controller.HotelController;
import com.hotelurbano.desafio.service.HotelService;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HotelControllerUnitTest {

    @Mock
    private HotelService hotelService;

    private final MockMvc mockMvc = standaloneSetup(new HotelController()).build();

    @Test
    public void busca_disponibilidade_sem_resultado() throws Exception {
        mockMvc.perform(get("/buscaDisponibilidade")).andExpect(status().isOk());
    }
}
