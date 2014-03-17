package com.puc.campinas.paradigmas.view.aeroporto;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.puc.campinas.paradigmas.controll.AeroportoDBHelper;
import com.puc.campinas.paradigmas.controll.VooDBHelper;
import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.Voo;
import com.puc.campinas.paradigmas.model.tables.TableAeroporto;

public class VisualizarActivityAeroporto extends ListActivity implements OnItemClickListener {
	private ListView listView;
	private String tipo; // tipo de activity a ser chamada
	private AeroportoDBHelper db;
	List<Aeroporto> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		db = new AeroportoDBHelper(this);		
		listView = getListView();
		listView.setOnItemClickListener(this);
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		super.onResume();		
		
		String[] titulos = null;
		
		Intent intent = getIntent();
		
		if (intent != null) {
			list = (ArrayList<Aeroporto>) intent.getSerializableExtra(TableAeroporto.TNAME);
			
			titulos = new String[list.size()];
			int i = 0;
			for (Aeroporto a : list) {
				titulos[i] = a.getIata();
				i++;
			}
			
			ArrayAdapter<?> adp = new ArrayAdapter<Aeroporto>(this, android.R.layout.simple_list_item_1, list);
			this.setListAdapter(adp);
			
			//setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, titulos));

			// Define o tipo de Activity a ser chamada
			tipo = intent.getStringExtra("tipo");
			
			if (tipo.equals("alterar")) {
				setTitle("Selecione o aeroporto para alterar");
			}else if (tipo.equals("excluir")) {
				setTitle("Selecione o aeroporto para excluir");
			}
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		if (tipo.equals("alterar")) {
			
			// Armazena as informações do livro selecionado
			Aeroporto a = list.get(position);
			// Chama a tela de Alterar passando as informações do livro
			// como parâmetro
			Intent intent = new Intent(this, AlterarActivityAeroporto.class);
			intent.putExtra(TableAeroporto.TNAME, a);
			startActivity(intent);
		}
		
		if (tipo.equals("excluir")) {
			Aeroporto a = (Aeroporto) listView.getItemAtPosition(position);
			VooDBHelper bdV = new VooDBHelper(this);
			List<Voo> voos = bdV.selectVoos();
			boolean excluir = true;
			
			for(Voo v: voos)
			{
				if(v.getDestino().getIata().equals(a.getIata()))
				{
					excluir = false;
					break;
				}
				
				if(v.getOrigem().getIata().equals(a.getIata()))
				{
					excluir = false;
					break;
				}
			}
			
			if(excluir)
				excluir = db.excluirAeroporto(a.getIata());
			
			if (excluir) {
				Toast.makeText(this, "O Aeroporto " + a.getIata() + " foi excluído com sucesso.",
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "O Aeroporto " + a.getIata() + " não foi excluído.",
						Toast.LENGTH_SHORT).show();
			}
		}
		finish();
	}
		
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		if (db != null) 
		{
			db.close();
		}
	}

}