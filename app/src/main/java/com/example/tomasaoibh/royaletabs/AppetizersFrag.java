package com.example.tomasaoibh.royaletabs;


import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Tomas & Aoibh on 17/04/2016.
 */
public class AppetizersFrag extends ListFragment implements AdapterView.OnItemClickListener {
    //ArrayList starters = LoadMenus.getStarters();
    final int listID=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MealMenuListAdapter adapter = new MealMenuListAdapter(getActivity(), R.layout.row_meal_menu, LoadMenus.getAppetizers());
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Meal meal =LoadMenus.getAppetizers().get(position);
        final Order mOrder = new Order(meal.get_mealName(),0,meal.get_mealPrice(),false,false);
        MealDetailAlertFragment alertFragment = MealDetailAlertFragment.newInstance(mOrder,false);
        alertFragment.show(getFragmentManager(), "DIALOG");
    }


}
