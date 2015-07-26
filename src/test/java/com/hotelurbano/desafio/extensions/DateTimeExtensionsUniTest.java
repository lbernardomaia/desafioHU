package com.hotelurbano.desafio.extensions;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.util.Date;

public class DateTimeExtensionsUniTest {

   @Test
   public void data_string_para_dateTime() {
       DateTime isoDateConvertida = DateTimeExtensions.stringToISODate("4/5/2015");

       DateTime dataEsperada = new DateTime(2015, 5, 4, 0, 0, 0, 0);

       String crDt = "2015-05-04T00:00:00.000Z";
       DateTimeFormatter parser = ISODateTimeFormat.dateTime();
       DateTime result = parser.parseDateTime(crDt);

       assert isoDateConvertida.equals(result);
   }


   @Test
   public void date_para_dateTime() {
       Date data = new Date();

       DateTime isoDateConvertida = DateTimeExtensions.dateToDateTime(data);

       DateTime dataEsperada = new DateTime(data).withHourOfDay(0)
               .withMinuteOfHour(0)
               .withSecondOfMinute(0)
               .withMillisOfDay(0)
               .withMillisOfSecond(0);

       assert isoDateConvertida.equals(dataEsperada);
   }
}
