package com.myapp.android.database;

import android.provider.BaseColumns;

public interface CorsaSingolaTable extends BaseColumns
{
	String TABLE_NAME = "CorsaSingolaTable";

	String ID_SFIDA = "IdSfida";
	
	String ID_CORSA_REGISTRATA = "IdCorsaRegistrata";
	
	String DATA = "data";

	String ESITO = "Esito";
	

	
	String[] COLUMNS = new String[]
	{ _ID, ID_SFIDA, DATA, ESITO, ID_CORSA_REGISTRATA};
}
