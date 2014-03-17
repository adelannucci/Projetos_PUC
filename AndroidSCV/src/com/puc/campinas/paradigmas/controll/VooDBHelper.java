package com.puc.campinas.paradigmas.controll;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.puc.campinas.paradigmas.model.Voo;
import com.puc.campinas.paradigmas.model.tables.TableVoo;

public class VooDBHelper 
{
	
	private static final String TAG = "VooDBHelper";
	private DBHelper dbHelper;;
	private SQLiteDatabase db;
	private AeroportoDBHelper aeroportoHelper;
	private List<Voo> list;	
	
	public VooDBHelper(Context ctx)
	{
		try
		{
			this.dbHelper =  new DBHelper(ctx, DBHelper.DATABASE_NAME, DBHelper.DATABASE_VERSION);
			this.list = new ArrayList<Voo>();
			this.db = dbHelper.getWritableDatabase();
			this.aeroportoHelper = new AeroportoDBHelper(ctx);

		}
		catch (SQLiteException e) 
		{
			Log.e(TAG, e.toString());
		}
	}
	
	public void close()
	{
		if (db != null) 
		{
			if (db.isOpen() == true) 
			{
				db.close();
			}
		}

	}
	
	public List<Voo> selectVoos()
	{
		Cursor cursor = null;
		list.clear();
		try 
		{
			cursor = db.query(TableVoo.TNAME, new Voo().getVooColumns(), null, null, null, null, null);
			
			if (cursor.getCount() > 0) {
				while (cursor.moveToNext()) {
					Voo v = new Voo();
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.ID))) {
						v.setId(cursor.getInt(cursor.getColumnIndex(TableVoo.ID)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.DISTANCIA))) {
						v.setDistancia(cursor.getDouble(cursor.getColumnIndex(TableVoo.DISTANCIA)));						
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.DATA))) {
						String dataHora = (cursor.getString(cursor.getColumnIndex(TableVoo.DATA)));
						v.setDataHora(DataConversor.strToGregorianCalendar(dataHora));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.IATA_ORIGEM))) {
						String iataOrigem = cursor.getString(cursor.getColumnIndex(TableVoo.IATA_ORIGEM));
						v.setOrigem(aeroportoHelper.findByIATA(iataOrigem));
						
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.IATA_DESTINO))) {
						String iataDestino = cursor.getString(cursor.getColumnIndex(TableVoo.IATA_DESTINO));
						v.setDestino(aeroportoHelper.findByIATA(iataDestino));
						
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.STATUS))) {
						String status = cursor.getString(cursor.getColumnIndex(TableVoo.STATUS));
						v.setStatus(status);
						
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableVoo.PRECO))) {
						Double preco = cursor.getDouble(cursor.getColumnIndex(TableVoo.PRECO));
						v.setPreco(preco);
						
					}

					this.list.add(v);
				}
			}
			
		} 
		catch (SQLiteException e) 
		{
			Log.e(TAG, e.toString());
		}
		finally
		{
			if (cursor != null) 
			{
				if (!cursor.isClosed()) 
				{
					cursor.close();
				}
			}
		}
		
		return list;
	}
	
	private ContentValues contentVoo(Voo v)
	{
		
		ContentValues values = new ContentValues();
		
		values.put(TableVoo.DATA, DataConversor.dateToStr(v.getDataHora().getTime()));
		values.put(TableVoo.IATA_ORIGEM, v.getOrigem().getIata());
		values.put(TableVoo.IATA_DESTINO, v.getDestino().getIata());
		values.put(TableVoo.STATUS, v.getStatus());
		values.put(TableVoo.DISTANCIA, v.getDistancia());
		values.put(TableVoo.PRECO, v.getPreco());

		return values;
	}
	
	/**
	 * Insere um Voo.
	 * @param aeroporto
	 * @return Retorna o id do livro inserido ou -1 se ocorrer erro na inserção do livro.
	 */
	public long insertVoo(Voo v)
	{
		long id = 0;
		
		try 
		{
			ContentValues values = contentVoo(v);
			id = db.insert(TableVoo.TNAME, "", values);
			
		} 
		catch (SQLiteException e) 
		{
			Log.e(TAG, e.toString());
		}
		
		return id;
	}
	
	/**
	 * Exclui um Voo.
	 * @param id Id do Voo a ser excluído
	 * @return
	 */
	public boolean excluirVoo(String id){
		boolean resultado = false;
		try 
		{
			String where = TableVoo.ID+"=?";
			String[] args = new String[]{id};
			
			int num = db.delete(TableVoo.TNAME, where, args);
			
			// Verifica se o Voo foi excluído
			if (num == 1) 
			{
				resultado = true;
			}
			
		} 
		catch (SQLiteException e) 
		{
			Log.e(TAG, e.toString());
		}
		
		return resultado;
	}
	
	public boolean alterarVoo(Voo voo){
		boolean resultado = false;
		try {
			
			String where = TableVoo.ID+"=?";
			String[] args = new String[]{String.valueOf(voo.getId())};
			
			int num = db.update(TableVoo.TNAME, contentVoo(voo), where, args);
			
			// Verifica se o voo foi alterado
			if (num == 1) 
			{
				resultado = true;
			}
			
		} 
		catch (SQLiteException e) 
		{
			Log.e(TAG, e.toString());
		}
		
		return resultado;
	}

}
