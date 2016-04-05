package com.makman.placesapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;


public class MarkerDialogFragment extends DialogFragment implements View.OnClickListener{

    EditText mTitle;
    EditText mDescription;
    Button mOkButton;
    Button mCancelButton;

    LatLng mLatlng;
    DialogListener mListener;

    interface DialogListener{
        void onDialogComplete(LatLng coords, String title, String description);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_marker_dialog, container);
        getDialog().setTitle(R.string.dialog_new_marker);
        mTitle  = (EditText) rootView.findViewById(R.id.dialog_title);
        mDescription = (EditText) rootView.findViewById(R.id.dialog_description);
        mOkButton = (Button) rootView.findViewById(R.id.dialog_ok);
        mOkButton.setOnClickListener(this);
        mCancelButton = (Button) rootView.findViewById(R.id.dialog_cancel);
        mCancelButton.setOnClickListener(this);
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
        if(v.getId() == mOkButton.getId()) {
            mListener.onDialogComplete(mLatlng, mTitle.getText().toString(), mDescription.getText().toString());
        }
        dismiss();
    }

}
