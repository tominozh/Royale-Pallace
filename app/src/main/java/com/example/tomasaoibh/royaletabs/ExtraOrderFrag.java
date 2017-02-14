package com.example.tomasaoibh.royaletabs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by Tomas & Aoibh on 18/04/2016.
 */
public class ExtraOrderFrag extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MealMenuListAdapter adapter = new MealMenuListAdapter(getActivity(),R.layout.row_meal_menu,LoadMenus.getExtras());
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
        final Meal meal =LoadMenus.getExtras().get(position);
        final Order mOrder = new Order(meal.get_mealName(),1,meal.get_mealPrice(),false,false);

        LoadMenus.addToCart(mOrder);
    }


}
