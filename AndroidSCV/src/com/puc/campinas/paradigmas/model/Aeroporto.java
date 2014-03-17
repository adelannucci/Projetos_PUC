package com.puc.campinas.paradigmas.model;

import java.io.Serializable;

import com.puc.campinas.paradigmas.model.tables.TableAeroporto;

public class Aeroporto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7471123888203861999L;
	private int id;
	private String iata;
	private String nome;
	private String cidade;
	private String pais;
	private double txNacional;
	private double txEstrange;
	private String[] aeroportosColumns;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getIata() 
	{
		return iata;
	}
	
	public void setIata(String iata) 
	{
		this.iata = iata.toUpperCase();;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome.toUpperCase();
	}
	
	public String getCidade() 
	{
		return cidade;
	}
	
	public void setCidade(String cidade) 
	{
		this.cidade = cidade.toUpperCase();
	}
	
	public String getPais() 
	{
		return pais;
	}
	
	public void setPais(String pais) 
	{
		this.pais = pais.toUpperCase();
	}
	
	public double getTxNacional() 
	{
		return txNacional;
	}
	
	public void setTxNacional(double txNacional) 
	{
		this.txNacional = txNacional;
	}
	public double getTxEstrange()
	{
		return txEstrange;
	}
	
	public void setTxEstrange(double txEstrange) 
	{
		this.txEstrange = txEstrange;
	}
	
	public String[] getAeroportosColumns() 
	{
		aeroportosColumns = new String[] {TableAeroporto.ID, TableAeroporto.IATA, TableAeroporto.NOME, TableAeroporto.CIDADE, TableAeroporto.PAIS, TableAeroporto.TAXA_NACIONAL, TableAeroporto.TAXA_ESTRANG};
		return aeroportosColumns;
	}
	
	public String cidadeIata()
	{
		return String.format("\nIata: %s\nCidade: %s",this.getIata(), this.getCidade());
	}
	
	@Override
	public String toString()
	{
		String out ="\nIATA: " +this.iata;
		out += "\nAeroporto: " + this.nome;
		out += "\nCidade: " + this.cidade;
		out += "\nPais: " + this.pais;
		out += "\nTaxa de Embarque Nacional: " + this.txNacional;
		out += "\nTaxa de Embarque estrangeiro: " + this.txEstrange;
		
		return out;
	}
	
}
