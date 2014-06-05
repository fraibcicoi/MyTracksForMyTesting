package com.myapp.android.database;

import com.google.android.apps.mytracks.content.MyTracksProviderUtils;
import com.google.android.maps.mytracks.myappexample.GraficoPersonaleFragment;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class TemporaryData extends Application{
private int ID;
private double axisX=0;
private GraficoPersonaleFragment graficoPersonale;
private int IDrecordingSfida;

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




public void setID(int id)
{
  ID=id;
  Log.d("test2", "ho messo "+ID);
}
public int getID()
{
  Log.d("test2", "restituisco "+ID);
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
  
  
 Cursor test= myTracksProviderUtils.getTrackCursor(null, null, null);
  test.getCount();
  
  
  
  
}

}
