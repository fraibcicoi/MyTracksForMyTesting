package com.myapp.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private SQLiteDatabase database;
	private static final String DATABASE_NAME = "MyApp5.db";

	private static final int SCHEMA_VERSION = 1;

	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	onCreateMy(getWritableDatabase());
	}
	
	
	public void onCreateMy(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE IF NOT EXISTS {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} TEXT NOT NULL,{3} TEXT NOT NULL);";
		db.execSQL(MessageFormat.format(sql, ProvinciaTable.TABLE_NAME, ProvinciaTable._ID, ProvinciaTable.CODICE, ProvinciaTable.NOME));
		
		String sql1 = "CREATE TABLE IF NOT EXISTS {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} INTEGER NOT NULL,{3} INTEGER NOT NULL,{4} INTEGER NOT NULL ,{5} REAL NOT NULL ,{6} REAL NOT NULL);";
		db.execSQL(MessageFormat.format(sql1, SfidaCorsaTable.TABLE_NAME, SfidaCorsaTable._ID, SfidaCorsaTable.FREQUENZA, SfidaCorsaTable.DURATA, SfidaCorsaTable.DISTANZAMINIMA, SfidaCorsaTable.SCOMMESSA,SfidaCorsaTable.VINCITA));
		
		String sql2 = "CREATE TABLE IF NOT EXISTS {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} TEXT NOT NULL,{3} TEXT NOT NULL,{4} TEXT NOT NULL ,{5} REAL NOT NULL ,{6} REAL NOT NULL, {7} INTEGER NOT NULL, {8} INTEGER NOT NULL, {9} INTEGER NOT NULL, {10} INTEGER NOT NULL, {11} INTEGER NOT NULL, {12} TEXT NOT NULL, {13} REAL);";
		db.execSQL(MessageFormat.format(sql2, ProfiloTable.TABLE_NAME, ProfiloTable._ID, ProfiloTable.EMAIL, ProfiloTable.PASSWORD, ProfiloTable.NICKNAME, ProfiloTable.HEIGHT, ProfiloTable.WEIGHT, ProfiloTable.DAY_OF_BIRHT, ProfiloTable.MONTH_OF_BIRHT, ProfiloTable.YEAR_OF_BIRHT, ProfiloTable.SEX, ProfiloTable.REMEMBER_ME, ProfiloTable.NOME, ProfiloTable.GOAL_WEIGHT));
	
		String sql3 = "CREATE TABLE IF NOT EXISTS {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} INTEGER NOT NULL,{3} TEXT NOT NULL,{4} REAL NOT NULL);";
	        db.execSQL(MessageFormat.format(sql3, PesoTable.TABLE_NAME, PesoTable._ID, PesoTable.PROFILO_ID, PesoTable.DATE, PesoTable.WEIGHT));
	    	
		
	
	
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		Log.d("test", "creo database");
		String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} TEXT NOT NULL,{3} TEXT NOT NULL);";
		db.execSQL(MessageFormat.format(sql, ProvinciaTable.TABLE_NAME, ProvinciaTable._ID, ProvinciaTable.CODICE, ProvinciaTable.NOME));
		
		String sql1 = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} INTEGER NOT NULL,{3} INTEGER NOT NULL,{4} INTEGER NOT NULL ,{5} REAL NOT NULL ,{6} REAL NOT NULL);";
		db.execSQL(MessageFormat.format(sql1, SfidaCorsaTable.TABLE_NAME, SfidaCorsaTable._ID, SfidaCorsaTable.FREQUENZA, SfidaCorsaTable.DURATA, SfidaCorsaTable.DISTANZAMINIMA, SfidaCorsaTable.SCOMMESSA,SfidaCorsaTable.VINCITA));
		
		String sql2 = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} TEXT NOT NULL,{3} TEXT NOT NULL,{4} TEXT NOT NULL ,{5} REAL NOT NULL ,{6} REAL NOT NULL, {7} INTEGER NOT NULL, {8} INTEGER NOT NULL, {9} INTEGER NOT NULL, {10} INTEGER NOT NULL, {11} INTEGER NOT NULL, {12} TEXT NOT NULL, {13} REAL);";
		db.execSQL(MessageFormat.format(sql2, ProfiloTable.TABLE_NAME, ProfiloTable._ID, ProfiloTable.EMAIL, ProfiloTable.PASSWORD, ProfiloTable.NICKNAME, ProfiloTable.HEIGHT, ProfiloTable.WEIGHT, ProfiloTable.DAY_OF_BIRHT, ProfiloTable.MONTH_OF_BIRHT, ProfiloTable.YEAR_OF_BIRHT, ProfiloTable.SEX, ProfiloTable.REMEMBER_ME, ProfiloTable.NOME, ProfiloTable.GOAL_WEIGHT));
		  String sql3 = "CREATE TABLE IF NOT EXISTS {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} INTEGER NOT NULL,{3} TEXT NOT NULL,{4} REAL NOT NULL);";
          db.execSQL(MessageFormat.format(sql3, PesoTable.TABLE_NAME, PesoTable._ID, PesoTable.PROFILO_ID, PesoTable.DATE, PesoTable.WEIGHT));
  
		
		database=db;
		inserisciProvince(db);
	
	
	}

	protected void inserisciProvince(SQLiteDatabase db)
	{
		
		
		inserisciProvincia(db, "Agrigento", "AG");
	}

	private void inserisciProvincia(SQLiteDatabase db, String nome, String codice)
	{
		ContentValues v = new ContentValues();
		v.put(ProvinciaTable.CODICE, codice);
		v.put(ProvinciaTable.NOME, nome);
		db.insert(ProvinciaTable.TABLE_NAME, null, v);
	}

	public void inserisciSfidaCorsa(int frequenza, int durata,int distanzaMinima, float vincita, float scommessa)
	{
		ContentValues v = new ContentValues();
		v.put(SfidaCorsaTable.FREQUENZA, frequenza);
		v.put(SfidaCorsaTable.DURATA, durata);
		v.put(SfidaCorsaTable.DISTANZAMINIMA, distanzaMinima);
		v.put(SfidaCorsaTable.SCOMMESSA, scommessa);
		v.put(SfidaCorsaTable.VINCITA, vincita);
			

		getReadableDatabase().insert(SfidaCorsaTable.TABLE_NAME, null, v);
	}
	
	public void inserisciProfilo(String email, String password, String nickname, float height, float weight, int dayOfBirth, int monthOfBirth, int yearOfBirth, int sex, int remeberMe, String nome )
	{
		ContentValues v = new ContentValues();
		v.put(ProfiloTable.EMAIL, email);
		v.put(ProfiloTable.PASSWORD, password);
		v.put(ProfiloTable.NICKNAME, nickname);
		v.put(ProfiloTable.HEIGHT, height);
		v.put(ProfiloTable.WEIGHT, weight);
		v.put(ProfiloTable.DAY_OF_BIRHT, dayOfBirth);
		v.put(ProfiloTable.MONTH_OF_BIRHT, monthOfBirth);
		v.put(ProfiloTable.YEAR_OF_BIRHT, yearOfBirth);
		v.put(ProfiloTable.SEX, sex);
		v.put(ProfiloTable.REMEMBER_ME, remeberMe);
		v.put(ProfiloTable.NOME, nome);
		getReadableDatabase().insert(ProfiloTable.TABLE_NAME, null, v);
Cursor utente=getProfilo(nickname, password);
int id= utente.getInt(utente.getColumnIndex(ProfiloTable._ID));

String data=(new Date().getTime())+"";
ContentValues v1 = new ContentValues();
v1.put(PesoTable.PROFILO_ID, id);
v1.put(PesoTable.DATE, data);

v1.put(PesoTable.WEIGHT, weight);
getReadableDatabase().insert(PesoTable.TABLE_NAME, null, v1);
	}
	
	
	public void inserisciNuovoPeso(int id, float weight)
	{
	  String data=new Date().getTime()+"";
	  ContentValues v = new ContentValues();
	  v.put(PesoTable.PROFILO_ID, id);
      v.put(PesoTable.DATE, data);
      v.put(PesoTable.WEIGHT, weight);
      getReadableDatabase().insert(PesoTable.TABLE_NAME, null, v);
      
      
      ContentValues v1 = new ContentValues();
      
      v1.put(ProfiloTable.WEIGHT, weight);
      
      getReadableDatabase().update(ProfiloTable.TABLE_NAME, v1, ProfiloTable._ID+"=?", new String[] { id+"" });
      

	  
	}

	public void inserisciNuovoObiettivoPeso(int id, float weight)
    {
      
      
      ContentValues v1 = new ContentValues();
      
      v1.put(ProfiloTable.GOAL_WEIGHT, weight);
      
      getReadableDatabase().update(ProfiloTable.TABLE_NAME, v1, ProfiloTable._ID+"=?", new String[] { id+"" });
      

      
    }

	
	public void setRememberMe(String username, String password, int remeberMe)
	{
		//Cursor user= getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, "NICKNAME = "+username+" AND PASSWORD = "+password , null,
			//	null, null, null);
		
	//	user.moveToFirst();
		
		ContentValues v = new ContentValues();
		
		v.put(ProfiloTable.REMEMBER_ME, remeberMe);
		
		getReadableDatabase().update(ProfiloTable.TABLE_NAME, v, ProfiloTable.NICKNAME+"=? and "+ProfiloTable.PASSWORD+"=?", new String[] { username, password });
	}
	
	public boolean getRememberMe()
	{
					
		Cursor users = (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, null , null,
					null, null, null));
			try{
				
				users.moveToFirst();
			while(users.moveToNext())
		{
			if(users.getInt(11)==1)
			{
				return true;
			}
		}
		}
		finally{
		users.close();
		}
		
		
		return false;
	}
	
	public Cursor getProvince()
	{
		return (getReadableDatabase().query(ProvinciaTable.TABLE_NAME, ProvinciaTable.COLUMNS, "length(" + ProvinciaTable.NOME + ") > 10", null,
				null, null, ProvinciaTable.NOME));
	}

	public Cursor getSfidaCorsa()
	{
		return (getReadableDatabase().query( SfidaCorsaTable.TABLE_NAME, SfidaCorsaTable.COLUMNS, null , null,
				null, null, null));
	}

	public Cursor getProfilo(String username, String password)
	{
		//Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable.NICKNAME +" = " +username+" AND "+ ProfiloTable.PASSWORD+" = "+password , null,
	//			null, null, null));
		
		Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable.NICKNAME+"=? and "+ProfiloTable.PASSWORD+"=?", new String[] { username, password },
				null, null, null));
		
		test.moveToFirst();
		Log.d("test", "numero righe "+test.getCount() );
		
		return test;
	}
	public Cursor getProfilo(int ID)
    {
        //Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable.NICKNAME +" = " +username+" AND "+ ProfiloTable.PASSWORD+" = "+password , null,
    //          null, null, null));
        
        Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable._ID+"=?", new String[] { ID+"" },
                null, null, null));
        
        test.moveToFirst();
        Log.d("test", "numero righe "+test.getCount() );
        
        return test;
    }

	public Cursor getPesiUtente(int ID)
    {
        //Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable.NICKNAME +" = " +username+" AND "+ ProfiloTable.PASSWORD+" = "+password , null,
    //          null, null, null));
        
        Cursor test= (getReadableDatabase().query( PesoTable.TABLE_NAME, PesoTable.COLUMNS, PesoTable.PROFILO_ID+"=?", new String[] { ID+"" },
                null, null, null));
        
        test.moveToFirst();
        Log.d("test", "numero righe "+test.getCount() );
        
        return test;
    }
	public float getGoalWeight(int ID)
    {
        //Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable.NICKNAME +" = " +username+" AND "+ ProfiloTable.PASSWORD+" = "+password , null,
    //          null, null, null));
        
        Cursor test= (getReadableDatabase().query( ProfiloTable.TABLE_NAME, ProfiloTable.COLUMNS, ProfiloTable._ID+"=?", new String[] { ID+"" },
                null, null, null));
        
       if( test.moveToFirst())
       {    
        return test.getFloat(test.getColumnIndex(ProfiloTable.GOAL_WEIGHT));
    }
       return 0;
       }
	

	
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
	}
	
	  private String getDateTime() {
	    
	    
	long dataLong= new Date().getTime();
	
	
	  //  new Date( new Date().getTime());
	    
	    Date anotherDate=new Date(new Date().getTime());
	 //   new Date()
	    
        SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.getDefault());
       // Date date = new Date();
        return dateFormat.format(anotherDate);
}
}