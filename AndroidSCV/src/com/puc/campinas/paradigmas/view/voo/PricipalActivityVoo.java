package com.puc.campinas.paradigmas.view.voo;

import java.io.Serializable;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.puc.campinas.paradigmas.controll.VooDBHelper;
import com.puc.campinas.paradigmas.model.tables.TableVoo;

public class PricipalActivityVoo extends ListActivity implements OnItemClickListener {

	private static VooDBHelper voos;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        
        // Cria ou abre a base de dados se já existe
		voos = new VooDBHelper(this);
		setTitle("Menu Voo");
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
     	voos.close();
    }
	
	 @Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Serializable list = (Serializable)voos.selectVoos();
			
			switch (position) {
			case 0: // Visualizar 
				Intent intentVisualizar = new Intent(this, VisualizarActivityVoo.class);
				intentVisualizar.putExtra(TableVoo.TNAME, list);
				intentVisualizar.putExtra("tipo", "visualizar");
				startActivity(intentVisualizar);
				break;

			case 1: // Inserir
				startActivity(new Intent(this,InsertActivityVoo.class));
				break;
			
			case 2: // Alterar
				Intent intentAlterar = new Intent(this, VisualizarActivityVoo.class);
				intentAlterar.putExtra(TableVoo.TNAME, list);
				intentAlterar.putExtra("tipo", "alterar");
				startActivity(intentAlterar);
				break;
				
			case 3: // Excluir
				Intent intentExcluir = new Intent(this, VisualizarActivityVoo.class);
				intentExcluir.putExtra(TableVoo.TNAME, list);
				intentExcluir.putExtra("tipo", "excluir");
				startActivity(intentExcluir);
				break;
				
			default:
				break;
			}
		}

}
