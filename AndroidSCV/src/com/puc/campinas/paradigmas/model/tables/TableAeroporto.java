package com.puc.campinas.paradigmas.model.tables;

public class TableAeroporto {

	public static final String TNAME = "aeroporto";
	public static final String ID = "_id";
	public static final String IATA = "iata";
	public static final String NOME = "nome";
	public static final String CIDADE = "cidade";
	public static final String PAIS = "pais";
	public static final String TAXA_NACIONAL = "tn";
	public static final String TAXA_ESTRANG = "te";
	

	
	public static final String SCRIPT_CREATE = "create table " + TNAME + " (" + 
	                ID + " integer primary key autoincrement, " +
					IATA + " text not null unique," +
	                NOME + " text not null," +
					CIDADE + " text not null," +
	                PAIS + " text not null," +
					TAXA_NACIONAL + " DOUBLE," + 
	                TAXA_ESTRANG + " DOUBLE" + " )";
	
	public static final String SCRIPT_DELETE = "DROP TABLE IF EXISTS " + TNAME;
	
	
}
