package com.puc.campinas.paradigmas.view.cia;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.puc.campinas.paradigmas.R;
import com.puc.campinas.paradigmas.controll.DataConversor;
import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.CiaAerea;
import com.puc.campinas.paradigmas.model.Voo;

public class FindActivityCia extends Activity implements OnClickListener {
	
	protected TextView txt;
	protected ListView listResult;
	protected Spinner cbx;
	protected Button btn;
	protected ArrayAdapter<?> adp;
	protected CiaAerea cia;
	
	private String tipo;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busca_aeroporto);
		Intent intent = getIntent();
		this.txt = (TextView)findViewById(R.id.txtFind);
		this.cbx = (Spinner)findViewById(R.id.cbxAeroportoFind);
		this.listResult = (ListView)findViewById(R.id.listResult);
		this.btn = (Button) findViewById(R.id.btnFind);
		this.btn.setOnClickListener(this);
		
		if(intent != null)
		{
			this.cia = (CiaAerea) intent.getSerializableExtra("cia");
			this.tipo = (String) intent.getStringExtra("tipo");
			
			List<String> list = new ArrayList<String>();
			
			for(Aeroporto a : cia.getDestino())
			{
				list.add(a.getCidade());
			}
			
			adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		    adp.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		    cbx.setAdapter(adp);
			
		
			if(tipo.equals("origem"))
			{
				setTitle("Voo por Cidade de Origem ");
				this.txt.setText("Origem");
			    this.btn.setText("Buscar Voo por Cidade de Origem");
			}
			else if(tipo.equals("destino"))
			{
				setTitle("Voo por Cidade Destino ");
				this.txt.setText("Destino");
			    this.btn.setText("Buscar Voo por Cidade de Origem");
			}
			else if(tipo.equals("data"))
			{
				setTitle("Voo por Data ");
				list.clear();
				for(Voo v : cia.getVoosProgramados())
				{
					list.add(DataConversor.dateToStr(v.getDataHora().getTime()));
				}
				this.txt.setText("Data");
			    this.btn.setText("Buscar Voo por Data");
			}
			else if(tipo.equals("preco"))
			{
				setTitle("Preço Voo");
				adp = new ArrayAdapter<Voo>(this, android.R.layout.simple_spinner_item, cia.getVoosProgramados());
			    adp.setDropDownViewResource(android.R.layout.simple_list_item_1);
			    cbx.setAdapter(adp);
				this.txt.setText("Voos");
			    this.btn.setText("Buscar Preço");
			}
		}

	}
	
	public void onClick(View v) {
		
		if (tipo.equals("origem")) 
		{
			List<Voo> list = cia.localizarVooOrigem((String)cbx.getSelectedItem());
			ArrayAdapter<?> adpV = new ArrayAdapter<Voo>(this, android.R.layout.simple_list_item_1, list);
		    this.listResult.setAdapter(adpV);
			
		}
		else if (tipo.equals("destino")) 
		{
			List<Voo> list = cia.localizarVooDestino((String)cbx.getSelectedItem());
			ArrayAdapter<?> adpV = new ArrayAdapter<Voo>(this, android.R.layout.simple_list_item_1, list);
		    this.listResult.setAdapter(adpV);
		}
		else if(tipo.equals("data"))
		{
			List<Voo> list = cia.localizarVooData((String)cbx.getSelectedItem());
			ArrayAdapter<?> adpV = new ArrayAdapter<Voo>(this, android.R.layout.simple_list_item_1, list);
		    this.listResult.setAdapter(adpV);
		}
		else if(tipo.equals("preco"))
		{
			Voo voo = (Voo)cbx.getSelectedItem();
			String res = "Cidade de Origem: " + voo.getOrigem().getCidade();
			res += "\nTaxa Nacional: R$ "+voo.getOrigem().getTxNacional();
			res += "\nTaxa Estrangeiro: R$ "+voo.getOrigem().getTxEstrange();
			res += "\nCidade de Destino: " + voo.getDestino().getCidade();
			res += "\nTaxa Nacional: R$ "+voo.getDestino().getTxNacional();
			res += "\nTaxa Estrangeiro: R$ "+voo.getDestino().getTxEstrange();
			res += "\nPreco do Voo: R$ " + voo.getPreco();
			res += "\nPreco Cheio(preço do voo + taxa de origem + taxa de destino):" ;
			res += "\nNacional para Nacional: R$ " +  (voo.getPreco() + voo.getOrigem().getTxNacional() + voo.getDestino().getTxNacional());
			res += "\nNacional para Estrangeiro: R$ " + (voo.getPreco() + voo.getOrigem().getTxNacional() + voo.getDestino().getTxEstrange());
			res += "\nEstrangeiro para Nacional: R$ " + (voo.getPreco() + voo.getOrigem().getTxEstrange() + voo.getDestino().getTxNacional());
			res += "\nEstrangeiro para Estrangeiro: R$ " + (voo.getPreco() + voo.getOrigem().getTxEstrange() + voo.getDestino().getTxEstrange());
			
			String[] r = new String[] {res};
			
			ArrayAdapter<?> adpV = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, r);
		    //adpV.setDropDownViewResource(android.R.layout.simple_list_item_1);
		    this.listResult.setAdapter(adpV);
		}
	}


}
