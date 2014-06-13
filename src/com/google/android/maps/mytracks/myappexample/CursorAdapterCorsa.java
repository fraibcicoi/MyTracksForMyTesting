package com.google.android.maps.mytracks.myappexample;

import com.google.android.maps.mytracks.R;
import com.myapp.android.database.DatabaseHelper;
import com.myapp.android.database.SfidaCorsaTable;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

public class CursorAdapterCorsa extends CursorAdapter {
  
//  private Context context;

  private int layout;

  private String rimanente="";
  
  private static long MILLISECOND_IN_A_WEEK=1000*60*60*24*7;
  
  private static long MILLISECOND_IN_A_DAY=1000*60*60*24;
  
  private static long MILLISECOND_IN_AN_HOUR=1000*60*60;
  
  
  public CursorAdapterCorsa(Context context, Cursor c, int flags) {
    super(context, c, flags);
    Log.d("test", "costruttore");
   // mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    
      Cursor c = getCursor();

      final LayoutInflater inflater = LayoutInflater.from(context);
      View v = inflater.inflate(R.layout.rowcustom, parent, false);

      
      int vincita = c.getInt(c.getColumnIndex(SfidaCorsaTable.VINCITA));
      Log.d("test", "vincita " +vincita);
      
      
      int IDSfida= c.getInt(c.getColumnIndex(SfidaCorsaTable._ID));
      int frequenza = c.getInt(c.getColumnIndex(SfidaCorsaTable.FREQUENZA))+1;
      
      int durata= c.getInt(c.getColumnIndex(SfidaCorsaTable.DURATA))+1;
      
      long inizioSettimana=c.getLong(c.getColumnIndex(SfidaCorsaTable.DATA_INIZIO));
      
      int[] giorniEOreRimanenti= formatDate((long)inizioSettimana);
       
      DatabaseHelper db = new DatabaseHelper(context);
     int sfideRimantenti= db.getSfideGiaFatteInSettimana(IDSfida, inizioSettimana+giorniEOreRimanenti[2]);
   
     rimanente="Ti mancano ancora ";
      rimanente=rimanente+((frequenza - sfideRimantenti)+" prove da fare nei prossimi ");
      rimanente=rimanente+(giorniEOreRimanenti[0]+" giorni e ");
      rimanente=rimanente+(giorniEOreRimanenti[1]+" ore");
      
         
      
      
      //db.get
      
      
/*
      Data - SfidaCorsaTable.DATA_INIZIO 
      
      millisecondi in una settimana  
  
      1000*60*60*24*7
      
      
  */  
      
      
      
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
  
  //  String rimanente = "ancora 2 votle nei prossimi tre giorni";

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
private int[] formatDate(long startingDate)
{
  
 int dayAndHour[]=new int[3]; 
  long millisecondPassed= (new Date().getTime())- startingDate;
  
  
  
  int settimaneTrascorse= (int)((millisecondPassed)/MILLISECOND_IN_A_WEEK);
  
  long tempoRimanente=  millisecondPassed-(settimaneTrascorse*MILLISECOND_IN_A_WEEK);

  int giorni =(int)(tempoRimanente/MILLISECOND_IN_A_DAY);
  
  int ore =   (int)((tempoRimanente-(giorni*MILLISECOND_IN_A_DAY))/MILLISECOND_IN_AN_HOUR);
   
  
  dayAndHour[0]=giorni;
      
      dayAndHour[1]=24-ore;
 dayAndHour[2]=(int)tempoRimanente;
      return dayAndHour;
  
}
  
}