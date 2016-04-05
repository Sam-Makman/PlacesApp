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
public class User extends RealmObject implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeList(this.mPlaces);
    }

    protected User(Parcel in) {
        this.mName = in.readString();
        this.mPlaces = new RealmList<Place>();
        in.readList(this.mPlaces, Place.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
