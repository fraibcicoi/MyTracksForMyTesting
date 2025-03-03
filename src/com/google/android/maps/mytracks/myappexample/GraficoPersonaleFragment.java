 /*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.android.maps.mytracks.myappexample;

import com.google.android.apps.mytracks.ChartView;
import com.google.android.apps.mytracks.TrackDetailActivity;
import com.google.android.apps.mytracks.content.MyTracksLocation;
import com.google.android.apps.mytracks.content.Sensor;
import com.google.android.apps.mytracks.content.Sensor.SensorDataSet;
import com.google.android.apps.mytracks.content.Track;
import com.google.android.apps.mytracks.content.TrackDataHub;
import com.google.android.apps.mytracks.content.TrackDataListener;
import com.google.android.apps.mytracks.content.TrackDataType;
import com.google.android.apps.mytracks.content.Waypoint;
import com.google.android.apps.mytracks.stats.TripStatistics;
import com.google.android.apps.mytracks.stats.TripStatisticsUpdater;
import com.google.android.apps.mytracks.util.CalorieUtils.ActivityType;
import com.google.android.apps.mytracks.util.LocationUtils;
import com.google.android.apps.mytracks.util.PreferencesUtils;
import com.google.android.apps.mytracks.util.UnitConversions;
import com.google.android.maps.mytracks.R;
import com.google.common.annotations.VisibleForTesting;
import com.myapp.android.database.DatabaseHelper;
import com.myapp.android.database.PesoTable;
import com.myapp.android.database.TemporaryData;

import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ZoomControls;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * A fragment to display track chart to the user.
 * 
 * @author Sandor Dornbush
 * @author Rodrigo Damazio
 */
public class GraficoPersonaleFragment extends Fragment implements TrackDataListener {

  public static final String CHART_FRAGMENT_TAG = "chartFragment";

  private final ArrayList<double[]> pendingPoints = new ArrayList<double[]>();

  private TrackDataHub trackDataHub;
private DatabaseHelper dbHelper;
  
  // Stats gathered from the received data
  private TripStatisticsUpdater tripStatisticsUpdater;
  private long startTime;

  private boolean metricUnits = true;
  private boolean reportSpeed = true;
  private int recordingDistanceInterval = PreferencesUtils.RECORDING_DISTANCE_INTERVAL_DEFAULT;

  // Modes of operation
  private boolean chartByDistance = true;
  private boolean[] chartShow = new boolean[] { true, true, true, true, true, true };

  // UI elements
  private PesoChartView chartView;
  private ZoomControls zoomControls;
  private float goalWeight;
  
  
  
  ArrayList<double[]> myPoints = new ArrayList<double[]>();
  
  
  
  /**
   * A runnable that will enable/disable zoom controls and orange pointer as
   * appropriate and redraw.
   */
  private final Runnable updateChart = new Runnable() {
      @Override
    public void run() {
      if (!isResumed()){// || trackDataHub == null) {
        return;
      }

      zoomControls.setIsZoomInEnabled(chartView.canZoomIn());
      zoomControls.setIsZoomOutEnabled(chartView.canZoomOut());
      chartView.setShowPointer(isSelectedTrackRecording());
      chartView.invalidate();
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /*
     * Create a chartView here to store data thus won't need to reload all the
     * data on every onStart or onResume.
     */
    chartView = new PesoChartView(getActivity());
  };

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.grafico_peso, container, false);
    zoomControls = (ZoomControls) view.findViewById(R.id.chart_zoom_controls);
    zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
        @Override
      public void onClick(View v) {
        zoomIn();
      }
    });
    zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
        @Override
      public void onClick(View v) {
        zoomOut();
      }
    });
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    dbHelper=new DatabaseHelper(getActivity());
    ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.chart_view_layout);
    LayoutParams layoutParams = new LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    layout.addView(chartView, layoutParams);

  aggiungiPuntiStart();
//che cazzo sto facendo

  }

  @Override
  public void onResume() {
    super.onResume();
    
  //  resumeTrackDataHub();
    Log.d("test2", "entro qua in resume");
    //clearTrackPoints();
    
    aggiungiPunti();
  /*  double[][] punto=new double[100][7];
    
    
    //=new double[];
    for(int i=0;i<100;i++)
    {
     punto[i][0] = i*0.1;
     punto[i][1] =i;
     punto[i][2] =i;
     punto[i][3] =Double.NaN;
     punto[i][4] =Double.NaN;
     punto[i][5] =Double.NaN;
     punto[i][6] =Double.NaN;
    myPoints.add(punto[i]);
    }*/
//    chartView.addDataPoints(myPoints);
   // runOnUiThread(updateChart); 
    checkChartSettings();
  //  chartView.addDataPoints(myPoints);
       
    getActivity().runOnUiThread(updateChart);
  //  chartView.addDataPoints(myPoints);
  //  myTrackPointsDone(myPoints);
  }

  
  public void aggiungiPuntiStart()
  {
 int ID=((TemporaryData) getActivity().getApplication()).getID();
     //  public void myTrackPointsDone(ArrayList<double[]> points) {
 Cursor userWeights=dbHelper.getPesiUtente(ID);
 goalWeight= dbHelper.getGoalWeight(ID);
 userWeights.moveToFirst();
 double[][] punto=new double[userWeights.getCount()][7];
 
 

 userWeights.moveToFirst();
 double startX=0;
// int test =userWeights.getCount();
 try{
   int i=0;
   do
   {
     if(i==0)
     {
       startX=Double.parseDouble( userWeights.getString(userWeights.getColumnIndex(PesoTable.DATE)));
       ((TemporaryData) getActivity().getApplication()).setxAxis(startX);
     }
       
     punto[i][0] =Double.parseDouble( userWeights.getString(userWeights.getColumnIndex(PesoTable.DATE)))-startX;
     punto[i][1] = userWeights.getFloat(userWeights.getColumnIndex(PesoTable.WEIGHT));
     punto[i][2] =goalWeight;
     punto[i][3] =Double.NaN;
     punto[i][4] =Double.NaN;
     punto[i][5] =Double.NaN;
     punto[i][6] =Double.NaN;
     myPoints.add(punto[i]);

     i++;
     
   } while(userWeights.moveToNext());
 }
 finally{
   userWeights.close();
 }
  }
 public void aggiungiPunti(){
 
 if (isResumed()) {
        chartView.addDataPoints(myPoints);
        myPoints.clear();
        runOnUiThread(updateChart);
      }
    
    
  }
  
  public void aggiornaPunti()
  {
 int ID=((TemporaryData) getActivity().getApplication()).getID();
     //  public void myTrackPointsDone(ArrayList<double[]> points) {
 Cursor userWeights=dbHelper.getPesiUtente(ID);
 if(userWeights.moveToLast()){
 double[] punto=new double[7];
 
 double startX=0;
// int test =userWeights.getCount();
     punto[0] =Double.parseDouble( userWeights.getString(userWeights.getColumnIndex(PesoTable.DATE)))-((TemporaryData) getActivity().getApplication()).getxAxis();
     punto[1] = userWeights.getFloat(userWeights.getColumnIndex(PesoTable.WEIGHT));
     punto[2] =3;
     punto[3] =Double.NaN;
     punto[4] =Double.NaN;
     punto[5] =Double.NaN;
     punto[6] =Double.NaN;
     myPoints.add(punto);

     
   userWeights.close();
 }
 if (isResumed()) {
        chartView.addDataPoints(myPoints);
        myPoints.clear();
        runOnUiThread(updateChart);
      }
    
    
  }

  @Override
  public void onPause() {
    super.onPause();
   // pauseTrackDataHub();
  }

  @Override
  public void onStop() {
    super.onStop();
    ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.chart_view_layout);
    layout.removeView(chartView);
  }

  @Override
  public void onTrackUpdated(Track track) {
    if (isResumed()) {
      if (track == null || track.getTripStatistics() == null) {
        startTime = -1L;
        return;
      }
      startTime = track.getTripStatistics().getStartTime();
    }
  }

  @Override
  public void clearTrackPoints() {
    if (isResumed()) {
      tripStatisticsUpdater = startTime != -1L ? new TripStatisticsUpdater(startTime) : null;
      pendingPoints.clear();
      chartView.reset();
      runOnUiThread(new Runnable() {
          @Override
        public void run() {
          if (isResumed()) {
            chartView.resetScroll();
          }
        }
      });
    }
  }

  @Override
  public void onSampledInTrackPoint(Location location) {
    if (isResumed()) {
      double[] data = new double[ChartView.NUM_SERIES + 1];
      fillDataPoint(location, data);
      pendingPoints.add(data);
    }
  }

  @Override
  public void onSampledOutTrackPoint(Location location) {
    if (isResumed()) {
      fillDataPoint(location, null);
    }
  }

  @Override
  public void onSegmentSplit(Location location) {
    if (isResumed()) {
      fillDataPoint(location, null);
    }
  }

  @Override
  public void onNewTrackPointsDone() {
    if (isResumed()) {
      chartView.addDataPoints(pendingPoints);
      pendingPoints.clear();
      runOnUiThread(updateChart);
    }
  }
  
  public void myTrackPointsDone(ArrayList<double[]> points) {
    if (isResumed()) {
      chartView.addDataPoints(points);
      pendingPoints.clear();
      runOnUiThread(updateChart);
    }
  }

  @Override
  public void clearWaypoints() {
    if (isResumed()) {
      chartView.clearWaypoints();
    }
  }

  @Override
  public void onNewWaypoint(Waypoint waypoint) {
    if (isResumed() && waypoint != null && LocationUtils.isValidLocation(waypoint.getLocation())) {
      chartView.addWaypoint(waypoint);
    }
  }

  @Override
  public void onNewWaypointsDone() {
    if (isResumed()) {
      runOnUiThread(updateChart);
    }
  }

  @Override
  public boolean onMetricUnitsChanged(boolean metric) {
    if (isResumed()) {
      if (metricUnits == metric) {
        return false;
      }
      metricUnits = metric;
      chartView.setMetricUnits(metricUnits);
      runOnUiThread(new Runnable() {
          @Override
        public void run() {
          if (isResumed()) {
            chartView.requestLayout();
          }
        }
      });
      return true;
    }
    return false;
  }

  @Override
  public boolean onReportSpeedChanged(boolean speed) {
    if (isResumed()) {
      if (reportSpeed == speed) {
        return false;
      }
      reportSpeed = speed;
      chartView.setReportSpeed(reportSpeed);
      boolean chartShowSpeed = PreferencesUtils.getBoolean(
          getActivity(), R.string.chart_show_speed_key, PreferencesUtils.CHART_SHOW_SPEED_DEFAULT);
      setSeriesEnabled(ChartView.SPEED_SERIES, chartShowSpeed && reportSpeed);
      setSeriesEnabled(ChartView.PACE_SERIES, chartShowSpeed && !reportSpeed);
      runOnUiThread(new Runnable() {
          @Override
        public void run() {
          if (isResumed()) {
            chartView.requestLayout();
          }
        }
      });
      return true;
    }
    return false;
  }

  @Override
  public boolean onRecordingGpsAccuracy(int minRequiredAccuracy) {
    // We don't care.
    return false;
  }
  
  @Override
  public boolean onRecordingDistanceIntervalChanged(int value) {
    if (isResumed()) {
      if (recordingDistanceInterval == value) {
        return false;
      }
      recordingDistanceInterval = value;
      return true;
    }
    return false;
  }
  
  @Override
  public boolean onMapTypeChanged(int mapType) {
    // We don't care.
    return false;
  }

  /**
   * Checks the chart settings.
   */
  private void checkChartSettings() {
    boolean needUpdate = false;
    if (chartByDistance != PreferencesUtils.isChartByDistance(getActivity())) {
      chartByDistance = !chartByDistance;
      chartView.setChartByDistance(chartByDistance);
     // reloadTrackDataHub();
      needUpdate = true;
    }
    if (setSeriesEnabled(ChartView.ELEVATION_SERIES, PreferencesUtils.getBoolean(getActivity(),
        R.string.chart_show_elevation_key, PreferencesUtils.CHART_SHOW_ELEVATION_DEFAULT))) {
      needUpdate = true;
    }

    boolean chartShowSpeed = PreferencesUtils.getBoolean(
        getActivity(), R.string.chart_show_speed_key, PreferencesUtils.CHART_SHOW_SPEED_DEFAULT);
    if (setSeriesEnabled(ChartView.SPEED_SERIES, chartShowSpeed && reportSpeed)) {
      needUpdate = true;
    }
    if (setSeriesEnabled(ChartView.PACE_SERIES, chartShowSpeed && !reportSpeed)) {
      needUpdate = true;
    }
    if (setSeriesEnabled(ChartView.POWER_SERIES, PreferencesUtils.getBoolean(
        getActivity(), R.string.chart_show_power_key, PreferencesUtils.CHART_SHOW_POWER_DEFAULT))) {
      needUpdate = true;
    }
    if (setSeriesEnabled(ChartView.CADENCE_SERIES, PreferencesUtils.getBoolean(getActivity(),
        R.string.chart_show_cadence_key, PreferencesUtils.CHART_SHOW_CADENCE_DEFAULT))) {
      needUpdate = true;
    }
    if (setSeriesEnabled(ChartView.HEART_RATE_SERIES, PreferencesUtils.getBoolean(getActivity(),
        R.string.chart_show_heart_rate_key, PreferencesUtils.CHART_SHOW_HEART_RATE_DEFAULT))) {
      needUpdate = true;
    }
    
setSeriesEnabled(0, true);
setSeriesEnabled(1, true);
setSeriesEnabled(2, true);
needUpdate = true;


    if (needUpdate) {
      chartView.postInvalidate();
    }
    
    
  }

  /**
   * Sets the series enabled value.
   * 
   * @param index the series index
   * @param value the value
   * @return true if changed
   */
  private boolean setSeriesEnabled(int index, boolean value) {
    if (chartShow[index] != value) {
      chartShow[index] = value;
      chartView.setChartValueSeriesEnabled(index, value);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Resumes the trackDataHub. Needs to be synchronized because trackDataHub can
   * be accessed by multiple threads.
   */
  private synchronized void resumeTrackDataHub() {
    trackDataHub = ((TrackDetailActivity) getActivity()).getTrackDataHub();
    trackDataHub.registerTrackDataListener(this, EnumSet.of(TrackDataType.TRACKS_TABLE,
        TrackDataType.WAYPOINTS_TABLE, TrackDataType.SAMPLED_IN_TRACK_POINTS_TABLE,
        TrackDataType.SAMPLED_OUT_TRACK_POINTS_TABLE, TrackDataType.PREFERENCE));
  }

  /**
   * Pauses the trackDataHub. Needs to be synchronized because trackDataHub can
   * be accessed by multiple threads.
   */
  private synchronized void pauseTrackDataHub() {
    trackDataHub.unregisterTrackDataListener(this);
    trackDataHub = null;
  }

  /**
   * Returns true if the selected track is recording. Needs to be synchronized
   * because trackDataHub can be accessed by multiple threads.
   */
  private synchronized boolean isSelectedTrackRecording() {
    return trackDataHub != null && trackDataHub.isSelectedTrackRecording();
  }

  /**
   * Reloads the trackDataHub. Needs to be synchronized because trackDataHub can
   * be accessed by multiple threads.
   */
  private synchronized void reloadTrackDataHub() {
    if (trackDataHub != null) {
      trackDataHub.reloadDataForListener(this);
    }
  }

  /**
   * To zoom in.
   */
  private void zoomIn() {
    chartView.zoomIn();
    zoomControls.setIsZoomInEnabled(chartView.canZoomIn());
    zoomControls.setIsZoomOutEnabled(chartView.canZoomOut());
  }

  /**
   * To zoom out.
   */
  private void zoomOut() {
    chartView.zoomOut();
    zoomControls.setIsZoomInEnabled(chartView.canZoomIn());
    zoomControls.setIsZoomOutEnabled(chartView.canZoomOut());
  }

  /**
   * Runs a runnable on the UI thread if possible.
   * 
   * @param runnable the runnable
   */
  private void runOnUiThread(Runnable runnable) {
    FragmentActivity fragmentActivity = getActivity();
    if (fragmentActivity != null) {
      fragmentActivity.runOnUiThread(runnable);
    }
  }

  /**
   * Given a location, fill in a data point, an array of double[]. <br>
   * data[0] = time/distance <br>
   * data[1] = elevation <br>
   * data[2] = speed <br>
   * data[3] = pace <br>
   * data[4] = heart rate <br>
   * data[5] = cadence <br>
   * data[6] = power <br>
   * 
   * @param location the location
   * @param data the data point to fill in, can be null
   */
  @VisibleForTesting
  void fillDataPoint(Location location, double data[]) {
    double timeOrDistance = Double.NaN;
    double elevation = Double.NaN;
    double speed = Double.NaN;
    double pace = Double.NaN;
    double heartRate = Double.NaN;
    double cadence = Double.NaN;
    double power = Double.NaN;

    if (tripStatisticsUpdater != null) {
      tripStatisticsUpdater.addLocation(
          location, recordingDistanceInterval, false, ActivityType.INVALID, 0.0);
      TripStatistics tripStatistics = tripStatisticsUpdater.getTripStatistics();
      if (chartByDistance) {
        double distance = tripStatistics.getTotalDistance() * UnitConversions.M_TO_KM;
        if (!metricUnits) {
          distance *= UnitConversions.KM_TO_MI;
        }
        timeOrDistance = distance;
      } else {
        timeOrDistance = tripStatistics.getTotalTime();
      }

      elevation = tripStatisticsUpdater.getSmoothedElevation();
      if (!metricUnits) {
        elevation *= UnitConversions.M_TO_FT;
      }

      speed = tripStatisticsUpdater.getSmoothedSpeed() * UnitConversions.MS_TO_KMH;
      if (!metricUnits) {
        speed *= UnitConversions.KM_TO_MI;
      }
      pace = speed == 0 ? 0.0 : 60.0 / speed;
    }
    if (location instanceof MyTracksLocation
        && ((MyTracksLocation) location).getSensorDataSet() != null) {
      SensorDataSet sensorDataSet = ((MyTracksLocation) location).getSensorDataSet();
      if (sensorDataSet.hasHeartRate()
          && sensorDataSet.getHeartRate().getState() == Sensor.SensorState.SENDING
          && sensorDataSet.getHeartRate().hasValue()) {
        heartRate = sensorDataSet.getHeartRate().getValue();
      }
      if (sensorDataSet.hasCadence()
          && sensorDataSet.getCadence().getState() == Sensor.SensorState.SENDING
          && sensorDataSet.getCadence().hasValue()) {
        cadence = sensorDataSet.getCadence().getValue();
      }
      if (sensorDataSet.hasPower()
          && sensorDataSet.getPower().getState() == Sensor.SensorState.SENDING
          && sensorDataSet.getPower().hasValue()) {
        power = sensorDataSet.getPower().getValue();
      }
    }

    if (data != null) {
      data[0] = timeOrDistance;
      data[1] = elevation;
      data[2] = speed;
      data[3] = pace;
      data[4] = heartRate;
      data[5] = cadence;
      data[6] = power;
    }
  }

  @VisibleForTesting
  PesoChartView getChartView() {
    return chartView;
  }

  @VisibleForTesting
  void setTripStatisticsUpdater(long time) {
    tripStatisticsUpdater = new TripStatisticsUpdater(time);
  }

  @VisibleForTesting
  void setChartView(PesoChartView view) {
    chartView = view;
  }

  @VisibleForTesting
  void setMetricUnits(boolean value) {
    metricUnits = value;
  }

  @VisibleForTesting
  void setReportSpeed(boolean value) {
    reportSpeed = value;
  }

  @VisibleForTesting
  void setChartByDistance(boolean value) {
    chartByDistance = value;
  }
}
