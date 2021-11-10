package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Vector;

public class ReserveActivity extends AppCompatActivity {
    Vector<Fragment> busReserveFragment;
    TabLayout tabs_reserve;
    Button confirmBtn;
    TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        tabs_reserve = findViewById(R.id.tabs_reserve);
        confirmBtn = (Button)findViewById(R.id.bus_confirm);
        tv_date = (TextView) findViewById(R.id.tv_date);

        Intent mIntent = getIntent();
        int index = mIntent.getIntExtra("tabIndex", 0);
        busReserveFragment = new Vector<>();

        initBusReserveMode(index);

        Button reserveBtn = findViewById(R.id.bus_reserve_ractivity);
        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "예약 하시겠습니까?", Toast.LENGTH_SHORT).show();
                if(tabs_reserve.getSelectedTabPosition() == 1)  {   //예약 확인 탭에서 예약하기 누름
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_reserve, busReserveFragment.get(0)).commit();
                    tabs_reserve.getTabAt(0).select();
                    changeBottomButtons(0);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("예약하시겠습니까?");

                    builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void initBusReserveMode(int index)    {

        tabs_reserve.addTab(tabs_reserve.newTab().setText("버스예약"));
        tabs_reserve.addTab(tabs_reserve.newTab().setText("예약확인"));
        tabs_reserve.selectTab(tabs_reserve.getTabAt(index));

        busReserveFragment.add(new BusReserveFragment());
        busReserveFragment.add(new BusReserveConfirmFragment());

        getSupportFragmentManager().beginTransaction().add(R.id.container_reserve, busReserveFragment.get(index)).commit();
        changeBottomButtons(index);

        tabs_reserve.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = busReserveFragment.get(position);
                tabs_reserve.selectTab(tabs_reserve.getTabAt(position));

                changeBottomButtons(position);

                getSupportFragmentManager().beginTransaction().replace(R.id.container_reserve, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    String dateMessage;

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        dateMessage = (month_string + "/" + day_string + "/" + year_string);
//        tv_date.setText(dateMessage);


        Toast.makeText(getBaseContext(),"Date: "+dateMessage,Toast.LENGTH_SHORT).show();
    }

    public void changeBottomButtons(int position)   {
        if(position == 1)  {
            confirmBtn.setText("돌아가기");
        }
        else    {
            confirmBtn.setText("취소");
        }
    }
}