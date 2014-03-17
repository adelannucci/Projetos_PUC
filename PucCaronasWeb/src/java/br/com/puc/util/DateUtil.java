/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author adelannucci
 */
public class DateUtil {

    public static Date strToDate(String d) {
        try {
            Date date = new Date(); //data atual
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = sd.parse(d);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static GregorianCalendar dateToGregorianCalendar(Date d) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(d);
        return c;
    }

    public static String dateToStr(Date d) {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sd.format(d);
    }
    
    public static String dateToDateStr(Date d) {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        return sd.format(d);
    }
    
    public static String dateToHourMinuteStr(Date d) {
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        return sd.format(d);
    }

    public static long dataMiles(Date d) {
        return d.getTime();
    }

    public static GregorianCalendar milesToGregorianCalendar(long m) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(m);
        return c;
    }
    
    public static boolean dateBefore(String date, String time)
    {
        Date current = new Date();
        Date compare = DateUtil.strToDate(date + " " + time);
        boolean out = current.before(compare);
        return out;
    }
    
    public static boolean dateBefore(Date date)
    {
        return date.before(new Date());
    }
    
}