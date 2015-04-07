package ca.ualberta.cs.team16app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;

//Edited by Tiancheng Shen
//Some codes are from Google Developers "Google Maps Android API v2"
public class Map extends Activity implements OnMapReadyCallback{
	@Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_map);

     MapFragment mapFragment = (MapFragment) getFragmentManager()
             .findFragmentById(R.id.map);
     mapFragment.getMapAsync(Map.this);
 }
	
	@Override
	/**
	 * @param txt file
	 * @return latitude and longitude
	 * @throws FileNotFoundException and IOException
	 */
 public void onMapReady(GoogleMap map) {
		ArrayList<String> homeGeo = new ArrayList<String>();
		try {
	        FileInputStream inStream = this.openFileInput("homeGeo.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			String line = in.readLine();
			while (line != null) {
				homeGeo.add(line);
				line = in.readLine();
			}
	        in.close();
	        inStream.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    catch (IOException e){
	        return;
	    }
     LatLng home = new LatLng(Double.valueOf(homeGeo.get(0)), Double.valueOf(homeGeo.get(1)));

     map.setMyLocationEnabled(true);
     map.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 13));

     map.addMarker(new MarkerOptions()
             .title("Home")
             .position(home));
     
     ArrayList<String> destination = new ArrayList<String>();
		try {
	        FileInputStream inStream = this.openFileInput("destination.txt");
	        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			String line = in.readLine();
			while (line != null) {
				destination.add(line);
				line = in.readLine();
			}
	        in.close();
	        inStream.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    catch (IOException e){
	        return;
	    }
		
		for (int i = 0; i < destination.size(); i+=3){
			LatLng dest = new LatLng(Double.valueOf(destination.get(i+1)), Double.valueOf(destination.get(i+2)));
	        map.setMyLocationEnabled(true);
	        map.addMarker(new MarkerOptions()
	                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark))
	                .title(destination.get(i).toString())
	                .position(dest));
		}
     
 }

}
