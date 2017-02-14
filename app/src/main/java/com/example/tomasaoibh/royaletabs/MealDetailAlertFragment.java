package com.example.tomasaoibh.royaletabs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HaladekT on 07/04/2016.
 */
public class MealDetailAlertFragment extends DialogFragment {


    FragButtonsListener myListener;


    public MealDetailAlertFragment() {
    }

    public static MealDetailAlertFragment newInstance(Order mMeal, boolean subMenu) {
        MealDetailAlertFragment dialog = new MealDetailAlertFragment();

        Bundle args = new Bundle();
        args.putString("NAME", mMeal.getName());
        args.putDouble("PRICE", mMeal.getPrice());
        args.putBoolean("SUBMENU", subMenu);
        dialog.setArguments(args);
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        final boolean isVib = prefs.getBoolean("UI_VIBRATION", true);
        final int[] mealCount = {0};
        final Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        final View view = inflater.inflate(R.layout.detail_fragment, container, false);
        final boolean subMenu = getArguments().getBoolean("SUBMENU");
        ImageButton plusBtn = (ImageButton) view.findViewById(R.id.plus_button);
        ImageButton minusBtn = (ImageButton) view.findViewById(R.id.minus_button);
        Button yesBtn = (Button) view.findViewById(R.id.yes_button);
        Button noBtn = (Button) view.findViewById(R.id.no_button);

        final TextView name = (TextView) view.findViewById(R.id.detail_name);
        final String orderName = getArguments().getString("NAME");
        name.setText(orderName);
        final TextView price = (TextView) view.findViewById(R.id.detail_price);
        final double orderPrice = getArguments().getDouble("PRICE");
        price.setText(String.format("%.2f", orderPrice));
        final TextView amount = (TextView) view.findViewById(R.id.amount);


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCount[0]++;
                amount.setText(String.valueOf(mealCount[0]));
                price.setText(String.format("%.2f", (mealCount[0] * orderPrice)));
                if (isVib) {
                    vibrator.vibrate(50);
                }

            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mealCount[0] > 0) {
                    mealCount[0]--;
                    String text = String.valueOf(mealCount[0]);
                    amount.setText(text);
                    price.setText(String.format("%.2f", (mealCount[0] * orderPrice)));
                    if (isVib) {
                        vibrator.vibrate(50);
                    }
                } else {
                    mealCount[0] = 0;
                    amount.setText(String.valueOf(mealCount[0]));
                }
            }
        });
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mealCount[0] > 0) {
                    myListener.yesButton(mealCount[0], orderName, orderPrice, subMenu);
                    if (isVib) {
                        vibrator.vibrate(40);
                    }
                    getDialog().dismiss();

                } else {
                    Snackbar.make(view, "Please add amount", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVib) {
                    vibrator.vibrate(40);
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myListener = (FragButtonsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragButtonListener");
        }
    }

    public void onResume() {

        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();

        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        // Set the width of the dialog proportional to 95% of the screen width
        window.setLayout((int) (size.x * 0.95), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        // Call super onResume after sizing
        super.onResume();

    }

    public interface FragButtonsListener {
        void yesButton(int num, String name, Double price, boolean subMenu);
    }
}

