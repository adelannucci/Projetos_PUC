package com.puc.campinas.paradigmas.view.aeroporto;

import com.puc.campinas.paradigmas.R;
import com.puc.campinas.paradigmas.controll.AeroportoDBHelper;
import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.tables.TableAeroporto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarActivityAeroporto extends Activity implements OnClickListener {
	private EditText editIATA;
	private EditText editAeroporto;
	private EditText editCidade;
	private EditText editPais;
	private EditText editTaxaN;
	private EditText editTaxaE;
	private Button buttonAlterar;
	private AeroportoDBHelper db;
	private Aeroporto aeroporto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.insere_altera_aeroporto);
		setTitle("Alterar Aeroporto");
		this.editIATA = (EditText)findViewById(R.id.editIata);
		this.editAeroporto = (EditText)findViewById(R.id.editAeroporto);
		this.editCidade = (EditText)findViewById(R.id.editCidade);
		this.editPais = (EditText)findViewById(R.id.editPais);
		this.editTaxaN = (EditText)findViewById(R.id.editTaxaN);
		this.editTaxaE = (EditText)findViewById(R.id.editTaxaE);
		buttonAlterar = (Button)findViewById(R.id.btnInserirAlterarAeroporto);
		buttonAlterar.setText("Alterar Aeroporto");
		buttonAlterar.setOnClickListener(this);
		
		Intent intent = getIntent();
		if (intent != null) 
		{
			this.aeroporto = (Aeroporto) intent.getSerializableExtra(TableAeroporto.TNAME);
			// Exibe as informações do livro selecionado
			this.editIATA.setText(this.aeroporto.getIata());
			this.editAeroporto.setText(this.aeroporto.getNome());
			this.editCidade.setText(this.aeroporto.getCidade());
			this.editPais.setText(this.aeroporto.getPais());
			this.editTaxaN.setText(this.aeroporto.getTxNacional()+"");
			this.editTaxaE.setText(this.aeroporto.getTxEstrange()+"");
		}
	}

	@Override
	public void onClick(View v) 
	{
		db = new AeroportoDBHelper(this);
		boolean resultado = false;
		
		Aeroporto alterado = new Aeroporto();
		// O id do livro com os dados alterados permanece o mesmo
		alterado.setId(this.aeroporto.getId());
		alterado.setIata(this.editIATA.getText().toString());
		alterado.setNome(this.editAeroporto.getText().toString());
		alterado.setCidade(this.editCidade.getText().toString());
		alterado.setPais(this.editPais.getText().toString());
		alterado.setTxNacional(Double.parseDouble(this.editTaxaN.getText().toString()));
		alterado.setTxEstrange(Double.parseDouble(this.editTaxaE.getText().toString()));
		
		resultado = db.alterarAeroporto(alterado);
		
		if (resultado == true) 
		{
			Toast.makeText(this, "O Aeroporto foi alterado com sucesso.", Toast.LENGTH_LONG).show();
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
