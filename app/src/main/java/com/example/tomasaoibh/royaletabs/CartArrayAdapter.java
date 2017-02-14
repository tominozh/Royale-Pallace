package com.example.tomasaoibh.royaletabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomas & Aoibh on 15/04/2016.
 */
public class CartArrayAdapter extends ArrayAdapter {
   Context context;
    int layoutResId;
    ArrayList<Order> objects;

    public CartArrayAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutResId=resource;
        this.objects=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DetailsHolder detailsHolder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResId,parent,false);
            detailsHolder = new DetailsHolder();
            detailsHolder.cartMealName=(TextView)row.findViewById(R.id.cart_row_name);
            detailsHolder.cartMealPrice=(TextView)row.findViewById(R.id.cart_row_price);
            detailsHolder.cartMealAmount=(TextView)row.findViewById(R.id.cart_row_amount);
            detailsHolder.cartMealTotal=(TextView)row.findViewById(R.id.cart_row_total);
            row.setTag(detailsHolder);
        } else {
            detailsHolder = (DetailsHolder) row.getTag();
        }
        Order order = objects.get(position);
        detailsHolder.cartMealName.setText(String.valueOf(order.getName()));
        detailsHolder.cartMealPrice.setText(String.format("%.2f",order.getPrice()));
        detailsHolder.cartMealAmount.setText(String.valueOf(order.getAmount()));
        detailsHolder.cartMealTotal.setText(String.format("%.2f",order.getTotal()));
        return row;
    }


    private class DetailsHolder {
        private TextView cartMealName;
        private TextView cartMealPrice;
        private TextView cartMealAmount;
        private TextView cartMealTotal;
    }
}

