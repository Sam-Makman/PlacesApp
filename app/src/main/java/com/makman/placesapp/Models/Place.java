package com.makman.placesapp.Models;

import io.realm.RealmObject;

/**
 * Created by sam on 4/2/16.
 */
public class Place extends RealmObject{
    private String mTitle;
    private String mDescription;
    private double mLatitude;
    private double mLongitude;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }
}
