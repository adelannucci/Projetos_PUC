package com.puc.campinas.paradigmas.view.cia;

import java.io.Serializable;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.puc.campinas.paradigmas.controll.AeroportoDBHelper;
import com.puc.campinas.paradigmas.controll.VooDBHelper;
import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.CiaAerea;
import com.puc.campinas.paradigmas.model.Voo;

public class PrincipalActivityCia extends ListActivity implements OnItemClickListener {
	
	private CiaAerea cia;
	private VooDBHelper dbVoo;
	private AeroportoDBHelper dbAero;
	private List<Aeroporto> aeroportos;
	private List<Voo> voos;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        cia = new CiaAerea("Cia", "cia");
        dbVoo = new VooDBHelper(this);
        dbAero = new AeroportoDBHelper(this);
        
        aeroportos = dbAero.selectAeroportos();
        
        for(Aeroporto a : aeroportos)
        {
			try {cia.destinoAdd(a);} 
        	catch (Exception e) {}
        }
        
        voos = dbVoo.selectVoos();
        
        for(Voo v : voos)
        {
        	try {cia.voosProgramadosAdd(v);} 
        	catch (Exception e) {}
        }


        
        String[] menu = new String[] {"Voo por Cidade Origem", "Voo por Cidade Destino", "Voo por Data", "Preço"};
        
        ListView listView = getListView();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        listView.setOnItemClickListener(this);
        
    }
    
    @Override
    protected void onDestroy() 
    {
     	super.onDestroy();     
    }
    
    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	Serializable sCia = (Serializable)this.cia;
    	Intent intent = new Intent(this, FindActivityCia.class);
    	intent.putExtra("cia", sCia);
    	
		switch (position) {
		case 0: //Origem 
//			Intent intentOrigem = new Intent(this, FindActivityCia.class);
//			intent.putExtra("cia", sCia);
			intent.putExtra("tipo", "origem");
			startActivity(intent);
			break;

		case 1: // Destino
//			Intent intentDestino = new Intent(this, FindActivityCia.class);
//			intent.putExtra("cia", sCia);
			intent.putExtra("tipo", "destino");
			startActivity(intent);
			break;
		
		case 2: // Data
//			Intent intentData = new Intent(this, FindActivityCia.class);
			intent.putExtra("tipo", "data");
			startActivity(intent);
			break;
			
		case 3: // preco
			intent.putExtra("tipo", "preco");
			startActivity(intent);
			break;
			
		default:
			break;
		}
	}
}
