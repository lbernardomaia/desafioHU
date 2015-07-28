package com.hotelurbano.desafio.extensions;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

public final class DateTimeExtensions {

    private DateTimeExtensions() {
    }

    public static final DateTime stringToISODate(String data){
        if (!data.equals("")){
            String[] dataSplit = data.split("/");
            dataSplit[0] = dataSplit[0].length() == 1 ? "0" + dataSplit[0] :  dataSplit[0];
            dataSplit[1] = dataSplit[1].length() == 1 ? "0" + dataSplit[1] :  dataSplit[1];
            dataSplit[2] = dataSplit[2].length() == 1 ? "0" + dataSplit[2] :  dataSplit[2];

            String dataStringISODate = dataSplit[2] + "-" + dataSplit[1] + "-" + dataSplit[0] + "T00:00:00.000Z";
            DateTimeFormatter parser = ISODateTimeFormat.dateTime();
            DateTime result = parser.parseDateTime(dataStringISODate);
            return result;
        }else{
            return null;
        }
    }

    public static final DateTime dateToDateTime(Date data){
        DateTime result = new DateTime(data).withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);
        return result;
    }
}
