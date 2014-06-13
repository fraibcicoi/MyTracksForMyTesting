package com.myapp.android.database;

import com.google.android.apps.mytracks.content.MyTracksProviderUtils;
import com.google.android.apps.mytracks.content.TracksColumns;
import com.google.android.apps.mytracks.util.StringUtils;
import com.google.android.maps.mytracks.myappexample.GraficoPersonaleFragment;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class TemporaryData extends Application{
private int ID;
private double axisX=0;
private GraficoPersonaleFragment graficoPersonale;
private int IDrecordingSfida;
private int IDSfidaDaCaricare;


public void setIDrecordingSfida(int id)
{
  IDrecordingSfida=id;
  //Log.d("test2", "ho messo "+ID);
}
public int getIDrecordingSfida()
{
 // Log.d("test2", "restituisco "+ID);
  return IDrecordingSfida;
}  

public void setIDSfidaDaCaricare(int id)
{
  IDSfidaDaCaricare=id;
  //Log.d("test2", "ho messo "+ID);
}
public int getIDSfidaDaCaricare()
{
 // Log.d("test2", "restituisco "+ID);
  return IDSfidaDaCaricare;
}  



public void setID(int id)
{
  ID=id;
  
}
public int getID()
{
  return ID;
}  

public void setxAxis(double x0)
{
  if(axisX==0)
 axisX=x0; 
}
public void setGraficoPersonale(GraficoPersonaleFragment MygraficoPersonale)
{
 graficoPersonale =MygraficoPersonale;
}

public GraficoPersonaleFragment getGraficoPersonale()
{
  return graficoPersonale;
}


public double getxAxis() {
  // TODO Auto-generated method stub
  return axisX;
}

public void checkAndAddTrack(long idTrack)
{
  
  
  MyTracksProviderUtils myTracksProviderUtils;
  
  myTracksProviderUtils = MyTracksProviderUtils.Factory.get(this);
  
  
 Cursor test= myTracksProviderUtils.getTrackCursor(TracksColumns._ID+"="+idTrack,null, null);
 
 test.moveToFirst();
 long idCorsaReale= test.getLong(test.getColumnIndex(TracksColumns._ID));
double rowDistance=test.getDouble(test.getColumnIndex( TracksColumns.TOTALDISTANCE));
test.close();

Log.d("test","l'id reale è "+idCorsaReale);




 DatabaseHelper db= new DatabaseHelper(getApplicationContext());
 

 String totalDistance = StringUtils.formatDistance(this,rowDistance , true);
 //totalDistance;
 String distanceInKm="";
for(int i=0; i< totalDistance.length(); i++)
{
  if(totalDistance.charAt(i)==',')
  {
    break;
  }
  
  distanceInKm=distanceInKm+totalDistance.charAt(i);
  
}
 
 int intDistance=Integer.parseInt(distanceInKm);
 
 Cursor sfida=db.getSfidaCorsa(IDrecordingSfida, db.IN_CORSO);
 
 int esito=0;
 double distanzaScommessa=0;
 if(sfida.moveToFirst())
 {
   distanzaScommessa=sfida.getInt(sfida.getColumnIndex(SfidaCorsaTable.DISTANZAMINIMA));
 sfida.close();
 }
 
 if(intDistance>=distanzaScommessa)
 {
  esito=db.VINTA; 
 }
 else
 {
   esito=db.PERSA;
 }
 Log.d("test",totalDistance);
 db.inserisciCorsaSingola(IDrecordingSfida, (int)idCorsaReale ,esito);
   
  
}

}
