package com.myapp.android.database;

import android.provider.BaseColumns;

public interface ProvinciaTable extends BaseColumns
{
	String TABLE_NAME = "province";

	String CODICE = "codice";

	String NOME = "nome";

	String[] COLUMNS = new String[]
	{ _ID, CODICE, NOME };
}
