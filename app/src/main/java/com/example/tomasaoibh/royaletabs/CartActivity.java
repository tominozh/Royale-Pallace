package com.example.tomasaoibh.royaletabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements OrderDetailFragment.TrashListener {
    private ListView cartListView;
    private double totalPrice;
    ArrayList<Order> myCart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOrder();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartListView = (ListView) findViewById(R.id.cartListView);
        cartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = LoadMenus.getCart().get(position);
                if (order.isFreeSide()) {
                    Snackbar.make(view,"Free side can not be deleted",Snackbar.LENGTH_SHORT).show();
                } else {
                    OrderDetailFragment detailFragment =
                            OrderDetailFragment.newInstance(
                                    order.getName(), order.getTotal(), order.getAmount(), order.isMainMeal(), position);
                    detailFragment.show(getSupportFragmentManager(), "DETAIL");
                }
            }
        });
        myCart = LoadMenus.getCart();
        Log.i("CART", "size " + myCart.size());
        if (myCart.size() > 0) {
            CartArrayAdapter adapter = new CartArrayAdapter(CartActivity.this, R.layout.row_cart, myCart);
            cartListView.setAdapter(adapter);
            calculateTotal();
        }
    }

    private void calculateTotal() {
        totalPrice = 0;
        for (int i = 0; i < myCart.size(); i++) {
            Order order = LoadMenus.getCart().get(i);
            totalPrice = totalPrice + order.getTotal();
        }
        TextView myTotal = (TextView) findViewById(R.id.price_tw);
        myTotal.setText(String.format("%.2f", totalPrice));
    }

    private void sendOrder() {
        if (myCart.size() > 0) {
            Intent intent = new Intent(CartActivity.this, SendActivity.class);
            intent.putExtra("TOTAL", totalPrice);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Cart Empty", Toast.LENGTH_SHORT).show();
        }

    }

    private void newOrder() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Clean your Cart?");
        dialog.setIcon(R.drawable.ic_info_black_24dp);
        dialog.setMessage("Are You Sure");
        dialog.setCancelable(false);
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isCleaned = LoadMenus.wipeCart();
                if (isCleaned) {
                    cartListView.invalidateViews();
                    totalPrice = 0.00;
                    TextView myTotal = (TextView) findViewById(R.id.price_tw);
                    myTotal.setText(String.format("%.2f", totalPrice));

                }
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void trashClicked(ArrayList<Integer> mealPosition) {
        for (int i = 0; i != mealPosition.size(); i++) {
            int pos = (mealPosition.size() - 1) - i;
            LoadMenus.deleteOrder(mealPosition.get(pos));
        }
        cartListView.invalidateViews();
        calculateTotal();
        Toast.makeText(CartActivity.this, mealPosition.size() + "Items Deleted", Toast.LENGTH_SHORT).show();

    }

    private void exitAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT");
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(CartActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_send_order) {
            sendOrder();
            return true;
        }
        if (id == R.id.action_new_order) {
            newOrder();
            return true;
        } else if (id == R.id.action_exit) {
            exitAlert();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
