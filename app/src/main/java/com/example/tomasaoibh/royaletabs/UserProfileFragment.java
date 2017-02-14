package com.example.tomasaoibh.royaletabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class UserProfileFragment extends Fragment {

    ProfileFragManager profileFragManager;


    public UserProfileFragment() {
    }

    public UserProfileFragment newInstance(String name, String address, String town, String phone, String email) {
        UserProfileFragment user = new UserProfileFragment();
        Bundle info = new Bundle();
        info.putCharSequence("NAME", name);
        info.putCharSequence("ADDRESS", address);
        info.putCharSequence("TOWN", town);
        info.putCharSequence("PHONE", phone);
        info.putCharSequence("EMAIL", email);
        user.setArguments(info);
        return user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_fragment, container, false);
        final TextView name = (TextView) view.findViewById(R.id.user_name);
        name.setText(getArguments().getString("NAME"));
        final TextView address = (TextView) view.findViewById(R.id.user_address);
        address.setText(getArguments().getString("ADDRESS"));
        final TextView town = (TextView) view.findViewById(R.id.user_town);
        town.setText(getArguments().getString("TOWN"));
        final TextView phone = (TextView) view.findViewById(R.id.user_phone);
        phone.setText(getArguments().getString("PHONE"));
        final TextView email = (TextView) view.findViewById(R.id.user_email);
        email.setText(getArguments().getString("EMAIL"));
        Button sendBtn = (Button) view.findViewById(R.id.user_send_button);
        Button editUserBtn = (Button) view.findViewById(R.id.edit_user_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileFragManager.proceedToOrder(new User(name.getText().toString(),
                        address.getText().toString(),town.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString()));
            }
        });
        editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileFragManager.EditUser();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            profileFragManager = (ProfileFragManager) context;
        } catch (ClassCastException e) {
            Log.i("CAST FRAG", "implement method " + e.getMessage().toString());
        }
    }

    public interface ProfileFragManager {
        void proceedToOrder(User user);

        void EditUser();
    }
}
