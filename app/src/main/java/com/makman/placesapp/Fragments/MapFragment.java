package com.makman.placesapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makman.placesapp.Models.User;
import com.makman.placesapp.R;


public class MapFragment extends Fragment {

    private static final String ARG_USER = "ARG_USER";

    public static MapFragment newInstance(User user) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

}
