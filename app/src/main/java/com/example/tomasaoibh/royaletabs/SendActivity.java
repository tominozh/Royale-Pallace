package com.example.tomasaoibh.royaletabs;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class SendActivity extends AppCompatActivity implements CreateUserProfile.CreateUserButtonListener, UserProfileFragment.ProfileFragManager {

    String uName, uAddress, uTown, uPhone, uEmail;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        uName = prefs.getString("USER_NAME", "empty");
        uAddress = prefs.getString("USER_ADDRESS", "empty");
        uTown = prefs.getString("USER_TOWN", "empty");
        uPhone = prefs.getString("USER_PHONE", "empty");
        uEmail = prefs.getString("USER_EMAIL", "empty");
        uEmail = getUsername();
        Log.i("SETTINGS", "uNeame " + uName);
        boolean isProfileCreated=false;
        if (uName.equals("empty")) {
            isProfileCreated = false;
        }
        if (uAddress.equals("empty")) {
            isProfileCreated = false;
        }
        if (uTown.equals("empty")) {
            isProfileCreated = false;
        }
        if (uPhone.equals("empty")) {
            isProfileCreated = false;
        }
        if(!isProfileCreated){
        CreateUserProfile fragment1 = new CreateUserProfile().newInstance(getApplicationContext(), uEmail, false);
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, fragment1).commit();
    }else{
        UserProfileFragment fragment2 = new UserProfileFragment().newInstance(uName, uAddress, uTown, uPhone, uEmail);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frag_container, fragment2);
        ft.commit();
    }

    Intent intent = getIntent();
    total=intent.getDoubleExtra("TOTAL",0);
    TextView uTotal = (TextView) findViewById(R.id.total_order);
    uTotal.setText(String.format("%.2f",total).

    concat(" Euros")

    );

}

    public String getUsername() {
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();
        for (Account account : accounts) {
            // TODO: Check possibleEmail against an email regex or treat
            // account.name as an email address only for certain account.type
            // values.
            possibleEmails.add(account.name);
        }
        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            String email = possibleEmails.get(0);
            return email;
        } else {
            return null;
        }
    }

    @Override
    public void createAndSaveUser(User user) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putString("USER_NAME", user.getName());
        editor.putString("USER_ADDRESS", user.getAddress());
        editor.putString("USER_TOWN", user.getTown());
        editor.putString("USER_PHONE", user.getPhone());
        editor.putString("USER_EMAIL", user.getEmail());
        editor.apply();
        Log.i("ADD_PROFILE", "user " + user.getName());
        UserProfileFragment frag = new UserProfileFragment().newInstance(
                user.getName(), user.getAddress(), user.getTown(), user.getPhone(), user.getEmail());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frag_container, frag);
        ft.commit();
    }

    @Override
    public void proceedToOrder(User userObj) {

        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order from " + userObj.getName() +
                " to " + userObj.getAddress() + " tel " + userObj.getPhone());
        intent.putExtra(Intent.EXTRA_TEXT, getOrderAsTXT(userObj));
        intent.setData(Uri.parse("mailto:tominozh@yahoo.ie")); // recipient address
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app,
        // your app is displayed, instead of the email app.
        startActivity(intent);
    }

    private String getOrderAsTXT(User user) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LoadMenus.getCart().size(); i++) {
            Order order = new Order();
            order = LoadMenus.getCart().get(i);
            sb.append(order.toString()).append("****\n\n\n");
        }
        sb.append("Delivery Address");
        sb.append(user.toString());
        sb.append("------------\n" + "TOTAL FOR THIS ORDER: " + String.format("%.2f", total));


        return sb.toString();
    }

    @Override
    public void EditUser() {
        CreateUserProfile createProfile = new CreateUserProfile().newInstance(getApplicationContext(), uEmail, true);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frag_container, createProfile);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(SendActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_new_order) {
            LoadMenus.wipeCart();
            Intent intent = new Intent(SendActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_exit) {
            exitAlert();
            return true;
        }
        return false;
    }

    private void exitAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT");
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishAffinity();

            }
        });
        builder.show();
    }
}
