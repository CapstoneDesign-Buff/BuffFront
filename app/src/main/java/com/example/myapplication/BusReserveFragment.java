package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class BusReserveFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_bus_reservation, container, false);

//        TextView fragment_tv = (TextView)rootView.findViewById(R.id.round_tv);
//
//        //get value from activity
//        BusInfoActivity activity = (BusInfoActivity) getActivity();
//        String myDataFromActivity = activity.getRoundInfo();
//
//        //set text
//        fragment_tv.setText(myDataFromActivity);

        Context c = container.getContext();
        //hour spinner
        Spinner hour_spinner = (Spinner) rootView.findViewById(R.id.hour_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(c,
                R.array.hour_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        hour_spinner.setAdapter(adapter);

        //minute spinner
        Spinner minute_spinner = (Spinner) rootView.findViewById(R.id.minute_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(c,
                R.array.minute_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        minute_spinner.setAdapter(adapter2);

        Button selectDateBtn = rootView.findViewById(R.id.departure_date);
        selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v);
            }
        });
        return rootView;
    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(),"datePicker");

    }



}