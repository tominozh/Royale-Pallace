package com.example.tomasaoibh.royaletabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HaladekT on 05/04/2016.
 */

public class MealMenuListAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceID;
    ArrayList<Meal> myData;

    public MealMenuListAdapter(Context context, int resource, ArrayList myData) {
        super(context, resource, myData);
        this.context = context;
        this.layoutResourceID = resource;
        this.myData = myData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DetailsHolder detailsHolder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);
            detailsHolder = new DetailsHolder();
            detailsHolder.mealId = (TextView) row.findViewById(R.id.meal_id);
            detailsHolder.mealName = (TextView) row.findViewById(R.id.meal_name);
            detailsHolder.mealPrice = (TextView) row.findViewById(R.id.meal_price);
            row.setTag(detailsHolder);
        } else {
            detailsHolder = (DetailsHolder) row.getTag();
        }
        Meal mMeal = myData.get(position);
        detailsHolder.mealId.setText(String.valueOf(mMeal.get_id()));
        detailsHolder.mealName.setText(mMeal.get_mealName());
        detailsHolder.mealPrice.setText(String.format("%.2f",mMeal.get_mealPrice()));


        return row;
    }

    private class DetailsHolder {
        private TextView mealId;
        private TextView mealName;
        private TextView mealPrice;
    }
}
