package com.google.android.maps.mytracks.myappexample;

import com.google.android.maps.mytracks.R;
import com.myapp.android.database.SfidaCorsaTable;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CursorAdapterCorsa extends CursorAdapter {
  
  private Context context;

  private int layout;

  public CursorAdapterCorsa(Context context, Cursor c, int flags) {
    super(context, c, flags);
    Log.d("test", "costruttore");
   // mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    Log.d("test", "creo");
    
      Cursor c = getCursor();

      final LayoutInflater inflater = LayoutInflater.from(context);
      View v = inflater.inflate(R.layout.rowcustom, parent, false);

      Log.d("test", "ho inflato");
      
      int vincita = c.getInt(c.getColumnIndex(SfidaCorsaTable.VINCITA));
      Log.d("test", "vincita " +vincita);
      
      int frequenza = c.getInt(c.getColumnIndex(SfidaCorsaTable.FREQUENZA))+1;
      String rimanente = "ancora 2 votle nei prossimi tre giorni";

      /**
       * Next set the name of the entry.
       */    
      TextView textViewfrequenza = (TextView) v.findViewById(R.id.textViewFrequenza);
      
      TextView textViewvincita = (TextView) v.findViewById(R.id.textViewVincita);
      TextView textViewrimanente = (TextView) v.findViewById(R.id.textViewVolteRimanente);
      
      if (textViewfrequenza != null) {
        textViewfrequenza.setText(frequenza+" a settimana");
      }
      if (textViewvincita != null) {
        textViewvincita.setText("puoi vencere"+ vincita);
    }
      if (textViewrimanente != null) {
        textViewrimanente.setText(rimanente);
    }

      return v;
  }

  @Override
  public void bindView(View v, Context context, Cursor c) {
    Log.d("test", "costruisco");
    
    int vincita = c.getInt(c.getColumnIndex(SfidaCorsaTable.VINCITA));
    int frequenza = c.getInt(c.getColumnIndex(SfidaCorsaTable.FREQUENZA))+1;
    String rimanente = "ancora 2 votle nei prossimi tre giorni";

    /**
     * Next set the name of the entry.
     */    
    TextView textViewfrequenza = (TextView) v.findViewById(R.id.textViewFrequenza);
    TextView textViewvincita = (TextView) v.findViewById(R.id.textViewVincita);
    TextView textViewrimanente = (TextView) v.findViewById(R.id.textViewVolteRimanente);
    
    if (textViewfrequenza != null) {
      textViewfrequenza.setText(frequenza+" a settimana");
    }
    if (textViewvincita != null) {
      textViewvincita.setText("puoi vencere"+ vincita);
  }
    if (textViewrimanente != null) {
      textViewrimanente.setText(rimanente);
    }
    
  }

  
}