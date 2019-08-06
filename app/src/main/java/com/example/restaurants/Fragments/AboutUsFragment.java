package com.example.restaurants.Fragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.example.restaurants.R;


public class AboutUsFragment extends Fragment {

    TextView about_us_text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        about_us_text = rootView.findViewById(R.id.about_us_textView);
        about_us_text.setMovementMethod(new ScrollingMovementMethod());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("About us");
        NavigationView view = getActivity().findViewById(R.id.nav_view);
        view.getMenu().getItem(3).setChecked(true); }
}
