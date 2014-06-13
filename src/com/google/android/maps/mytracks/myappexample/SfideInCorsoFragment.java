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
import com.myapp.android.database.SfidaCorsaTable;
import com.myapp.android.database.TemporaryData;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
public class SfideInCorsoFragment extends Fragment{

  private DatabaseHelper db;
	private int IdUtente;
	private Cursor sfideCorsaPending;
	
	public SfideInCorsoFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sfide_in_corso, container, false);
		IdUtente=((TemporaryData)(getActivity().getApplication())).getID();
		
		db= new DatabaseHelper(getActivity());
	//	SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = sharedPref.edit();
		
	//	editor.putInt(getString(R.string.saved_high_score), 10);
		
		
	//	editor.commit();
		
		
		return view;
	}
	
	
	public void onViewCreated (View view, Bundle savedInstanceState)
    {
    
	 ListView listView = (ListView)getView().findViewById(R.id.listViewDemo);
	
	 
	 //getActivity().startM
	 sfideCorsaPending= db.getSfidaCorsa(IdUtente,db.IN_CORSO);
	 
	 
	// int[]  to = new int[] { R.id.textViewFrequenza };
	 
	 
	 
	 
	 
     CursorAdapterCorsa adapter = new CursorAdapterCorsa(getActivity(),sfideCorsaPending,2);
     
     
     
     
     listView.setAdapter(adapter);
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

       
       
      public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

       Log.d("test", "hai schiacciato "+pos);
       
       
       
       Intent mainActivity = getActivity().getIntent();
       mainActivity.setClass(getActivity(), CorsaTrackListActivity.class);
     //   mainActivity.
    //   Intent mainActivity = new Intent(getActivity(), CorsaTrackListActivity.class);  
      // Bundle sfida= new Bundle();
       
       sfideCorsaPending.moveToPosition(pos);
       
     // sfida.putInt("ID_SFIDA",sfideCorsaPending.getInt(sfideCorsaPending.getColumnIndex(SfidaCorsaTable._ID)));
     //  mainActivity.putExtra("SFIDA", sfida);
       
       ((TemporaryData)(getActivity().getApplication())).setIDSfidaDaCaricare(sfideCorsaPending.getInt(sfideCorsaPending.getColumnIndex(SfidaCorsaTable._ID)));
    
       
       //   sfida.put
      // getActivity().finish();
       mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       startActivity(mainActivity);
    
       
       //  new DettagliSfidaInCorso();
    //Fragment fragment = new DettagliSfidaInCorso();
   // ( getActivity().getFragmentManager()).addOnBackStackChangedListener(listener);.removeOnBackStackChangedListener(listener); .FragmentTransaction.replace(fragment);
   //    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
   //    fragmentManager.beginTransaction().replace(R.id.descriptionFragment, fragment).commit();
   /*    mDrawerList.setItemChecked(position, true);
       setTitle(mPlanetTitles[position]);
       mDrawerLayout.closeDrawer(mDrawerList);*/
      }
      
    });   
}
    
	@Override
	public void onPause()
	{
	  super.onPause();
      sfideCorsaPending.close();
      
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
