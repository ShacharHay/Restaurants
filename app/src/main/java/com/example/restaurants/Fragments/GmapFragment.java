package com.example.restaurants.Fragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.example.restaurants.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class GmapFragment extends Fragment implements OnMapReadyCallback {

    public static List<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();
    public static MarkerOptions marker;

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;


    //
    SharedPreferences sharedPreferences;
    int locationCount;
//

    public GmapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_gmap, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        mMapView=(MapView)mView.findViewById(R.id.map);
        if(mMapView!=null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);

        //del if not work
        sharedPreferences=this.getActivity().getSharedPreferences("location",0);
        locationCount = sharedPreferences.getInt("locationCount", 0);
        // If locations are already saved
        if (locationCount != 0) {
            String title="";
            String lat = "";
            String lon = "";

            // Iterating through all the locations stored
            for (int i = 0; i < locationCount; i++) {

                title=sharedPreferences.getString("title"+i,"0");

                lat = sharedPreferences.getString("lat" + i, "0");

                lon = sharedPreferences.getString("lon" + i, "0");

                double lat_from_SP = Double.valueOf(lat).doubleValue();

                double lon_from_SP = Double.valueOf(lon).doubleValue();

                this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat_from_SP, lon_from_SP)).title(title).snippet(""));
                //test
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("locationCount", locationCount);
                editor.commit();
                //test
            }

        }

        //del if not work




        //  addExistsMarkers();


        /*if (this.markerOptions != null && !this.markerOptions.isEmpty()) {
            for (MarkerOptions markerOption : this.markerOptions) {
                double lon = markerOption.getPosition().longitude;
                double lat = markerOption.getPosition().latitude;
                String title = markerOption.getTitle();
                this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(title).snippet("from ok place"));


            }
        }*/ //to return afther test


    }



    public  void placeMarker(String title,double lat,double lon) {





/*
      marker=new MarkerOptions();
      marker.title(title);
       marker.position(new LatLng(lat,lon));
       marker = new MarkerOptions().title(title).position(new LatLng(lat, lon));
        this.markerOptions.add(marker);
*/ //return afther test

    }

    public void addExistsMarkers()
    {
        this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(31.046051,34.851612)).title("Israel"));
        this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(23.634501,-102.552784)).title("Mexico"));
        this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(	15.870032,100.992541)).title("Thailand"));
        this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(41.87194,12.56738)).title("Italy"));
        this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(18.220833,66.590149)).title("Puerto Rico"));
    }


    @Override
    public void onResume() {
        super.onResume();
        Activity currentActivity = getActivity();

        currentActivity.setTitle("Map");
        NavigationView view = currentActivity.findViewById(R.id.nav_view);
        view.getMenu().getItem(6).setChecked(true);
    }


}



















