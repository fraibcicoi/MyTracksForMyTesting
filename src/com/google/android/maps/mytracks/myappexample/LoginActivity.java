package com.google.android.maps.mytracks.myappexample;


import com.google.android.maps.mytracks.R;
import com.myapp.android.database.DatabaseHelper;
import com.myapp.android.database.ProfiloTable;
import com.myapp.android.database.TemporaryData;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
			"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String nickname;
	private String password;
	private String savedNickname;
	private String savedPassword;
	// UI references.
	private EditText nicknameView;
	private EditText passwordView;
	private View nicknameFormView;
	private View nicknameStatusView;
	private TextView nicknameStatusMessageView;
	private boolean remeberMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		// Set up the login form.
		
		nicknameView = (EditText) findViewById(R.id.nickname_login);
		
		passwordView = (EditText) findViewById(R.id.password_login);
		passwordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		nicknameFormView = findViewById(R.id.login_form);
		nicknameStatusView = findViewById(R.id.login_status);
		nicknameStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		nicknameView.setError(null);
		passwordView.setError(null);

		// Store values at the time of the login attempt.
		nickname = nicknameView.getText().toString();
		password = passwordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(password)) {
			passwordView.setError(getString(R.string.error_field_required));
			focusView = passwordView;
			cancel = true;
		} else if (password.length() < 4) {
			passwordView.setError(getString(R.string.error_invalid_password));
			focusView = passwordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(nickname)) {
			nicknameView.setError(getString(R.string.error_field_required));
			focusView = nicknameView;
			cancel = true;
		} 
		
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			nicknameStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			nicknameStatusView.setVisibility(View.VISIBLE);
			nicknameStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							nicknameStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			nicknameFormView.setVisibility(View.VISIBLE);
			nicknameFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							nicknameFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			nicknameStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			nicknameFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return false;
			}
/*
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext() );
	        
			
			savedNickname = sharedPref.getString (getString(R.string.saved_Nickname), "no");
			savedPassword = sharedPref.getString (getString(R.string.saved_Password), "no");
			*/
			DatabaseHelper databaseHelper= new DatabaseHelper(getApplication());
			Log.d("test", "ho creato database");
			Cursor c = databaseHelper.getProfilo(nickname, password);
			
			
			
		//	Log.d("debug", "saved" + savedNickname+" nick "+nickname+"saved P"+ savedPassword+"pas "+password);
			
			if(c.getCount()==1)
			{
				((TemporaryData) getApplication()).setID(c.getInt(c.getColumnIndex(ProfiloTable._ID)));
				//SharedPreferences.Editor editor = sharedPref.edit();
				 final CheckBox remeberMeCheckbox = (CheckBox) findViewById(R.id.rememberMe);
		       
				remeberMe=remeberMeCheckbox.isChecked();
	    		
				if(remeberMeCheckbox.isChecked())
				{
					databaseHelper.setRememberMe(nickname, password, 1);
				}
				//editor.putBoolean(getString(R.string.saved_Remember_me), remeberMe);
	    	//	editor.commit();
	    		
				Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);  
	    		startActivity(mainActivity);
	    		
				return true;	
			}
			else
			
			
			return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				
				
				finish();
			} else {
				passwordView
						.setError(getString(R.string.error_incorrect_password));
				passwordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
