package com.puc.campinas.paradigmas.controll;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.puc.campinas.paradigmas.model.Aeroporto;
import com.puc.campinas.paradigmas.model.tables.TableAeroporto;

public class AeroportoDBHelper {
	
	private static final String TAG = "AeroportoDBHelper";
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private List<Aeroporto> list;	
	
	public AeroportoDBHelper(Context ctx)
	{
		try
		{
			this.dbHelper =  new DBHelper(ctx, DBHelper.DATABASE_NAME,DBHelper.DATABASE_VERSION);
			this.list = new ArrayList<Aeroporto>();
			
			// Cria ou abre a base de dados
			
			db = dbHelper.getWritableDatabase();
	
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
	
	public List<Aeroporto> selectAeroportos()
	{
		Cursor cursor = null;
		list.clear();
		try 
		{
			cursor = db.query(TableAeroporto.TNAME, new Aeroporto().getAeroportosColumns(), null, null, null, null, TableAeroporto.IATA+" ASC");
			
			if (cursor.getCount() > 0) {
				while (cursor.moveToNext()) {
					Aeroporto a = new Aeroporto();
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.ID))) {
						a.setId(cursor.getInt(cursor.getColumnIndex(TableAeroporto.ID)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.IATA))) {
						a.setIata(cursor.getString(cursor.getColumnIndex(TableAeroporto.IATA)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.NOME))) {
						a.setNome(cursor.getString(cursor.getColumnIndex(TableAeroporto.NOME)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.CIDADE))) {
						a.setCidade(cursor.getString(cursor.getColumnIndex(TableAeroporto.CIDADE)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.PAIS))) {
						a.setPais(cursor.getString(cursor.getColumnIndex(TableAeroporto.PAIS)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.TAXA_NACIONAL))) {
						a.setTxNacional(cursor.getDouble(cursor.getColumnIndex(TableAeroporto.TAXA_NACIONAL)));
					}
					
					if (!cursor.isNull(cursor.getColumnIndex(TableAeroporto.TAXA_ESTRANG))) {
						a.setTxEstrange(cursor.getDouble(cursor.getColumnIndex(TableAeroporto.TAXA_ESTRANG)));
					}
					
					this.list.add(a);
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
	
	private ContentValues contentAeroporto(Aeroporto a)
	{
		
		ContentValues values = new ContentValues();
		values.put(TableAeroporto.IATA, a.getIata());
		values.put(TableAeroporto.NOME, a.getNome());
		values.put(TableAeroporto.CIDADE.toString(), a.getCidade());
		values.put(TableAeroporto.PAIS, a.getPais());
		values.put(TableAeroporto.TAXA_NACIONAL, a.getTxNacional());
		values.put(TableAeroporto.TAXA_ESTRANG, a.getTxEstrange());

		return values;
	}
	
	/**
	 * Insere um Aeroporto.
	 * @param aeroporto
	 * @return Retorna o id do livro inserido ou -1 se ocorrer erro na inserção do livro.
	 */
	public long insertAeroporto(Aeroporto aeroporto)
	{
		long id = 0;
		
		try 
		{
			ContentValues values = contentAeroporto(aeroporto);
			id = db.insert(TableAeroporto.TNAME, "", values);
			
		} 
		catch (SQLiteException e) 
		{
			Log.e(TAG, e.toString());
		}
		
		return id;
	}
	
	/**
	 * Exclui um Aeroporto.
	 * @param iata IATA do Aeroporto a ser excluído
	 * @return
	 */
	public boolean excluirAeroporto(String iata){
		boolean resultado = false;
		try 
		{
			String where = TableAeroporto.IATA+"=?";
			String[] args = new String[]{iata};
			
			int num = db.delete(TableAeroporto.TNAME, where, args);
			
			// Verifica se o Aeroporto foi excluído
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
	
	public boolean alterarAeroporto(Aeroporto aeroporto){
		boolean resultado = false;
		try {
			
			String where = TableAeroporto.ID+"=?";
			String[] args = new String[]{String.valueOf(aeroporto.getId())};
			
			int num = db.update(TableAeroporto.TNAME, contentAeroporto(aeroporto), where, args);
			
			// Verifica se o livro foi alterado
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
	
	public Aeroporto findByIATA(String iata)
	{
		Aeroporto a = null;
		String where = TableAeroporto.IATA+"='"+iata+"'";
		Cursor c = db.query(TableAeroporto.TNAME, new Aeroporto().getAeroportosColumns(), where, null, null, null, null);
		if(c.moveToNext())
		{
			a = new Aeroporto();
			a.setId(c.getInt(0));
			a.setIata(c.getString(1));
			a.setNome(c.getString(2));
			a.setCidade(c.getString(3));
			a.setPais(c.getString(4));
			a.setTxNacional(c.getDouble(5));
			a.setTxEstrange(c.getDouble(6));
		}
		
		return a;
	}

}
