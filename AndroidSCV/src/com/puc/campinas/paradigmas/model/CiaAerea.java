package com.puc.campinas.paradigmas.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CiaAerea implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4021065762357929964L;
	private String nomeCia;
	private String codigoIata; 
	private List<Aeroporto> destino;
	private List<Voo> voosProgramados;
	
	/**
	 * 
	 * @author 12647202
	 * 
	 * @param nomeCia - nome da companhia aerea
	 * @param codigoIata - codigo identificador da cia. Aerea que deve ser maior que zero e menor que 4
	 * 
	 * */
	public CiaAerea(String nomeCia, String iata)
	{
		setCodigoIata(iata);
		setNomeCia(nomeCia);
		destino = new ArrayList<Aeroporto>();
		voosProgramados = new ArrayList<Voo>();
	}
	
	/**
	 * 
	 * adiciona um aeroporto a lista de destinos
	 * 
	 * @author 12647202
	 * 
	 * @param a - recebe um aeroporto
	 * 
	 * @exception - caso o aeroporto ja seja um destino.
	 * */
	public void destinoAdd(Aeroporto a) throws Exception
	{
		if(!this.localizarDestino(a.getIata()))
			destino.add(a);
		else
			throw new Exception("Destino já adicionado");
	}
	
	
	/**
	 * 
	 * Adiciona um voo a lista de voos programados
	 * 
	 * @author 12647202
	 * 
	 * @param v - recebe um Voo
	 * @exception - caso o voo nao pertenca a ciaAerea ou ele ja esteja adicionado a lista de voos programados
	 * 
	 * */
	public void voosProgramadosAdd(Voo v) throws Exception
	{
		if(null == this.localizarVoo(v.getId(), v.getDataHora()))
		{
			this.voosProgramados.add(v);
		}
		else
		{
			throw new Exception("Voo ja adicionado.");
		}	
	}
	
	/**
	 * 
	 * Busca por um destino atendido pela cia. Aerea
	 * 
	 * @author 12647202
	 * 
	 * @param busca - que deve conter ou o codigo IATA do aeroporto ou a cidade
	 * @return true caso ele seja um destino da cia Aerea ou false caso não seja.
	 * 
	 * */
	public boolean localizarDestino(String busca)
	{
		busca = busca.toUpperCase();
		
		if(!busca.isEmpty())
		{
			for (Aeroporto d : destino) {
				if(d.getIata().equals(busca))
				{
					return true;
				}
				else if (d.getCidade().equals(busca))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public List<Voo> localizarVooOrigem(String cidade)
	{
		cidade = cidade.toUpperCase();
		List<Voo> l = new ArrayList<Voo>();
		
		if(!cidade.isEmpty())
		{
			for (Voo v: voosProgramados) {
				if (v.getOrigem().getCidade().equals(cidade))
				{
					l.add(v);
				}
			}
		}
		
		return l;
		
	}
	
	public List<Voo> localizarVooDestino(String cidade)
	{
		
		cidade = cidade.toUpperCase();
		List<Voo> l = new ArrayList<Voo>();
		
		if(!cidade.isEmpty())
		{
			for (Voo v: voosProgramados) {
				if (v.getDestino().getCidade().equals(cidade))
				{
					l.add(v);
				}
			}
		}
		
		return l;
		
	}
	
	public List<Voo> localizarVooData(String data)
	{
		List<Voo> l = new ArrayList<Voo>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String busca = df.format(data.getTime());
//		
//		busca = busca.substring(0, 11);
		
		if(!data.isEmpty())
		{
			for (Voo v : voosProgramados) {
				String vData = df.format(v.getDataHora().getTime());
//				vData = vData.substring(0, 11);
				if(vData.equals(data))
				{
					l.add(v);
				}
			}
		}
		
		return l;
	}
	
	public List<Voo> localizarVooPorDestinoEData(String cidade, GregorianCalendar data)
	{
		
		cidade = cidade.toUpperCase();
		
		List<Voo> l = new ArrayList<Voo>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String busca = df.format(data.getTime());
		
		busca = busca.substring(0, 11);
		
		if(!busca.isEmpty())
		{
			for (Voo v : voosProgramados) {
				String vData = df.format(v.getDataHora().getTime());
				vData = vData.substring(0, 11);
				if(vData.equals(busca)&&v.getDestino().getCidade().equals(cidade))
				{
					l.add(v);
				}
			}
		}
		
		return l;
	}
	
	public Aeroporto localizarDestinoIATA(String busca)
	{
		busca = busca.toUpperCase();
		
		if(!busca.isEmpty())
		{
			for (Aeroporto d : destino) {
				if(d.getIata().equals(busca))
				{
					return d;
				}
	
			}
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * Localiza um voo pertencente a cia. aerea
	 * 
	 * @author 12647202
	 * 
	 * @param codigo - o codigo identificador do voo
	 * @param data - recebe uma data que representa a hora agendada do voo
	 * 
	 *  @return retorna um Voo caso ele seja encontrado ou null caso não
	 * */
	public Voo localizarVoo(int codigo, GregorianCalendar data)
	{
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String busca = df.format(data.getTime());
		
		busca = busca.substring(0, 11);
		
		if(!busca.isEmpty())
		{
			for (Voo v : voosProgramados) {
				String vData = df.format(v.getDataHora().getTime());
				vData = vData.substring(0, 11);
				if((v.getId() == codigo)&&(vData.equals(busca)))
				{
					return v;
				}
			}
		}
		
		return null;
	}
	
	public String getNomeCia()
	{
		return nomeCia;
	}
	
	public String getCodigoIata()
	{
		return this.codigoIata;
	}
	
	public void setNomeCia(String nomeCia)
	{
		this.nomeCia = nomeCia.toUpperCase();
	}
	
	public void setCodigoIata(String codigoIata)
	{
		if(codigoIata.length() <= 3 && codigoIata.length()>0)
			this.codigoIata = codigoIata.toUpperCase();	
	}
	
	public List<Aeroporto> getDestino() {
		return destino;
	}

	public List<Voo> getVoosProgramados() {
		return voosProgramados;
	}

}
