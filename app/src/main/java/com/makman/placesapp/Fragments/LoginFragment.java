package com.makman.placesapp.Fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.makman.placesapp.Models.User;
import com.makman.placesapp.R;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class LoginFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = LoginFragment.class.getSimpleName();

    EditText mUserName;
    Button mLoginButton;
    Button mNewButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        mUserName = (EditText) rootView.findViewById(R.id.login_edit_text_name);
        mLoginButton = (Button)rootView.findViewById(R.id.login_button_login);
        mNewButton = (Button) rootView.findViewById(R.id.login_button_new_user);

        mLoginButton.setOnClickListener(this);
        mNewButton.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_button_login:
                loginClick();
                break;
            case R.id.login_button_new_user:
                newClick();
                break;
        }
    }


    public void newClick(){
        String name = validUserString();
        if(!name.equals("")) {
            Realm realm = Realm.getDefaultInstance();
            RealmQuery<User> query = realm.where(User.class);
            query.equalTo("mName", name);
            RealmResults<User> results = query.findAll();
            if(results.size() == 0){
                realm.beginTransaction();
                User user = realm.createObject(User.class);
                user.setmName(name);
                realm.commitTransaction();
            }else{
                CoordinatorLayout cView = (CoordinatorLayout) getActivity().findViewById(R.id.login_coordinator_layout);
                Snackbar.make(cView, R.string.login_user_exists, Snackbar.LENGTH_SHORT).show();
            }

        }else{
            CoordinatorLayout cView = (CoordinatorLayout) getActivity().findViewById(R.id.login_coordinator_layout);
            Snackbar.make(cView, R.string.login_enter_user_name, Snackbar.LENGTH_SHORT).show();
        }
    }


    public void loginClick(){
        String name = validUserString();
        if(!name.equals("")) {
            Realm realm = Realm.getDefaultInstance();
            RealmQuery<User> query = realm.where(User.class);
            query.equalTo("mName", name);
            RealmResults<User> results = query.findAll();
            User user = results.first();
            MapFragment map = MapFragment.newInstance(user);
            android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_activity_frame, map);
            transaction.commit();


        }else{
            CoordinatorLayout cView = (CoordinatorLayout) getActivity().findViewById(R.id.login_coordinator_layout);
            Snackbar.make(cView, R.string.login_enter_user_name, Snackbar.LENGTH_SHORT).show();
        }

    }

    private String validUserString(){
        String name = mUserName.getText().toString();
        name.replaceAll("\\s", "").equals("");
        return name;
    }


}
