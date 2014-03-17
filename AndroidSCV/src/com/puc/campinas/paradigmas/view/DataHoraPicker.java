package com.puc.campinas.paradigmas.view;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.puc.campinas.paradigmas.R;
import com.puc.campinas.paradigmas.controll.DataConversor;

public class DataHoraPicker extends FragmentActivity  {
	protected DateFormat df = DateFormat.getDateTimeInstance();
	protected TextView dateTimeLabel;
	protected Calendar dateTime=Calendar.getInstance();
	
	DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateTime.set(Calendar.YEAR, year);
			dateTime.set(Calendar.MONTH, monthOfYear);
			dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}
	};  
	
	TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay,
				int minute) {
			dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateTime.set(Calendar.MINUTE, minute);
			updateLabel();
		}
	};  

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.insere_altera_voo);

		Button btn=(Button)findViewById(R.id.btnMudarDataVoo);

		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(DataHoraPicker.this,
						d,
						dateTime.get(Calendar.YEAR),
						dateTime.get(Calendar.MONTH),
						dateTime.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		btn=(Button)findViewById(R.id.btnMudarHoraVoo);

		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new TimePickerDialog(DataHoraPicker.this,
						t,
						dateTime.get(Calendar.HOUR_OF_DAY),
						dateTime.get(Calendar.MINUTE),
						true).show();
			}
		});

		dateTimeLabel=(TextView)findViewById(R.id.textDataHora);

		updateLabel();
	}

	private void updateLabel() {
		dateTimeLabel.setText(DataConversor.dateToStr(dateTime.getTime()));
	}
}