package com.puc.campinas.paradigmas.model.tables;

public class TableVoo {
	
	public static final String TNAME = "voo";
	public static final String ID = "_id";
//	public static final String IATA_CIA = "iata_cia";
	public static final String DATA = "data";
	public static final String IATA_ORIGEM = "origem";
	public static final String IATA_DESTINO = "destino";
	public static final String STATUS = "status";
	public static final String PRECO = "preco";
	public static final String DISTANCIA = "distancia";
	
	
	public static final String SCRIPT_CREATE = "create table " + TNAME + " (" + 
	                ID + " integer primary key autoincrement, " +
	                DATA + " text not null," +
					IATA_ORIGEM + " text not null," +
	                IATA_DESTINO + " text not null," +
					STATUS + " text not null," +
					DISTANCIA + " DOUBLE," +
					PRECO + " DOUBLE" + " )";
	
	public static final String SCRIPT_DELETE = "DROP TABLE IF EXISTS " + TNAME;
	

}
