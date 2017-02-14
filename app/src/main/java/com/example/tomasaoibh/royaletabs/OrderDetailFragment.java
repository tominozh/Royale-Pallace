package com.example.tomasaoibh.royaletabs;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomas & Aoibh on 01/05/2016.
 */
public class OrderDetailFragment extends DialogFragment {
    private TrashListener trashListener;
    public OrderDetailFragment(){}

    public static OrderDetailFragment newInstance(String name,double total,int amount,boolean isMainMeal,int position){
        OrderDetailFragment detail = new OrderDetailFragment();
        Bundle info = new Bundle();
        info.putString("NAME",name);
        info.putDouble("TOTAL",total);
        info.putInt("POSITION",position);
        info.putBoolean("MAIN",isMainMeal);
        info.putInt("AMOUNT",amount);
        detail.setArguments(info);
        return detail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.order_detail,container);
        Button trashBtn = (Button) view.findViewById(R.id.order_detail_trash_btn);
        Button cancelBtn = (Button)view.findViewById(R.id.cancel_detail_order_button);
        TextView orderName = (TextView)view.findViewById(R.id.order_detail_name);
        TextView orderTotal = (TextView)view.findViewById(R.id.order_detail_total);
        orderName.setText(getArguments().getString("NAME").toUpperCase());
        orderTotal.setText(getString(R.string.total_for_this_item)+String.format("%.2f",getArguments().getDouble("TOTAL")));
        final ArrayList<Integer> mealPosition = new ArrayList<Integer>();
        mealPosition.add(getArguments().getInt("POSITION"));
        TextView freeSide = (TextView)view.findViewById(R.id.order_detail_free_side);
        freeSide.setEnabled(false);
        if(getArguments().getBoolean("MAIN")){
            freeSide.setEnabled(true);
            freeSide.setPadding(16,16,16,16);
            freeSide.setText("and following free side:\n");
            int amount=getArguments().getInt("AMOUNT");
            int position = getArguments().getInt("POSITION");
            for (int i=0;i!=amount;i++){
                position=position+1;
                freeSide.append("\n"+LoadMenus.getCart().get(position).getName().toUpperCase()+"\n");
                mealPosition.add(position);
            }

        }
        trashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trashListener.trashClicked(mealPosition);
                getDialog().dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        try {
            trashListener = (TrashListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement TrashListener interface");
        }
        super.onAttach(activity);
    }

    public void onResume() {

        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();

        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        // Set the width of the dialog proportional to 75% of the screen width

        window.setLayout((int) (size.x * 0.95), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        // Call super onResume after sizing
        super.onResume();

    }

    public interface TrashListener{
       void trashClicked(ArrayList<Integer>mealPosition);
    }
}
