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

    SharedPreferences sharedPreferences;
    int locationCount;

    int countPosts;
    String Markerd="FALSE";


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

        sharedPreferences=this.getActivity().getSharedPreferences("location",0);
        locationCount = sharedPreferences.getInt("locationCount", 0);

        // If locations are already saved
        if (locationCount != 0) {
            String title="";
            String lat = "";
            String lon = "";

            // Iterating through all the locations stored
            for (int i = 0; i < locationCount; i++) {

                Markerd= sharedPreferences.getString("Markerd"+ i,"FALSE");
                if(Markerd.equals("FALSE")) {

                    title = sharedPreferences.getString("title" + i, "0");

                    lat = sharedPreferences.getString("lat" + i, "0");

                    lon = sharedPreferences.getString("lon" + i, "0");

                    double lat_from_SP = Double.valueOf(lat).doubleValue();

                    double lon_from_SP = Double.valueOf(lon).doubleValue();

                    countPosts = 1;

                    for (int j = 0; j < locationCount; j++) {
                        if (title.equals((sharedPreferences.getString("title" + j, "0")))) {
                            countPosts++;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Markerd"+Integer.toString(j),"TRUE");
                            editor.commit();
                        }
                    }


                    countPosts = countPosts - 1;
                    this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat_from_SP, lon_from_SP)).title(title).snippet("Posts:" + countPosts));
                    countPosts = 0;

                }

            }

            for (int k = 0; k < locationCount; k++) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Markerd"+Integer.toString(k),"FALSE");
                editor.commit();


            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("locationCount", locationCount);

            editor.commit();

        }

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



















