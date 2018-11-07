package com.example.anurag.iiitvApp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Thought> {

    // Calendar calendar;
    // SimpleDateFormat simpleDateFormat;

    private static final String LOG_TAG = CustomAdapter.class.getSimpleName();


    public CustomAdapter(Activity context, ArrayList<Thought> elements) {
        super(context, 0,elements);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_one,parent,false);
        }

        Thought current_element = getItem(position);

        TextView textView1 = (TextView) listItemView.findViewById(R.id.name_id);
        textView1.setText(current_element.getmName());

        TextView textView2 = (TextView) listItemView.findViewById(R.id.description_id);
        textView2.setText(current_element.getmDescription());

        TextView textView3 = (TextView) listItemView.findViewById(R.id.time_id);
        textView3.setText(current_element.getmTime());

        return listItemView;


    }
}


