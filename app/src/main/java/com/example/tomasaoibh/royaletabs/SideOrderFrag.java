package com.example.tomasaoibh.royaletabs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Tomas & Aoibh on 18/04/2016.
 */
public class SideOrderFrag extends ListFragment implements AdapterView.OnItemClickListener {
    final int listID=3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MealMenuListAdapter adapter = new MealMenuListAdapter(getActivity(),R.layout.row_meal_menu,LoadMenus.getSides());
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Meal meal =LoadMenus.getSides().get(position);
        final Order mOrder = new Order(meal.get_mealName(),0,meal.get_mealPrice(),false,false);
        MealDetailAlertFragment alertFragment = MealDetailAlertFragment.newInstance(mOrder,false);
        alertFragment.show(getFragmentManager(), "DIALOG");
    }

}
