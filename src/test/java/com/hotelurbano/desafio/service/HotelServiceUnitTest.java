package com.hotelurbano.desafio.service;

import com.hotelurbano.desafio.dao.HotelDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class HotelServiceUnitTest {

    @Mock
    private HotelService hotelService;

    @Before
    public void setup() {
        hotelService = new HotelService();
    }

    @Test
    public void isMatches_caso1(){
        assert !hotelService.isMatches("Araruama", "rio de janeiro");
    }

    @Test
    public void isMatches_caso2(){
        hotelService.isMatches("rio de janeiro", "rio de janeiro");
    }

    @Test
    public void isMatches_caso3(){
        hotelService.isMatches("janeiro", "rio de janeiro");
    }

    @Test
    public void isMatches_caso4(){
        hotelService.isMatches("JaNeIrO", "rio de janeiro");
    }
}
