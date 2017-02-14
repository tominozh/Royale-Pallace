package com.example.tomasaoibh.royaletabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas & Aoibh on 02/05/2016.
 */
public class FreeSideAdapter extends ArrayAdapter{

    Context context;
    int layoutResID;
    List<String> objects;
    int amount;
    int [] star;
    DetailsHolder detailsHolder;

    public FreeSideAdapter(Context context, int resource, List objects,int[] star,int amount) {
        super(context, resource, objects);
        this.context=context;
        this.layoutResID=resource;
        this.objects=objects;
        this.amount=amount;
        this.star=star;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if(view==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResID, parent, false);
            detailsHolder = new DetailsHolder();
            detailsHolder.sideTxt = (TextView) view.findViewById(R.id.free_side_txt_view);
            detailsHolder.checkTxt = (TextView)view.findViewById(R.id.free_side_check);
            view.setTag(detailsHolder);
        }else{
            detailsHolder= (DetailsHolder) view.getTag();
        }
        detailsHolder.sideTxt.setText(objects.get(position));
        int num = star[position];
        String line="";
        if(num!=0){
            for(int i=0;i!=num;i++){
                line.concat("* ");
            }
        }
        detailsHolder.checkTxt.setText(line);
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private class DetailsHolder {
        private TextView sideTxt;
        private TextView checkTxt;
    }
}
