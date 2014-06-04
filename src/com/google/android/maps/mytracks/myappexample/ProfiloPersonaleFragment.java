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
import com.myapp.android.database.ProfiloTable;
import com.myapp.android.database.TemporaryData;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfiloPersonaleFragment extends Fragment{

    private String nome;
    private String nickname;
    private String email;
    private String birthDate;
    
    private float weight;
    private float goalWeight;
    private float height;
    private String sex;
   private int sexInt;
    
  private Button buttonChangeWeight;
  private Button buttonChangeGoalWeight;

  private TextView textViewNome;
	private TextView textViewNickname;
	private TextView textViewEmail;
	private TextView textViewData;
	private TextView textViewPeso;
	private TextView textViewAltezza;
	private TextView textViewObiettivoPeso;
	private TextView textViewSesso;
    private Cursor utente;
    private int ID;
    
    private DatabaseHelper databaseHelper;
	
	public ProfiloPersonaleFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_profilo_personale, container, false);
		
	//	SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = sharedPref.edit();
		ID=((TemporaryData) getActivity().getApplication()).getID();
		
	//	editor.putInt(getString(R.string.saved_high_score), 10);
		
		
	//	editor.commit();
		
		
		return view;
	}
	
	
	public void onViewCreated (View view, Bundle savedInstanceState)
    {
	
	  
	 databaseHelper= new DatabaseHelper(getActivity().getApplication());
      utente=databaseHelper.getProfilo(ID);
      
      if(utente.getCount()==1)
      {
        inizializzaDatiUtente();
        inizializzaTextView();
        inizializzaBottoni();
      }
	  
    }
	
	private void inizializzaDatiUtente()
	{
	  utente.moveToFirst();
	  nome=utente.getString(utente.getColumnIndex(ProfiloTable.NOME));
	  nickname=utente.getString(utente.getColumnIndex(ProfiloTable.NICKNAME));
	  
	  email=utente.getString(utente.getColumnIndex(ProfiloTable.EMAIL));
	
	  birthDate= (utente.getInt(utente.getColumnIndex(ProfiloTable.DAY_OF_BIRHT)))+ " - "+
	              (utente.getInt(utente.getColumnIndex(ProfiloTable.MONTH_OF_BIRHT)))+ " - "+
	              (utente.getInt(utente.getColumnIndex(ProfiloTable.YEAR_OF_BIRHT)));
	  
	  weight=utente.getFloat(utente.getColumnIndex(ProfiloTable.WEIGHT));
      goalWeight=utente.getFloat(utente.getColumnIndex(ProfiloTable.GOAL_WEIGHT));
      height=utente.getFloat(utente.getColumnIndex(ProfiloTable.HEIGHT));
      sexInt=utente.getInt(utente.getColumnIndex(ProfiloTable.SEX));
       if(sexInt==1)
       {
         sex="Uomo";
       }
       else
       {
         sex="Donna";   
       }
	}
	private void inizializzaTextView()
	{
	  
	  textViewNome = (TextView) getView().findViewById(R.id.profiloName);
	  textViewNome.setText(nome);
	  
	  textViewNickname = (TextView) getView().findViewById(R.id.profiloNickname);
      textViewNickname.setText(nickname);
      
      textViewEmail = (TextView) getView().findViewById(R.id.profiloEmail);
      textViewEmail.setText(email);
      
      textViewObiettivoPeso = (TextView) getView().findViewById(R.id.profiloObiettivoPeso);
      textViewObiettivoPeso.setText(goalWeight+"");
   
      textViewPeso = (TextView) getView().findViewById(R.id.profiloPeso);
      textViewPeso.setText(weight+"");
      
      textViewAltezza=(TextView) getView().findViewById(R.id.profiloAltezza);
      textViewAltezza.setText(height+"");
     
      textViewData=(TextView) getView().findViewById(R.id.profiloDataDiNascita);
      textViewData.setText(birthDate);
     
      
      textViewSesso=(TextView) getView().findViewById(R.id.profiloSesso);
      textViewSesso.setText(sex);
          
      
      
	}
	
	
	private void inizializzaBottoni()
	{
	  buttonChangeWeight = (Button) getView().findViewById(R.id.buttonPeso);
	  buttonChangeGoalWeight=(Button) getView().findViewById(R.id.buttonObiiettivoPeso);
	  
	  buttonChangeWeight.setOnClickListener(new OnClickListener() {
	    
        @Override
        public void onClick(View arg0) {

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(getActivity());
            View promptsView = li.inflate(R.layout.peso_popup_message, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.popupPesoEditText);

            // set dialog message
            alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                    // get user input and set it to result
                    // edit text
                      setNewWeight(userInput.getText().toString());
                     // (getActivity().getFragmentManager()).
               //       GraficoPersonaleFragment titleFragmentById = (GraficoPersonaleFragment)((getActivity().getFragmentManager().findFragmentById(10)));
           //           (getActivity().getFragmentManager()).getClass();
                    }
                  })
                .setNegativeButton("Cancel",
                  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                    }
                  });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
    });
	  
	   buttonChangeGoalWeight.setOnClickListener(new OnClickListener() {
	        
	        @Override
	        public void onClick(View arg0) {

	            // get prompts.xml view
	            LayoutInflater li = LayoutInflater.from(getActivity());
	            View promptsView = li.inflate(R.layout.peso_popup_message, null);

	            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

	            // set prompts.xml to alertdialog builder
	            alertDialogBuilder.setView(promptsView);

	            final EditText userInput = (EditText) promptsView
	                    .findViewById(R.id.popupPesoEditText);

	            // set dialog message
	            alertDialogBuilder
	                .setCancelable(false)
	                .setPositiveButton("OK",
	                  new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,int id) {
	                    // get user input and set it to result
	                    // edit text
	                      setNewGoalWeight(userInput.getText().toString());
	                    }
	                  })
	                .setNegativeButton("Cancel",
	                  new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,int id) {
	                    dialog.cancel();
	                    }
	                  });

	            // create alert dialog
	            AlertDialog alertDialog = alertDialogBuilder.create();

	            // show it
	            alertDialog.show();

	        }
	    });
	  
	  
	}
	
	private void setNewWeight(String newWeight)
	{
	  float weightFloat=Float.parseFloat(newWeight);
	  databaseHelper.inserisciNuovoPeso(ID, weightFloat);
	  textViewPeso.setText(newWeight);
	( (TemporaryData)( getActivity().getApplication())).getGraficoPersonale().aggiornaPunti();
	}
	   private void setNewGoalWeight(String newWeight)
	    {
	      float weightFloat=Float.parseFloat(newWeight);
	      databaseHelper.inserisciNuovoObiettivoPeso(ID, weightFloat);
	      textViewObiettivoPeso.setText(newWeight);
	      
	      ( (TemporaryData)( getActivity().getApplication())).getGraficoPersonale().clearTrackPoints();
	      ( (TemporaryData)( getActivity().getApplication())).getGraficoPersonale().aggiungiPuntiStart();
	      ( (TemporaryData)( getActivity().getApplication())).getGraficoPersonale().aggiungiPunti();
          

	      
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
