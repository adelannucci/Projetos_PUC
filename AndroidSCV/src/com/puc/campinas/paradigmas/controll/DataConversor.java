package com.puc.campinas.paradigmas.controll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataConversor {
	
	
	public static GregorianCalendar strToGregorianCalendar(String d)
	{
		try {
			Date data = new Date(); //data atual
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			data = sd.parse(d);
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(data);
			return c;
		} catch (ParseException e) {
			return null;
		}
		
		
	}
	
	public static GregorianCalendar strToGregorianCalendar(Date d)
	{
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		return c;
	}
	
	public static String dateToStr(Date d)
	{
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sd.format(d);
	}
	
	public static long dataMiles(Date d)
	{
		return d.getTime();
	}
	
	public static GregorianCalendar milesToGregorianCalendar(long m)
	{
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(m);
		return c;
	}

}
