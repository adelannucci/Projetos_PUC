package com.puc.campinas.paradigmas.controll;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.puc.campinas.paradigmas.model.tables.TableAeroporto;
import com.puc.campinas.paradigmas.model.tables.TableVoo;

public class DBHelper extends SQLiteOpenHelper {
	
	public static final String TAG = "SQLiteHelper";
	public static final String DATABASE_NAME = "SCV";
	public static final int DATABASE_VERSION = 1;


	public DBHelper(Context context, String databaseName, int databaseVersion){
		super(context, databaseName, null, databaseVersion);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TableAeroporto.SCRIPT_CREATE);
		db.execSQL(TableVoo.SCRIPT_CREATE);
		//db.execSQL(TableCiaAerea.SCRIPT_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			// Exclui a tabela anterior
			db.execSQL(TableAeroporto.SCRIPT_DELETE);
			db.execSQL(TableVoo.SCRIPT_DELETE);
			//db.execSQL(TableCiaAerea.SCRIPT_DELETE);

			onCreate(db);
		} catch (SQLException e) {
			Log.e(TAG, e.toString());
		}
	}

}
