package com.makman.placesapp;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;


public class MarkerDialogFragment extends DialogFragment implements View.OnClickListener{

    EditText mTitle;
    EditText mDescription;
    Button mButton;

    LatLng mLatlng;
    DialogListener mListener;

    interface DialogListener{
        void onDialogComplete(LatLng coords, String title, String description);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_marker_dialog, container);

        mTitle  = (EditText) rootView.findViewById(R.id.dialog_title);
        mDescription = (EditText) rootView.findViewById(R.id.dialog_description);
        mButton = (Button) rootView.findViewById(R.id.dialog_ok);
        mButton.setOnClickListener(this);
        return rootView;
    }
    public void setmLatlng(LatLng coords){
        mLatlng = coords;
    }

    public void setOnClickListener(DialogListener listener){
        mListener = listener;
    }


    @Override
    public void onClick(View v) {
        mListener.onDialogComplete(mLatlng, mTitle.getText().toString(), mDescription.getText().toString());
    }
}
