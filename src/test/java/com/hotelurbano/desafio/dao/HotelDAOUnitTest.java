package com.hotelurbano.desafio.dao;

import com.hotelurbano.desafio.model.Disponibilidade;
import com.hotelurbano.desafio.model.Hotel;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class HotelDAOUnitTest {

    @Mock
    private HotelDAO hotelDAO;

    @Before
    public void setup() {
        hotelDAO = new HotelDAO();
    }

    @Test
    public void teste_transformer_json_to_hotel() throws Exception {
        Document document = new Document();
        document.append("_id", new ObjectId("55b41f608debdf22a00e4d6f"));
        document.append("id", 1);
        document.append("cidade", "Araruama");
        document.append("hotel", "Mercatto Casa Hotel");

        ArrayList<Document> disponibilidades = new ArrayList<>();

        Document disponibilidade1 = new Document();
        disponibilidade1.append("data", new Date());
        disponibilidade1.append("disponivel", 0);
        disponibilidades.add(disponibilidade1);

        Document disponibilidade2 = new Document();
        disponibilidade2.append("data", new Date());
        disponibilidade2.append("disponivel", 1);
        disponibilidades.add(disponibilidade2);

        document.append("disponibilidade", disponibilidades);

        Hotel hotelEsperado = new Hotel();
        hotelEsperado.set_id(new ObjectId("55b41f608debdf22a00e4d6f"));
        hotelEsperado.setId(1);
        hotelEsperado.setCidade("Araruama");
        hotelEsperado.setHotel("Mercatto Casa Hotel");

        Hotel hotelObtido = hotelDAO.transformerJsonToHotel(document);
        ArrayList<Disponibilidade> disponibilidadesObtida = (ArrayList<Disponibilidade>) hotelObtido.getDisponibilidade();

        assert hotelEsperado.get_id().equals(hotelObtido.get_id());
        assert hotelEsperado.getId().equals(hotelObtido.getId());
        assert hotelEsperado.getCidade().equals(hotelObtido.getCidade());
        assert hotelEsperado.getHotel().equals(hotelObtido.getHotel());
    }
}
