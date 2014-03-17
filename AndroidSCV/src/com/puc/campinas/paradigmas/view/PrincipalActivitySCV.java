package com.puc.campinas.paradigmas.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.puc.campinas.paradigmas.view.aeroporto.PricipalActivityAeroporto;
import com.puc.campinas.paradigmas.view.cia.PrincipalActivityCia;
import com.puc.campinas.paradigmas.view.voo.PricipalActivityVoo;

public class PrincipalActivitySCV extends ListActivity implements OnItemClickListener {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
 
        String[] menu = new String[] {"Cia. Aerea", "Aeroportos", "Voos", "Sair"};
        
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
		
		switch (position) {
		case 0: // Cia Aerea 
			startActivity(new Intent(this,PrincipalActivityCia.class));
			break;

		case 1: // Aeroporto
			startActivity(new Intent(this,PricipalActivityAeroporto.class));
			break;
		
		case 2: // Voo
			startActivity(new Intent(this,PricipalActivityVoo.class));
			break;
			
		case 3: // Sair
			finish();
			break;
			
		default:
			break;
		}
	}

}
