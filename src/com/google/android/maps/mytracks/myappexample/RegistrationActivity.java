package com.google.android.maps.mytracks.myappexample;


import com.google.android.maps.mytracks.R;
import com.myapp.android.database.DatabaseHelper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class RegistrationActivity extends Activity {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello",
            "bar@example.com:world"
    };

    /**
     * The default email to populate the email field with.
     */
    public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // Values for email and password at the time of the login attempt.
   
    private boolean isMale;
    private String mName;
    
    private String mEmail;
    private String mPassword;
    private int day=1;
    private int month=1;
    private int year=1980;
    private int weight=1;
    private int height=1980;
    private String nickname;
    // UI references.
    private EditText nicknameView;
    private NumberPicker dayPicker;
    private NumberPicker monthPicker;
    private NumberPicker yearPicker;
    private RadioGroup sex;
    private Spinner weightSpinner;
    private Spinner heightSpinner;
    
    
    private EditText nameView;
     
    private EditText emailView;
    private EditText passwordView;
    
    private View mLoginFormView;
    private View mLoginStatusView;
    private TextView mLoginStatusMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        // Set up the login form.
        setUpDatePicker();
        setUpSex();
        setUpHeightAndWeight();
        
        mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
        
        nameView=(EditText) findViewById(R.id.nome);
        emailView = (EditText) findViewById(R.id.email);
        
        
        nicknameView = (EditText) findViewById(R.id.nickname);
        
        emailView.setText(mEmail);

        passwordView = (EditText) findViewById(R.id.password);
        
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mLoginStatusView = findViewById(R.id.login_status);
        mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.registration, menu);
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptRegister() {
       
    	if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        emailView.setError(null);
        passwordView.setError(null);
        
        nicknameView.setError(null);
        
        nameView.setError(null);

        // Store values at the time of the login attempt.
        mEmail = emailView.getText().toString();
        mPassword = passwordView.getText().toString();
        
        Log.d("debug", "ora la pass e"+mPassword);
        nickname= nicknameView.getText().toString();
       mName=nameView.getText().toString();
        
        height= (int) heightSpinner.getSelectedItemId()+120;
        weight= (int) weightSpinner.getSelectedItemId()+40;
        
        
        
        sex= (RadioGroup) findViewById(R.id.sex_radioGroup);
        
       switch (sex.getCheckedRadioButtonId()  ) {
	case R.id.male: {isMale=true;
		
		break;}
	case R.id.female:{ isMale=false;
	
	break;
	}
	default:
		break;
	} 
        
        

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        } else if (mPassword.length() < 4) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmail)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!mEmail.contains("@")) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }
        
        if (TextUtils.isEmpty(mPassword)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        } else if (mPassword.length() < 4) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }
        
        
        

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
            showProgress(true);
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void) null);
        }
    }

    
    private void setUpHeightAndWeight()
    {
    	
    	weightSpinner = (Spinner) findViewById(R.id.weightSpinner);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
    	        R.array.weigth_array, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	weightSpinner.setAdapter(adapter);
    
    	
    	
    	heightSpinner = (Spinner) findViewById(R.id.heightSpinner);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
    	        R.array.height_array, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	heightSpinner.setAdapter(adapter2);
    	
    	
    }
    private void setUpSex()
    {
    	RadioButton male;
    	male=(RadioButton) findViewById(R.id.male);
    	male.setChecked(true);
    }
    
    private void      setUpDatePicker()
    {
    	
    	 dayPicker = (NumberPicker) findViewById(R.id.day);
    	 dayPicker.setMaxValue(31);       
    	 dayPicker.setMinValue(1);
    	 dayPicker.setValue(1);
          dayPicker.setWrapSelectorWheel(true);
          dayPicker.setOnValueChangedListener( new NumberPicker.
              OnValueChangeListener() {
              @Override
              public void onValueChange(NumberPicker picker, int
                  oldVal, int newVal) {
                 day=newVal;
              }
          });
          
     	 monthPicker = (NumberPicker) findViewById(R.id.month);
     	monthPicker.setMaxValue(12);       
     	monthPicker.setMinValue(1);         
     	monthPicker.setWrapSelectorWheel(true);
     	monthPicker.setValue(1);
     	monthPicker.setOnValueChangedListener( new NumberPicker.
              OnValueChangeListener() {
              @Override
              public void onValueChange(NumberPicker picker, int
                  oldVal, int newVal) {
                 month=newVal;
                 
         dayPicker.setMaxValue(daysInMonth(newVal));        
                 
                          
                 
              }
          });	
     	
     	
     	
     	yearPicker = (NumberPicker) findViewById(R.id.year);
     	yearPicker.setMaxValue(2014);       
     	yearPicker.setMinValue(1920);         
     	yearPicker.setWrapSelectorWheel(true);
     	yearPicker.setValue(1980);
     	yearPicker.setOnValueChangedListener( new NumberPicker.
               OnValueChangeListener() {
               @Override
               public void onValueChange(NumberPicker picker, int
                   oldVal, int newVal) {
                  year=newVal;
               }
           });
      }
    
    
    int daysInMonth(int month)
    {
    	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
    	{
    		return 31;
    	}
    	if(month==4||month==6||month==5||month==9||month==11)
    	{
    		return 30;
    	}
    	if(month==2 && year%4==0)
    	{
    		return 29;
    	}
    	else 	
    	{
    		return 28;
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
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginStatusView.setVisibility(View.VISIBLE);
            mLoginStatusView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

            mLoginFormView.setVisibility(View.VISIBLE);
            mLoginFormView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
/*
        	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext() );
        	
    		SharedPreferences.Editor editor = sharedPref.edit();
    		
    		editor.putInt(getString(R.string.saved_weight), weight);
    		editor.commit();
    		editor.putInt(getString(R.string.saved_height), height);
    		editor.commit();
    		editor.putInt(getString(R.string.saved_Birth_Day), day);
    		editor.commit();
    		editor.putInt(getString(R.string.saved_Birth_Month), month);
    		editor.commit();
    		editor.putInt(getString(R.string.saved_Birth_Year), year);
    		editor.commit();
        	editor.putBoolean(getString(R.string.saved_Sex), isMale);
        	editor.commit();
        	Log.d("debug", "metto"+mPassword);
        	editor.putString(getString(R.string.saved_Password), mPassword);
        	editor.commit();
        	editor.putString(getString(R.string.saved_Email), mEmail);
        	editor.commit();
        	editor.putString(getString(R.string.saved_Nickname), nickname);
            	
    		
    		editor.commit();
    	
    		Log.d("debug", "user" + sharedPref.getString (getString(R.string.saved_Nickname), "no")+"pass "+sharedPref.getString (getString(R.string.saved_Password), "no")+"  mail  "+sharedPref.getString (getString(R.string.saved_Email), "no"));
    	*/
        	DatabaseHelper databaseHelper = new DatabaseHelper(getApplication());
        	
        	int sesso;
        	if(isMale)
        	{
        		sesso=1;
        	}
        	else
        	{
        		sesso=0;
        	}
          	databaseHelper.inserisciProfilo(mEmail, mPassword, nickname, (float)height, (float)weight, day, month, year, sesso, 0, mName);
            
        	
    		Intent loginActivity = new Intent(RegistrationActivity.this, LoginActivity.class);  
    		startActivity(loginActivity);
            
            
            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                passwordView.setError(getString(R.string.error_incorrect_password));
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
