package com.puc.campinas.paradigmas.view.voo;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.puc.campinas.paradigmas.R;
import com.puc.campinas.paradigmas.controll.AeroportoDBHelper;
import com.puc.campinas.paradigmas.controll.DataConversor;
import com.puc.campinas.paradigmas.controll.VooDBHelper;
import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.StatusVoo;
import com.puc.campinas.paradigmas.model.Voo;
import com.puc.campinas.paradigmas.view.DataHoraPicker;

public class InsertActivityVoo extends DataHoraPicker implements OnClickListener {
	
	protected EditText editDistancia;
	protected EditText editPreco;
	protected Spinner cbxOrigem;
	protected Spinner cbxDestino;
	protected Spinner cbxStatus;
	protected Button buttonInserir;
	protected VooDBHelper db;
	
	protected AeroportoDBHelper dbAeroporto;
	protected ArrayAdapter<?> adp;
	protected List<Aeroporto> aeroportos;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Inserir Voo");
		this.editDistancia = (EditText)findViewById(R.id.editDistancia);
		this.cbxOrigem = (Spinner) findViewById(R.id.cbxOrigem);
		this.cbxDestino = (Spinner) findViewById(R.id.cbxDestino);
		this.cbxStatus = (Spinner) findViewById(R.id.cbxStatus);
		this.editPreco = (EditText)findViewById(R.id.editPreco);
		
		dbAeroporto = new AeroportoDBHelper(this);
		aeroportos = dbAeroporto.selectAeroportos();
        adp = new ArrayAdapter<Aeroporto>(this, android.R.layout.simple_spinner_item, aeroportos);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        
        this.cbxOrigem.setAdapter(adp);
        this.cbxDestino.setAdapter(adp);
        this.cbxDestino.setSelection(1);
        
        String status[] = new String[9];
		int i = 0;
		for(StatusVoo op : StatusVoo.values()){
			status[i] = op.getStatus();
			i++;
		}
		
		adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);
		adp.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		this.cbxStatus.setAdapter(adp);
        
		buttonInserir = (Button)findViewById(R.id.btnInserirAlterarVoo);
		buttonInserir.setText("Inserir Voo");
		buttonInserir.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View arg0) {
		Voo v = new Voo();
		db = new VooDBHelper(this);
		long id = 0;
		
		v.setDistancia(Double.parseDouble(this.editDistancia.getText().toString()));
		v.setDataHora(DataConversor.strToGregorianCalendar(super.dateTime.getTime()));
		v.setOrigem((Aeroporto)this.cbxOrigem.getSelectedItem());
		v.setDestino((Aeroporto) this.cbxDestino.getSelectedItem());
		v.setPreco(Double.parseDouble(this.editPreco.getText().toString()));
		v.setStatus((String)this.cbxStatus.getSelectedItem());
		
		if(!v.getOrigem().getIata().equals(v.getDestino().getIata()))
			id = db.insertVoo(v);
		else
			id = -1;
		
		if (id != -1) 
		{
			Toast.makeText(this, "O Voo foi inserido com sucesso.", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "O Voo não foi inserido.", Toast.LENGTH_LONG).show();
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
