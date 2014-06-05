package com.myapp.android.database;

import android.provider.BaseColumns;

public interface SfidaCorsaTable extends BaseColumns
{
	String TABLE_NAME = "SfidaCorsaTable";

	String FREQUENZA = "frequenza";
	
	String DURATA = "durata";

	String DISTANZAMINIMA = "distanzaMinima";
	
	String SCOMMESSA =	"scommessa";
	
	String VINCITA = "vincita";
	
	String ID_GIOCATORE="idGiocatore";

   String DATA_INIZIO="DataInizio";
   
   String STATO="stato";


	
	String[] COLUMNS = new String[]
	{ _ID, FREQUENZA, DURATA, DISTANZAMINIMA, SCOMMESSA, VINCITA, ID_GIOCATORE , DATA_INIZIO, STATO};
}
