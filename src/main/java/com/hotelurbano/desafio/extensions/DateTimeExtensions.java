package com.hotelurbano.desafio.extensions;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTimeExtensions {

    private DateTimeExtensions() {
    }

    public static final DateTime StringToISODate(String data){
        String[] dataSplit = data.split("/");
        dataSplit[0] = dataSplit[0].length() == 1 ? "0" + dataSplit[0] :  dataSplit[0];
        dataSplit[1] = dataSplit[1].length() == 1 ? "0" + dataSplit[1] :  dataSplit[1];
        dataSplit[2] = dataSplit[2].length() == 1 ? "0" + dataSplit[2] :  dataSplit[2];

        String crDt = dataSplit[2] + "-" + dataSplit[1] + "-" + dataSplit[0] + "T00:00:00.000Z";
        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime result = parser.parseDateTime(crDt);
        return result;
    }
}
