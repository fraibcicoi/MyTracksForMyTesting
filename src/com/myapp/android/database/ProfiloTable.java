package com.myapp.android.database;

import android.provider.BaseColumns;

public interface ProfiloTable extends BaseColumns
{
	String TABLE_NAME = "ProfiloTable";

	String EMAIL = "email";
	
	String NOME= "nome";
	
	String PASSWORD = "password";

	String NICKNAME = "nickname";
	
	String HEIGHT =	"height";
	
	String WEIGHT = "weight";
	
	String GOAL_WEIGHT = "goalWeight";
	
	String DAY_OF_BIRHT = "dayOfBirth";
	
	String MONTH_OF_BIRHT = "monthOfBirth";
	
	String YEAR_OF_BIRHT = "yearOfBirth";
	
	String SEX = "sex";
	
	String REMEMBER_ME= "rememberMe";
	
	String SCORE= "score";
    

	String[] COLUMNS = new String[]
	{ _ID, EMAIL, PASSWORD, NICKNAME, HEIGHT, WEIGHT, DAY_OF_BIRHT, MONTH_OF_BIRHT,YEAR_OF_BIRHT, SEX, REMEMBER_ME,NOME, GOAL_WEIGHT, SCORE };
}
