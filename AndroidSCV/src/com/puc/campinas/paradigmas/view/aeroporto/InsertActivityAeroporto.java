package com.puc.campinas.paradigmas.view.aeroporto;

import com.puc.campinas.paradigmas.R;
import com.puc.campinas.paradigmas.controll.AeroportoDBHelper;
import com.puc.campinas.paradigmas.model.Aeroporto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InsertActivityAeroporto extends Activity implements OnClickListener {
	private EditText editIATA;
	private EditText editAeroporto;
	private EditText editCidade;
	private EditText editPais;
	private EditText editTaxaN;
	private EditText editTaxaE;
	private Button buttonInserir;
	private AeroportoDBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.insere_altera_aeroporto);
		setTitle("Inserir Aeroporto");
		this.editIATA = (EditText)findViewById(R.id.editIata);
		this.editAeroporto = (EditText)findViewById(R.id.editAeroporto);
		this.editCidade = (EditText)findViewById(R.id.editCidade);
		this.editPais = (EditText)findViewById(R.id.editPais);
		this.editTaxaN = (EditText)findViewById(R.id.editTaxaN);
		this.editTaxaE = (EditText)findViewById(R.id.editTaxaE);
		buttonInserir = (Button)findViewById(R.id.btnInserirAlterarAeroporto);
		buttonInserir.setText("Inserir Aeroporto");
		buttonInserir.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		Aeroporto a = new Aeroporto();
		db = new AeroportoDBHelper(this);
		long id = 0;
		
		a.setIata(this.editIATA.getText().toString());
		a.setNome(this.editAeroporto.getText().toString());
		a.setCidade(this.editCidade.getText().toString());
		a.setPais(this.editPais.getText().toString());
		a.setTxNacional(Double.parseDouble(this.editTaxaN.getText().toString()));
		a.setTxEstrange(Double.parseDouble(this.editTaxaE.getText().toString()));
		
		id = db.insertAeroporto(a);
		
		if (id != -1) {
			Toast.makeText(this, "O Aeroporto foi inserido com sucesso.", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "O Aeroporto não foi inserido.", Toast.LENGTH_LONG).show();
		}
		
		db.close();
		
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