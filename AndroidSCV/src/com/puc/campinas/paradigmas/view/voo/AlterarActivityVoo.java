package com.puc.campinas.paradigmas.view.voo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.puc.campinas.paradigmas.controll.DataConversor;
import com.puc.campinas.paradigmas.controll.VooDBHelper;
import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.StatusVoo;
import com.puc.campinas.paradigmas.model.Voo;
import com.puc.campinas.paradigmas.model.tables.TableVoo;

public class AlterarActivityVoo extends InsertActivityVoo implements OnClickListener {

	private Voo voo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.insere_altera_voo);
		setTitle("Alterar Voo");

		super.buttonInserir.setText("Alterar Voo");
		super.buttonInserir.setOnClickListener(this);
		
		Intent intent = getIntent();
		if (intent != null) 
		{
			this.voo = (Voo) intent.getSerializableExtra(TableVoo.TNAME);
			
			this.editDistancia.setText(voo.getDistancia()+"");
			this.editPreco.setText(voo.getPreco()+"");
			super.dateTimeLabel.setText(DataConversor.dateToStr(voo.getDataHora().getTime()));
			int position = 0;
			
			for(StatusVoo op : StatusVoo.values()){
				if(op.getStatus().equals(voo.getStatus()))
				{
					this.cbxStatus.setSelection(position);
					break;
				}
				position++;
			}
			
			position = 0;
			for(Aeroporto a : super.aeroportos)
			{
				if(a.getIata().equals(voo.getDestino().getIata()))
				{
					this.cbxDestino.setSelection(position);
					break;
				}
				position++;
			}
			
			position = 0;
			for(Aeroporto a : super.aeroportos)
			{
				if(a.getIata().equals(voo.getOrigem().getIata()))
				{
					this.cbxOrigem.setSelection(position);
					break;
				}
				position++;
			}
			


		}
	}

	@Override
	public void onClick(View v) 
	{
		db = new VooDBHelper(this);
		boolean resultado = false;
		
		Voo alterado = new Voo();
		alterado.setId(this.voo.getId());
		alterado.setDistancia(Double.parseDouble(this.editDistancia.getText().toString()));
		alterado.setDataHora(DataConversor.strToGregorianCalendar(super.dateTime.getTime()));
		alterado.setOrigem((Aeroporto)this.cbxOrigem.getSelectedItem());
		alterado.setDestino((Aeroporto) this.cbxDestino.getSelectedItem());
		alterado.setPreco(Double.parseDouble(this.editPreco.getText().toString()));
		alterado.setStatus((String)this.cbxStatus.getSelectedItem());
		
		resultado = db.alterarVoo(alterado);
		
		if (resultado == true) 
		{
			Toast.makeText(this, "O Voo foi alterado com sucesso.", Toast.LENGTH_LONG).show();
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
