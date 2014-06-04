package com.myapp.android.database;

import com.google.android.maps.mytracks.myappexample.GraficoPersonaleFragment;

import android.app.Application;
import android.util.Log;

public class TemporaryData extends Application{
private int ID;
private double axisX=0;
private GraficoPersonaleFragment graficoPersonale;

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



}
