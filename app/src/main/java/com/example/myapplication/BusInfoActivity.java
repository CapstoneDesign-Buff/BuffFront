package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Vector;

public class BusInfoActivity extends FragmentActivity {
    TabLayout tabs;
    String roundInfo = "1회차";
    Vector<MainFragment> vecFragments;

    int page = 0;
    final int MAX_ROUND = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);
        vecFragments = new Vector<>();

        tabs = findViewById(R.id.tabs);

        initBusRouteInfo();



//        // instantiate the TimelineFragment
//        TimelineFragment mFragment = new TimelineFragment();
//
//        //Set data
//        mFragment.setData(loadDataInTimeline(), TimelineGroupType.MONTH);
//
//        //Set configurations
////        mFragment.addOnClickListener(this);
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, mFragment);
//        transaction.commit();


        Button reserveBtn = findViewById(R.id.bus_reserve_ractivity);
        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "[예약하기 페이지]준비중", Toast.LENGTH_SHORT).show();
                openBusReserveActivity(0);
            }
        });

        Button checkBtn = findViewById(R.id.bus_confirm);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBusReserveActivity(1);
//                Toast.makeText(getApplicationContext(), "[예약 확인 페이지]준비중", Toast.LENGTH_SHORT).show();
            }
        });
    }

//
//    private ArrayList<TimelineObject> loadDataInTimeline() {
//        //Load the data in a list and sort it by times in milli
//        ArrayList<TimelineObject> objs = new ArrayList<>();
//        objs.add(new BusStopModel("abcd", "11:30"));
//        objs.add(new BusStopModel("abcd", "11:30"));
//        objs.add(new BusStopModel("abcd", "11:30"));
//        objs.add(new BusStopModel("abcd", "11:30"));
////        objs.add(new Test0(Long.parseLong("1483196400000"), "A", "url"));
////        objs.add(new Test0(Long.parseLong("1483196400000"), "A", "url"));
////        objs.add(new Test0(Long.parseLong("1483196400000"), "B", "url" ));
////        objs.add(new Test0(Long.parseLong("1483196400000"), "C" , "url"));
////        objs.add(new Test0(Long.parseLong("1484146800000"), "D" ,"url"));
//
//
//        //Sort and return
//        //Sort logic
//        return objs;
//    }

    public void initBusRouteInfo()  {
        tabs.addTab(tabs.newTab().setText("◀"));

        for(int i = 0 ; i < MAX_ROUND; i++) {
            vecFragments.add(new MainFragment());
            tabs.addTab(tabs.newTab().setText( (i+1) + "회차"));
        }

        tabs.addTab(tabs.newTab().setText("▶"));
        tabs.selectTab(tabs.getTabAt(1));
        getSupportFragmentManager().beginTransaction().add(R.id.container, vecFragments.get(0)).commit();

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                switch (position) {
                    case 0:
                        if(page != 0)   {
                            page--;
                        }
                        tabs.selectTab(tabs.getTabAt(1));
                        roundInfo = (page * 5 + 1) + "회차";
                        selected = vecFragments.get(page * 5 + 1);
                        break;

                    case 1:case 2:case 3:case 4: case 5:

//                        decode = Integer.decode("00FFFF");
//                        colorDrawable = new ColorDrawable(decode);
//                        fragment5.setBgColor(colorDrawable);
                        roundInfo = (page * 5 + position) + "회차";
//                        selected = fragment5;
                        selected = vecFragments.get(page * 5 + (position-1));
                        break;
                    case 6:
                        if((page+1) * 5 < MAX_ROUND) {
                            page++;
                        }
                        tabs.selectTab(tabs.getTabAt(1));
                        roundInfo = (page * 5 + 1) + "회차";
//                        selected = fragment1;
                        selected = vecFragments.get(page * 5 + 1);
                        break;

                    default:
//                        decode = Integer.decode("777777");
//                        colorDrawable = new ColorDrawable(decode);
//                        fragment1.setBgColor(colorDrawable);
                        roundInfo = "unknown";
//                        selected = fragment1;
                        selected = vecFragments.get(page * 5 + 1);
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    public String getRoundInfo() {
        return roundInfo;
    }


    public void openBusReserveActivity(int index){
        Intent intent = new Intent(this, ReserveActivity.class);
        intent.putExtra("tabIndex", index);

        startActivity(intent);
    }


}
