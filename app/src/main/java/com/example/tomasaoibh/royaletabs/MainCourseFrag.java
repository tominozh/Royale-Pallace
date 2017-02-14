package com.example.tomasaoibh.royaletabs;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
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
public class MainCourseFrag extends ListFragment implements AdapterView.OnItemClickListener {
    final int listID = 2;
    final String TAG = "MEAL_DETAIL";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MealMenuListAdapter adapter = new MealMenuListAdapter(getActivity(), R.layout.row_meal_menu, LoadMenus.getMainMeal());
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
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final Meal meal =LoadMenus.getMainMeal().get(position);
        final Order mOrder = new Order(meal.get_mealName(),0,meal.get_mealPrice(),true,false);
        final String holdName = mOrder.getName().concat("- ");
        ArrayList<String> options;
        options = getOptions(position);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(holdName);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, options);

        dialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sauce = adapter.getItem(which);
                if (sauce.contains("(")){
                    sauce = sauce.substring(0,sauce.indexOf("("));
                }
                mOrder.setName(holdName.concat(sauce));
                dialog.dismiss();
                MealDetailAlertFragment alertFragment = MealDetailAlertFragment.newInstance(mOrder,true);
                alertFragment.show(getFragmentManager(), TAG);

            }
        });
        dialog.show();
    }

    private ArrayList<String> getOptions(int position) {
        ArrayList<String> options = new ArrayList<String>();
        switch (position) {
            case 0:
                options.add("Hot Garlic Sauce");
                options.add("Sweet Sour Sauce");
                options.add("Salt & Chilli");
                options.add("Sweet Chilli Sauce");
                break;
            case 1:
                options.add("Hot Garlic Sauce");
                options.add("Salt & Chilli");
                options.add("Sweet Chilli Sauce");
                options.add("Sesame Honey Sauce");
                break;
            case 2:
                options.add("Curry Sauce");
                options.add("Black Pepper Sauce");
                options.add("Satay Sauce");
                options.add("Sweet Chilli Sauce");
                break;
            case 3:
                options.add("Curry Sauce");
                options.add("Black Pepper Sauce");
                options.add("Satay Sauce");
                options.add("Sweet Chilli Sauce");
                break;
            case 4:
                options.add("Sweet & Sour");
                options.add("Sweet Chilli Sauce");
                options.add("Satay Sauce");
                options.add("Kar - kee Sauce");
                break;
            case 5:
                options.add("Sweet & Sour");
                options.add("Sweet Chilli Sauce");
                options.add("Salt & Chilli");
                options.add("Kar - kee Sauce");
                break;
            case 6:
                options.add("Cantonese Style");
                options.add("Plum Sauce");
                options.add("King Do Sauce");
                options.add("Oyster Sauce");
                break;
            case 7:
                options.add("Satay Sauce");
                options.add("Kung Po Sauce");
                options.add("Szechuan Style");
                options.add("Hot Garlic Sauce");
                break;
            case 8:
                options.add("Black Bean Sauce");
                options.add("King Do Sauce");
                options.add("Szechuan Style");
                options.add("Satay Sauce");
                break;
            case 9:
                options.add("Green Pepper Black Bean Sauce");
                options.add("Mushroom Black Bean Sauce");
                options.add("Black Pepper Sauce");
                options.add("Chinese Style");
                options.add("Satay Sauce");
                break;
            case 10:
                options.add("Green Pepper Black Bean Sauce");
                options.add("Mushroom Black Bean Sauce");
                options.add("Szechuan Style (Cooked with Vegetables in Hot and Sweetie Sauce)");
                options.add("Kung Po Sauce (Little Sweet and Medium Spicy with Cashew Nuts)");
                options.add("Satay Sauce (Peanut Creamed, bit Spicy & Sweet)");
                options.add("Chinese Style (Fruity Sauce)");
                options.add("Black Pepper Sauce");
                options.add("Cashew Nuts (Stir Fried mixed Veg in Savoury Sauce & Cashew Nuts)");
                options.add("Thai Special Spicy (Sweet & Spicy)");
                options.add("Curry Sauce");
               break;

        }
        return options;
    }
}
