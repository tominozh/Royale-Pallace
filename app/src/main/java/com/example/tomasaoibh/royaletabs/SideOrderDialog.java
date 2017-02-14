package com.example.tomasaoibh.royaletabs;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tomas & Aoibh on 29/04/2016.
 */
public class SideOrderDialog extends DialogFragment {

    SideOrderButtonListener btnListener;
    FreeSideAdapter adapter;

    public SideOrderDialog() {
    }

    public static SideOrderDialog newInstance(String[] mySides, int num) {
        SideOrderDialog dialog = new SideOrderDialog();
        Bundle info = new Bundle();
        info.putStringArray("SIDES", mySides);
        info.putInt("NUM", num);
        dialog.setArguments(info);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final int[] count = {0};
        final ArrayList<Integer> addSide = new ArrayList<>();
        View view = inflater.inflate(R.layout.side_list_view, container);
        final ListView listView = (ListView) view.findViewById(R.id.listView_free_side);

        Button btnBack = (Button) view.findViewById(R.id.side_list_button_back);
        Button btnAdd = (Button) view.findViewById(R.id.side_list_button_add);
        TextView myTitle = (TextView)view.findViewById(R.id.free_side_title);
        final TextView sideNum = (TextView)view.findViewById(R.id.free_side_amount);
        final String choose = "Choose ";
        final String[] sides = {"Free Boiled Rice", "Free Fried Rice", "Free Chips", "Noodles - 2.50e extra"};
        final int[] star = {0,0,0,0};
        final int num = getArguments().getInt("NUM");
        String title;
        if (num == 1) {
            title = "Choose side for your meal";
        } else {
            title = "Choose " + num + " sides for your meal";
        }
        myTitle.setText(title);
        sideNum.setText(choose+num);
        final TextView sideOrdered = (TextView)view.findViewById(R.id.side_ordered);
        sideOrdered.setText("");
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.side_row_txt,R.id.free_side_txt_view, sides);
        adapter = new FreeSideAdapter(getContext(),R.layout.side_row_txt, Arrays.asList(sides),star,getArguments().getInt("AMOUNT"));

        listView.setAdapter(adapter);
        int[] colors = {0, 0xFFFF0000, 0}; // red for the example
        listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listView.setDividerHeight(1);
        listView.setHorizontalFadingEdgeEnabled(true);
        listView.setVerticalFadingEdgeEnabled(true);
        listView.setFadingEdgeLength(70);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                star[position]++;
                adapter.notifyDataSetChanged();

                if (count[0] == num) {
                    Snackbar.make(view,"You already choosed "+count[0]+" sides",Snackbar.LENGTH_SHORT).show();
           //         Toast.makeText(getContext(), "You already put " + count[0] + " sides", Toast.LENGTH_SHORT).show();
                }
                if (count[0] < num) {
                    sideOrdered.append(sides[position].toUpperCase()+"\n");
                    addSide.add(position);
                    count[0]++;
                    sideNum.setText(choose+(num-addSide.size()));
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnListener.clickAdd(addSide);
                getDialog().dismiss();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              addSide.clear();
                count[0]=0;
                sideOrdered.setText("");
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            btnListener = (SideOrderButtonListener) context;
        } catch (ClassCastException e) {

        }
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

    public interface SideOrderButtonListener {
        void clickAdd(ArrayList list);

    }
}
