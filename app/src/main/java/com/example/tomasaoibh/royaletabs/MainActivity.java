package com.example.tomasaoibh.royaletabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.tomasaoibh.royaletabs.R.styleable.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MealDetailAlertFragment.FragButtonsListener, SideOrderDialog.SideOrderButtonListener {


    private SectionPagerAdapter mSectionsPagerAdapter;
    private Vibrator vibrator;
    private static int count = 0;
    private ViewPager mViewPager;
    private Order mOrder;
    private boolean isVib = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        isVib = prefs.getBoolean("UI_VIBRATION", false);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        FragmentManager fm = getSupportFragmentManager();
        mSectionsPagerAdapter = new SectionPagerAdapter(fm);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if (isVib) {
                    vibrator.vibrate(40);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_exit) {
            exitAlert();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.new_order) {
            LoadMenus.wipeCart();
            if (isVib) {
                vibrator.vibrate(40);
            }
        } else if (id == R.id.review_order) {
            Intent intent = new Intent(this, CartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            if (isVib) {
                vibrator.vibrate(40);
            }
            startActivity(intent);

        } else if (id == R.id.call) {
            String uri = "tel:016102705";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);

        } else if (id == R.id.exitApp) {
            exitAlert();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exitAlert();
        }
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

    //this method overides interface from MealDetailAlertFragment
    @Override
    public void yesButton(int num, String name, Double price, boolean subMenu) {
        if (!subMenu) {
            mOrder = new Order();
            mOrder.setName(name);
            mOrder.setAmount(num);
            mOrder.setPrice(price);
            mOrder.setTotal(price * num);
            LoadMenus.addToCart(mOrder);
        }
        if (subMenu) {
            mOrder = new Order();
            mOrder.setName(name);
            mOrder.setAmount(num);
            mOrder.setPrice(price);
            mOrder.setTotal(price * num);
            mOrder.setMainMeal(true);
            LoadMenus.addToCart(mOrder);

            final String[] sides = {"Free Boiled Rice", "Free Fried Rice", "Free Chips", "Noodles - 2.50e extra"};
            SideOrderDialog sideOrderDialog = (SideOrderDialog) SideOrderDialog.newInstance(sides, num);
            sideOrderDialog.show(getSupportFragmentManager(), "SIDE");

        }
        if (isVib) {
            vibrator.vibrate(40);
        }
    }

    @Override
    public void clickAdd(ArrayList list) {
        if (isVib) {
            vibrator.vibrate(40);
        }
        Log.i("ADD", "size " + list.size());
        for (int i = 0; i < list.size(); i++) {
            mOrder = new Order();
            mOrder.setAmount(1);
            mOrder.setFreeSide(true);
            int hold = (int) list.get(i);
            Log.i("Side", "number " + hold);
            switch (hold) {
                case 0:
                    mOrder.setName("Free Boiled Rice");
                    mOrder.setPrice(0.00);
                    mOrder.setTotal(0.00);
                    LoadMenus.addToCart(mOrder);
                    break;
                case 1:
                    mOrder.setName("Free Fried Rice");
                    mOrder.setPrice(0.00);
                    mOrder.setTotal(0.00);
                    LoadMenus.addToCart(mOrder);
                    break;
                case 2:
                    mOrder.setName("Free Chips");
                    mOrder.setPrice(0.00);
                    mOrder.setTotal(0.00);
                    LoadMenus.addToCart(mOrder);
                    break;
                case 3:
                    mOrder.setName("Noodles");
                    mOrder.setPrice(2.50);
                    mOrder.setTotal(2.50);
                    LoadMenus.addToCart(mOrder);
                    break;
            }
        }
    }
}
