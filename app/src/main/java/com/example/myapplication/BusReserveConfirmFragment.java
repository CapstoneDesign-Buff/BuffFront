package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

public class BusReserveConfirmFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_bus_reserve_confirm, container, false);

        TextView fragment_tv = (TextView)rootView.findViewById(R.id.round_tv);

        //get value from activity
        ReserveActivity activity = (ReserveActivity) getActivity();

        ArrayList<ItemModel> itemsList = new ArrayList<>();
        ListView list = (ListView) rootView.findViewById(R.id.listview);
        itemsList = sortAndAddSections(getItems());
        Context c = container.getContext();

        ListAdapter adapter = new ListAdapter(c, itemsList);
        list.setAdapter(adapter);

        return rootView;
    }

    private ArrayList sortAndAddSections(ArrayList<ItemModel> itemList)
    {

        ArrayList<ItemModel> tempList = new ArrayList<>();
        //First we sort the array
        Collections.sort(itemList);

        //Loops thorugh the list and add a section before each sectioncell start
        String header = "";
        for(int i = 0; i < itemList.size(); i++)
        {
            //If it is the start of a new section we create a new listcell and add it to our array
            if(!(header.equals(itemList.get(i).getDate()))) {
                ItemModel sectionCell = new ItemModel(null, null,null,itemList.get(i).getDate());
                sectionCell.setToSectionHeader();
                tempList.add(sectionCell);
                header = itemList.get(i).getDate();
            }
            tempList.add(itemList.get(i));
        }

        return tempList;
    }


    public class ListAdapter extends ArrayAdapter {

        LayoutInflater inflater;
        public ListAdapter(Context context, ArrayList items) {
            super(context, 0, items);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ItemModel cell = (ItemModel) getItem(position);

            //If the cell is a section header we inflate the header layout
            if(cell.isSectionHeader())
            {
                v = inflater.inflate(R.layout.section_header, null);

                v.setClickable(false);

                TextView header = (TextView) v.findViewById(R.id.section_header);
                header.setText(cell.getDate());
            }
            else
            {
                v = inflater.inflate(R.layout.row_item, null);
                TextView time_time = (TextView) v.findViewById(R.id.time_time);
                TextView tv_item_sysdia = (TextView) v.findViewById(R.id.tv_item_sysdia);

                TextView tv_item_plus = (TextView) v.findViewById(R.id.tv_item_plus);

                time_time.setText(cell.getDeptPos());
                tv_item_sysdia.setText(cell.getDeptTime());
                tv_item_plus.setText(cell.getArrivalPos());


            }
            return v;
        }
    }


    private ArrayList<ItemModel>  getItems(){

        ArrayList<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel("금정역", "군포시평생학습원","9:20","31 Oct 17"));
        items.add(new ItemModel("삼성마을5단지", "팔곡마을주공아파트","13:20","31 Oct 17"));
        items.add(new ItemModel("군포중학교", "비봉중고등학교","15:40","Tue,31 Oct 17"));
        items.add(new ItemModel("군포G샘병원", "도금단지","10:15","29 Oct 17"));
        items.add(new ItemModel("남양읍행정복지센터", "마도교차로","12:50","29 Oct 17"));

        return  items;
    }

}