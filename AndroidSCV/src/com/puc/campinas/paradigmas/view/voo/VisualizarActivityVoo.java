package com.puc.campinas.paradigmas.view.voo;

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

import com.puc.campinas.paradigmas.controll.VooDBHelper;
import com.puc.campinas.paradigmas.model.Voo;
import com.puc.campinas.paradigmas.model.tables.TableVoo;

public class VisualizarActivityVoo extends ListActivity implements OnItemClickListener {
	private ListView listView;
	private String tipo; // tipo de activity a ser chamada
	private VooDBHelper db;
	List<Voo> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		db = new VooDBHelper(this);		
		listView = getListView();
		listView.setOnItemClickListener(this);
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		super.onResume();		
				
		Intent intent = getIntent();
		
		if (intent != null) {
			list = (ArrayList<Voo>) intent.getSerializableExtra(TableVoo.TNAME);
			 ArrayAdapter<?> adp = new ArrayAdapter<Voo>(this, android.R.layout.simple_list_item_1, list);
			 this.setListAdapter(adp);
			// Define o tipo de Activity a ser chamada
			tipo = intent.getStringExtra("tipo");
			
			if (tipo.equals("alterar")) {
				setTitle("Selecione o Voo para alterar");
			}else if (tipo.equals("excluir")) {
				setTitle("Selecione o Voo para excluir");
			}
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		if (tipo.equals("alterar")) {
			
			// Armazena as informações do livro selecionado
			Voo v = list.get(position);
			// Chama a tela de Alterar passando as informações do livro
			// como parâmetro
			Intent intent = new Intent(this, AlterarActivityVoo.class);
			intent.putExtra(TableVoo.TNAME, v);
			startActivity(intent);
		}
		
		if (tipo.equals("excluir")) {
			Voo v = (Voo) listView.getItemAtPosition(position);
			boolean excluir = db.excluirVoo(v.getId()+"");
			
			if (excluir) {
				Toast.makeText(this, "O Voo " + v.getId() + " foi excluído com sucesso",
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "O Voo " + v.getId() + " não foi excluído com sucesso",
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
