package com.example.tomasaoibh.royaletabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tomas & Aoibh on 26/04/2016.
 */
public class CreateUserProfile extends Fragment {
    CreateUserButtonListener createUserListener;

    public CreateUserProfile() {
    }

    public CreateUserProfile newInstance(Context context, String email, Boolean repeat) {
        CreateUserProfile userProfile = new CreateUserProfile();
        Bundle info = new Bundle();
        if (repeat) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            info.putString("NAME", prefs.getString("USER_NAME", "empty"));
            info.putString("ADDRESS", prefs.getString("USER_ADDRESS", "empty"));
            info.putString("TOWN", prefs.getString("USER_TOWN", "empty"));
            info.putString("PHONE", prefs.getString("USER_PHONE", "empty"));
            info.putString("EMAIL", email);
            info.putBoolean("REPEAT", repeat);
        } else {
            info.putString("EMAIL", email);
            info.putBoolean("REPEAT", repeat);
        }
        userProfile.setArguments(info);
        return userProfile;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.user_input_info_fragment, container, false);
        boolean isRepeating = getArguments().getBoolean("REPEAT");
        final EditText name = (EditText) view.findViewById(R.id.user_name_et);
        final EditText address = (EditText) view.findViewById(R.id.user_address_et);
        final EditText town = (EditText) view.findViewById(R.id.user_town_et);
        final EditText phone = (EditText) view.findViewById(R.id.user_phone_et);
        final TextView email = (TextView) view.findViewById(R.id.user_email_et);
        if (isRepeating) {
            name.setText(getArguments().getString("NAME","empty"));
            address.setText(getArguments().getString("ADDRESS","empty"));
            town.setText(getArguments().getString("TOWN","empty"));
            phone.setText(getArguments().getString("PHONE","empty"));
            email.setText(getArguments().getString("EMAIL","empty"));
        } else {
            email.setText(getArguments().getString("EMAIL"));
        }
        final Button createUserBtn = (Button) view.findViewById(R.id.user_send_button_et);
        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(name.getText().toString(),
                        address.getText().toString(),
                        town.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString());
                if (!name.getText().toString().equals("")) {
                    if (!address.getText().toString().equals("")) {
                        if (!town.getText().toString().equals("")) {
                            if (!phone.getText().toString().equals("")) {
                                if (!email.getText().toString().equals("")) {
                                    createUserListener.createAndSaveUser(user);
                                } else {
                                    Snackbar.make(view,"Fill Email",Snackbar.LENGTH_SHORT).show();
                                }
                            } else {
                                Snackbar.make(view, "Fill Phone", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(view, "Fill Town", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(view, "Fill Address", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(view, "Fill Name",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createUserListener = (CreateUserButtonListener) context;
        } catch (Exception e) {
            Log.i("CAST", "exc " + context.toString());
        }
    }

    interface CreateUserButtonListener {
        void createAndSaveUser(User user);
    }

}