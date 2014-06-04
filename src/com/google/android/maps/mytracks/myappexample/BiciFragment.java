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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BiciFragment extends Fragment{

	
	public BiciFragment() {
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
		
	//	editor.putInt(getString(R.string.saved_high_score), 10);
		
		
	//	editor.commit();
		
		
		return view;
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
