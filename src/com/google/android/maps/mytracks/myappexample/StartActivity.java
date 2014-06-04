package com.google.android.maps.mytracks.myappexample;


import com.google.android.maps.mytracks.R;
import com.myapp.android.database.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class StartActivity extends Activity {
	private boolean rememberMe=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
		
		
		
		setContentView(R.layout.activity_start);

//SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext() );
        
	Log.d("test", "sono qua");

	//	rememberMe = ((boolean)sharedPref.getBoolean(getString(R.string.saved_Remember_me), false));
		Log.d("test", "ora invece sono qua");
		DatabaseHelper databaseHelper= new DatabaseHelper(this);
		
		
		
		if(databaseHelper.getRememberMe())
		{
			Intent MainActivity = new Intent(this, MainActivity.class);  
			startActivity(MainActivity);
			finish();
			return;
			
		}
		
		// Set up the login form.
	
		findViewById(R.id.buttonLogin).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startLogin();
					}
				});
		
		
		
		findViewById(R.id.buttonRegister).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startRegistration();
					}
				});

	}
	
	private void startLogin()
	{
		Intent loginActivity = new Intent(this, LoginActivity.class);  
		startActivity(loginActivity);
		
	}
	
	private void startRegistration()
	{
		Intent registrationActivity = new Intent(this, RegistrationActivity.class);  
		startActivity(registrationActivity);
	}
	}


	