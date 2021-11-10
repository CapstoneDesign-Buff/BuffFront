package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFragment extends Fragment {

    private ListView listView;
    private TimeAxisAdapter mTimeAxisAdapter;
    private List<HashMap<String, Object>> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        TextView fragment_tv = (TextView)rootView.findViewById(R.id.round_tv);

        //get value from activity
        BusInfoActivity activity = (BusInfoActivity) getActivity();
        String myDataFromActivity = activity.getRoundInfo();

        //set text
        fragment_tv.setText(myDataFromActivity);

        initView(rootView);



        return rootView;
    }


    private void initView(View rootView) {
                 listView = (ListView) rootView.findViewById(R.id.timelineview);
                 listView.setDividerHeight(0);
                 mTimeAxisAdapter = new TimeAxisAdapter(rootView.getContext(), getList());
                 listView.setAdapter(mTimeAxisAdapter);
    }

    public List<HashMap<String, Object>> getList() {
        List<HashMap<String, Object>> listChild = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("content", "금정역");
        map.put("time", "9:05");
        listChild.add(map);

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("content", "천지사입구");
        map1.put("time", "9:15");
        listChild.add(map1);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("content", "당동우체국");
        map2.put("time", "9:25");
        listChild.add(map2);

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("content", "삼성마을5단지");
        map3.put("time", "9:30");
        listChild.add(map3);

        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("content", "서해아파트건너편");
        map4.put("time", "9:45");
        listChild.add(map4);

        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("content", "팔곡주유소건너편");
        map5.put("time", "9:55");
        listChild.add(map5);

        return listChild;
    }
}