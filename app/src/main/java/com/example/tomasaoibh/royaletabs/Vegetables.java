package com.example.tomasaoibh.royaletabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
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
public class Vegetables extends ListFragment implements AdapterView.OnItemClickListener{
    final int listID=2;
    final String TAG = "MEAL_DETAIL";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MealMenuListAdapter adapter = new MealMenuListAdapter(getActivity(),R.layout.row_meal_menu,LoadMenus.getVegetables());
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
        final Meal meal =LoadMenus.getVegetables().get(position);
        final Order mOrder = new Order(meal.get_mealName(),0,meal.get_mealPrice(),false,false);
        final String holdName = mOrder.getName().concat("- ");

        ArrayList<String> options = new ArrayList<String>();
        options = getOptions(position);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(holdName);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, options);

        dialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sauce = adapter.getItem(which);

                mOrder.setName(holdName.concat(sauce));
                dialog.dismiss();
                MealDetailAlertFragment alertFragment = MealDetailAlertFragment.newInstance(mOrder,false);
                alertFragment.show(getFragmentManager(), TAG);

            }
        });
        dialog.show();

    }

    private ArrayList<String> getOptions(int position) {
        ArrayList<String> options = new ArrayList<String>();
        switch (position){
            case 0:
                options.add("Coke");
                options.add("Sprite");
                options.add("Fanta");
                options.add("Club Orange");
                options.add("Club Rock-Shandy");
                break;
            case 1:
                options.add("Coke");
                options.add("Sprite");
                options.add("Fanta");
                options.add("Club Orange");
                options.add("Club Rock-Shandy");
                break;
            case 2:
                options.add("Apple");
                options.add("Strawberry");
                options.add("Mango");
                options.add("Orange");
                break;
        }
        return options;
    }

}
