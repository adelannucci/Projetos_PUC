package com.puc.campinas.paradigmas.model;

public enum StatusVoo {
	P("Previsto"),
	C("Confirmado"),
	D("Despacho"),
	A("Aberto"),
	E("Embarque Próximo"),
	EV("Em Voo"),
	PO("Pousado"),
	AT("Atrasado"),
	CA("Cancelado");
	
	
	private final String status;
	
	public String getStatus()
	{
		return status;
	}
	
	private StatusVoo(String status)
	{
		this.status = status;
	}

}
