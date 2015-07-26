package com.hotelurbano.desafio.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class HotelServiceUnitTest {

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

    @Test
    public void isMatches_caso5(){
        hotelService.isMatches("JÁNêíó", "rio de janeiro");
    }

    @Test
    public void isMatches_caso6(){
       hotelService.isMatches("Sao Paulo", "São Paulo");
    }
}
