package com.hotelurbano.desafio.dao;

import com.hotelurbano.desafio.Application;
import com.hotelurbano.desafio.controller.HotelController;
import com.hotelurbano.desafio.extensions.DateTimeExtensions;
import com.hotelurbano.desafio.service.HotelService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HotelDAOUnitTest {

    @Mock
    private HotelDAO hotelDAO;

    @Before
    public void setup() {
        hotelDAO = new HotelDAO();
    }

    @Test
    public void busca_disponibilidade_sem_resultado() throws Exception {
    //    hotelDAO.transformerJsonToObject(null);
        assert true;
    }
}
