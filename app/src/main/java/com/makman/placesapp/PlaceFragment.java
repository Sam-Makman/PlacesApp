package com.makman.placesapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.makman.placesapp.Models.Place;
import com.makman.placesapp.Models.User;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class PlaceFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, MarkerDialogFragment.DialogListener {

    private static final String ARG_USER = "ARG_USER";

    SupportMapFragment mSupportFragment;
    GoogleMap mMap;
    User mUser;

    public static PlaceFragment newInstance(User user) {
        PlaceFragment fragment = new PlaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER, user.getmName());
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            String name = "";
            if(getArguments() != null){

                name = getArguments().getString(ARG_USER, "");
            }

        if(!name.equals("")) {
            Realm realm = Realm.getDefaultInstance();
            RealmQuery<User> query = realm.where(User.class);
            query.equalTo("mName", name);
            RealmResults<User> results = query.findAll();
            mUser = results.first();
        }

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        mSupportFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        setHasOptionsMenu(true);


        if(mSupportFragment == null){
            mSupportFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mSupportFragment).commit();
        }
        mSupportFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        RealmList<Place> results = mUser.getmPlaces();
        for(Place place : results){
            mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(place.getmLatitude(), place.getmLongitude()))
                            .title(place.getmTitle())
                            .snippet(place.getmDescription())
            );
        }

    }

    @Override
    public void onMapClick(LatLng latLng) {
        android.app.FragmentManager manager = getActivity().getFragmentManager();
        MarkerDialogFragment dialog = new MarkerDialogFragment();
        dialog.setmLatlng(latLng);
        dialog.setOnClickListener(this);
        dialog.show(manager, "marker_dialog_fragment");


    }


    @Override
    public void onDialogComplete(LatLng coords, String title, String description) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Place place = realm.createObject(Place.class);
        place.setmDescription(description);
        place.setmTitle(title);
        place.setmLatitude(coords.latitude);
        place.setmLongitude(coords.longitude);
        mUser.getmPlaces().add(place);

        realm.commitTransaction();

        mMap.addMarker(new MarkerOptions()
                        .position(coords)
                        .title(title)
                        .snippet(description)
        );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(0,0,0,R.string.logout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 0:
                android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_frame, new LoginFragment());
                transaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
