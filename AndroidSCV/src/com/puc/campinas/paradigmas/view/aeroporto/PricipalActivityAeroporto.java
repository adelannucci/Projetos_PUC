package com.puc.campinas.paradigmas.view.aeroporto;

import java.io.Serializable;

import com.puc.campinas.paradigmas.controll.AeroportoDBHelper;
import com.puc.campinas.paradigmas.model.tables.TableAeroporto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PricipalActivityAeroporto extends ListActivity implements OnItemClickListener {

	private static AeroportoDBHelper aeroportos;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        // Cria ou abre a base de dados se já existe
        aeroportos = new AeroportoDBHelper(this);
        setTitle("Menu Aeroporto");
        String[] menu = new String[] {"Visualizar", "Inserir","Alterar", "Excluir"};
        
        ListView listView = getListView();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        listView.setOnItemClickListener(this);
        
    }
    
    @Override
    protected void onDestroy() 
    {
     	super.onDestroy();     
     	// Fecha a base de dados
     	aeroportos.close();
    }
    
    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Serializable list = (Serializable)aeroportos.selectAeroportos();
		
		switch (position) {
		case 0: // Visualizar 
			Intent intentVisualizar = new Intent(this, VisualizarActivityAeroporto.class);
			intentVisualizar.putExtra(TableAeroporto.TNAME, list);
			intentVisualizar.putExtra("tipo", "visualizar");
			startActivity(intentVisualizar);
			break;

		case 1: // Inserir
			startActivity(new Intent(this,InsertActivityAeroporto.class));
			break;
		
		case 2: // Alterar
			Intent intentAlterar = new Intent(this, VisualizarActivityAeroporto.class);
			intentAlterar.putExtra(TableAeroporto.TNAME, list);
			intentAlterar.putExtra("tipo", "alterar");
			startActivity(intentAlterar);
			break;
			
		case 3: // Excluir
			Intent intentExcluir = new Intent(this, VisualizarActivityAeroporto.class);
			intentExcluir.putExtra(TableAeroporto.TNAME, list);
			intentExcluir.putExtra("tipo", "excluir");
			startActivity(intentExcluir);
			break;
			
		default:
			break;
		}
	}

}
