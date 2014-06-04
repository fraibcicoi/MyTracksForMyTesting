package com.google.android.maps.mytracks.myappexample;

import com.google.android.maps.mytracks.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DettagliSfidaInCorso extends Fragment{
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_dettagli_sfida_in_corso, container, false);
return view;
  }


  
@Override   
  public void onViewCreated (View view, Bundle savedInstanceState)
  {
  }
}
