/*
 * 	   Created by Daniel Nadeau
 * 	   daniel.nadeau01@gmail.com
 * 	   danielnadeau.blogspot.com
 * 
 * 	   Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.google.android.maps.mytracks.myappexample;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LineGraph.OnPointClickedListener;
import com.echo.holographlibrary.LinePoint;
import com.google.android.maps.mytracks.R;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CopyOfGraficoPersonaleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
    	
    	final View v = inflater.inflate(R.layout.fragment_linegraph, container, false);
        final Resources resources = getResources();
        Line l = new Line();
        
        l.setUsingDips(true);
        LinePoint p = new LinePoint();
        p.setX(0);
        p.setY(5);
        p.setColor(resources.getColor(R.color.red));
        p.setSelectedColor(resources.getColor(R.color.transparent_blue));
        l.addPoint(p);
        p = new LinePoint();
        p.setX(8);
        p.setY(8);
        
        p.setColor(resources.getColor(R.color.blue));
        l.addPoint(p);
        p = new LinePoint();
        p.setX(10);
        p.setY(4);
        l.addPoint(p);
        p.setColor(resources.getColor(R.color.green));
        l.setColor(resources.getColor(R.color.orange));

        LineGraph li = (LineGraph) v.findViewById(R.id.linegraph);
        li.setUsingDips(true);
        li.addLine(l);
        li.setRangeY(0, 10);
        li.setLineToFill(2);
        

        li.setOnPointClickedListener(new OnPointClickedListener() {

            @Override
            public void onClick(int lineIndex, int pointIndex) {

            }

        });

        return v;
    }
}
