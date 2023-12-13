package com.partiurole.partiurole.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static boolean isOldest(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        if (date1 == null || date2 == null) {
            return false;
        }
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        return d1.before(d2);
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = sdf.parse(date);
        return d;
    }

    public static String formatDate(String date) throws ParseException {
        //PARSE STRING "2024-03-16T23:00:00.000000Z" TO FORMAT "dd/MM/yyyy HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        Date d = sdf.parse(date);
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(d);
    }

}
