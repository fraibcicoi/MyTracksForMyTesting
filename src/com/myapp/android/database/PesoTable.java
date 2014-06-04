package com.myapp.android.database;

import android.provider.BaseColumns;

public interface PesoTable extends BaseColumns
{
	String TABLE_NAME = "PesoTable";

	String PROFILO_ID = "profiloId";
	
	String DATE= "date";
	
	String WEIGHT = "weight";

	String[] COLUMNS = new String[]
	    
	{ _ID, PROFILO_ID, DATE, WEIGHT};
}
