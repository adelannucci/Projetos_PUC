package com.puc.campinas.paradigmas.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import com.puc.campinas.paradigmas.controll.DataConversor;
import com.puc.campinas.paradigmas.model.tables.TableVoo;

public class Voo implements Serializable{

	private static final long serialVersionUID = -4470997189881355282L;
	private int id;
//	private CiaAerea ciaAerea;
	private double distancia;
	private GregorianCalendar dataHora;
	private double preco;
	private Aeroporto origem, destino;
	private String status;
	private String[] vooColumns;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
//	public CiaAerea getCiaAerea() 
//	{
//		return ciaAerea;
//	}
//	
//	public void setCiaAerea(CiaAerea ciaAerea) 
//	{
//		this.ciaAerea = ciaAerea;
//	}
	
	public double getDistancia() 
	{
		return distancia;
	}
	
	public void setDistancia(double distancia) 
	{
		this.distancia = distancia;
	}
	
	public GregorianCalendar getDataHora()
	{
		return dataHora;
	}
	
	public void setDataHora(GregorianCalendar dataHora) 
	{
		this.dataHora = dataHora;
	}
	
	public double getPreco() 
	{
		return preco;
	}
	
	public void setPreco(double preco)
	{
		this.preco = preco;
	}
	
	public Aeroporto getOrigem() 
	{
		return origem;
	}
	
	public void setOrigem(Aeroporto origem) 
	{
		this.origem = origem;
	}
	
	public Aeroporto getDestino() 
	{
		return destino;
	}
	
	public void setDestino(Aeroporto destino)
	{
		this.destino = destino;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public String[] getVooColumns() 
	{
		vooColumns = new String[] {TableVoo.ID, TableVoo.DATA, TableVoo.IATA_ORIGEM, TableVoo.IATA_DESTINO, TableVoo.STATUS, TableVoo.DISTANCIA, TableVoo.PRECO };
		return vooColumns;
	}
	
	public String toString()
	{
		String out = new String("");
		
		out +="Codigo: "+this.id + "\n";
		out += "\nDistancia: "+this.distancia;
		out += "\nData e Hora:"+ DataConversor.dateToStr(dataHora.getTime());
		out += "\nPreco: "+this.preco;
		out += "\nAeroporto de Origem"+origem;
		out += "\nAeroporto de Destino" + destino;
		out += "\nStatus: "+ this.status;
		
		return out;
	}

}
