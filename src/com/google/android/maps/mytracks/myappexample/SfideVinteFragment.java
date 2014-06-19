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

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
public class SfideVinteFragment extends Fragment{

	Button gpsCoordinates;
	
	public SfideVinteFragment() {
        // Empty constructor required for fragment subclasses
    }
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_description2, container, false);
		
	//	SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = sharedPref.edit();
		
	//	editor.putInt(getString(R.string.saved_high_score), 10);
		
		
	//	editor.commit();
		
		
		return view;
	}
	
	public void onViewCreated (View view, Bundle savedInstanceState)
    {
	  
	  gpsCoordinates = (Button) getView().findViewById(R.id.leggiPosizione);
	    
	  
	  gpsCoordinates.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
          
          GPSTracker  gps = new GPSTracker(getActivity());
          
          // check if GPS enabled     
          if(gps.canGetLocation()){
               
              double latitude = gps.getLatitude();
              double longitude = gps.getLongitude();
              Geocoder geocoder;
              List<Address> addresses;
              geocoder = new Geocoder(getActivity(),Locale.getDefault());
              try {
              addresses = geocoder.getFromLocation(latitude, longitude, 1);
              String address = addresses.get(0).getAddressLine(0);
              String city = addresses.get(0).getAddressLine(1);
              String country = addresses.get(0).getAddressLine(2);
              Toast.makeText(getActivity().getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+"\ne sei in "+address, Toast.LENGTH_LONG).show();    
              
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

              // \n is for new line
          }else{
              // can't get location
              // GPS or Network is not enabled
              // Ask user to enable GPS/network in settings
              gps.showSettingsAlert();
          }
        
        }
	  });
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
