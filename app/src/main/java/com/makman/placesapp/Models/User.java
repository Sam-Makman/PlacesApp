package com.makman.placesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Realm user model
 */
public class User extends RealmObject {

    @PrimaryKey
    private String mName;
    private RealmList<Place> mPlaces;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public RealmList<Place> getmPlaces() {
        return mPlaces;
    }

    public void setmPlaces(RealmList<Place> mPlaces) {
        this.mPlaces = mPlaces;
    }

}
