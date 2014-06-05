/*
Copyright (c) 2013 Tiziano Basile

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE
 */
package com.google.android.maps.mytracks.myappexample;

import com.google.android.maps.mytracks.R;
import com.myapp.android.database.DatabaseHelper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PalestraFragment extends Fragment{

	Spinner frequenzaSpinner;
	Spinner periodoSpinner;
	Spinner TempoSpinner;
	EditText quantita;
	TextView quota;
	TextView risultato;
	Button bottoniScommetti;
	float risultatoScommessa;
	float scommessa;
	public PalestraFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_palestra, container, false);
		
				
	//	SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = sharedPref.edit();
		
	//	editor.putInt(getString(R.string.saved_high_score), 10);
		
		
	//	editor.commit();
		
		
		return view;
	}

	
	@Override
	public void onViewCreated (View view, Bundle savedInstanceState)
	{/*
		setupSpinner();
		
		
		risultato= (TextView) getView().findViewById(R.id.risultato);
		
				quantita=(EditText) getView().findViewById(R.id.quantita_scommessa);
				
				
				
				quantita.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		            @Override
		            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
		                     
		            	
		            	String text= (String) textView.getText().toString();
		            	Log.d("debug",text);
		            	scommessa=Float.parseFloat(text) ;
		            
		            	
		            	risultatoScommessa=scommessa*(frequenzaSpinner.getSelectedItemId()+1)*(periodoSpinner.getSelectedItemId()+1)*(TempoSpinner.getSelectedItemId()+1);
		         //   char[]=risultatoScommessa;
		            
		            	risultato.setText(risultatoScommessa+"");
		            	
		            	return false;
		            }
		        });

				 getView().findViewById(R.id.scommetti_button).setOnClickListener(new View.OnClickListener() {
			            @Override
			            public void onClick(View view) {
			                scommetti();
			            }
			        });		
				
			*/	
		
	}	
	
	private void scommetti()
	{
		
		DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
		//databaseHelper.inserisciSfidaCorsa((int)frequenzaSpinner.getSelectedItemId(), (int)periodoSpinner.getSelectedItemId(), (int)TempoSpinner.getSelectedItemId(),risultatoScommessa,scommessa);
	
		Cursor c = databaseHelper.getSfidaCorsa(1);
		try
		{
			while (c.moveToNext())
			{
				Log.d("devAPP", c.getLong(0) + " " + c.getString(1) + " " + c.getString(2));
			}
		}
		finally
		{
			c.close();
		}
		
		
	}
	
	private void setupSpinner()
	{
		frequenzaSpinner=(Spinner) getView().findViewById(R.id.frequenza_spinner);
		TempoSpinner=(Spinner) getView().findViewById(R.id.tempo_minimo_spinner);
periodoSpinner=(Spinner) getView().findViewById(R.id.periodo_spinner);

ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
        R.array.frequenza_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
frequenzaSpinner.setAdapter(adapter);


adapter = ArrayAdapter.createFromResource(getActivity(),
        R.array.distanza_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
TempoSpinner.setAdapter(adapter);


adapter = ArrayAdapter.createFromResource(getActivity(),
        R.array.periodo_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
periodoSpinner.setAdapter(adapter);



	}
	/*
	 * Just an helper method to write the value received from the intent/action
	 * into the TextView in the layout
	 */
	public void setDescriptionIntoFragment(String s)
	{
		Log.d("test", "dovrei mettere  "+s);
		
		
		TextView tv = (TextView) getView().findViewById(R.id.description_textView);
		
		Log.d("test", ""+tv);
		tv.setText(s);
	}
}
