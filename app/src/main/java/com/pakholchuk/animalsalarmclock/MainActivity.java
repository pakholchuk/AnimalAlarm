package com.pakholchuk.animalsalarmclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pakholchuk.animalsalarmclock.adapter.AlarmRecycleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab) FloatingActionButton floatingActionButton;
    @BindView(R.id.switch_am_pm) Switch switchAmPm;
    @BindView(R.id.tv_new_time) TextView tvNewTime;

    @BindView(R.id.iv_hour) ImageView ivHour;
    @BindView(R.id.iv_minute) ImageView ivMinute;

    @BindView(R.id.btn_mon) Button btnMon;
    @BindView(R.id.btn_tue) Button btnTue;
    @BindView(R.id.btn_wed) Button btnWed;
    @BindView(R.id.btn_thu) Button btnThu;
    @BindView(R.id.btn_fri) Button btnFri;
    @BindView(R.id.btn_sat) Button btnSat;
    @BindView(R.id.btn_sun) Button btnSun;

    @BindView(R.id.ib_time_1) ImageButton ibTime1;
    @BindView(R.id.ib_time_2) ImageButton ibTime2;
    @BindView(R.id.ib_time_3) ImageButton ibTime3;
    @BindView(R.id.ib_time_4) ImageButton ibTime4;
    @BindView(R.id.ib_time_5) ImageButton ibTime5;
    @BindView(R.id.ib_time_6) ImageButton ibTime6;
    @BindView(R.id.ib_time_7) ImageButton ibTime7;
    @BindView(R.id.ib_time_8) ImageButton ibTime8;
    @BindView(R.id.ib_time_9) ImageButton ibTime9;
    @BindView(R.id.ib_time_10) ImageButton ibTime10;
    @BindView(R.id.ib_time_11) ImageButton ibTime11;
    @BindView(R.id.ib_time_12) ImageButton ibTime12;



    ArrayList<AlarmClock> listAlarms = new ArrayList<>();
    ArrayList<View> daysList = new ArrayList<>();
    boolean amPm;
    int hour;
    int minute;
    final String LOG_TAG = "LOG_TAG";

    RecyclerView recyclerView;
    AlarmRecycleAdapter alarmAdapter;

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmAdapter = new AlarmRecycleAdapter();
        recyclerView.setAdapter(alarmAdapter);

    }

    View.OnClickListener fabMainListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            newAlarm();
            floatingActionButton.hide();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showCurrentTime();
        initRecyclerView();

        floatingActionButton.setOnClickListener(fabMainListener);
        Log.d(LOG_TAG, "fabMainListener added");

    }

    class HourOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ib_time_1:{
                    setMinutes(1);
                    break;
                }
                case R.id.ib_time_2:{
                    setMinutes(2);
                    break;
                }
                case R.id.ib_time_3:{
                    setMinutes(3);
                    break;
                }
                case R.id.ib_time_4:{
                    setMinutes(4);
                    break;
                }
                case R.id.ib_time_5:{
                    setMinutes(5);
                    break;
                }
                case R.id.ib_time_6:{
                    setMinutes(6);
                    break;
                }
                case R.id.ib_time_7:{
                    setMinutes(7);
                    break;
                }
                case R.id.ib_time_8:{
                    setMinutes(8);
                    break;
                }
                case R.id.ib_time_9:{
                    setMinutes(9);
                    break;
                }
                case R.id.ib_time_10:{
                    setMinutes(10);
                    break;
                }
                case R.id.ib_time_11:{
                    setMinutes(11);
                    break;
                }
                case R.id.ib_time_12:{
                    setMinutes(12);
                    break;
                }
                default:break;
            }

        }
    }

    class DayOnTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            v.performClick();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (!v.isPressed()) {
                    Log.d(LOG_TAG, "setPressed " + v.getId());
                    v.setPressed(true);
                }
                else {
                    v.setPressed(false);
                    Log.d(LOG_TAG, "setUnpressed " + v.getId());
                }
            }
            return true;
        }
    }

    private void setMinutes(int hour) {
        switchAmPm.setVisibility(View.INVISIBLE);
        amPm = switchAmPm.isChecked();
        this.hour=hour;
        ivMinute.setVisibility(View.VISIBLE);
        ivMinute.setRotation(0);
        ivHour.setRotation(hour*30);
        String am = "AM";
        if (amPm){
            am = "PM";
        }
        String time = String.format(Locale.getDefault(), "%02d", hour)
                + ":00"
                + am;
        Log.d(LOG_TAG, time + " , setMinutes");
        tvNewTime.setText(time);
        setButtonsOnClick(new MinuteOnClickListener());
    }

    class MinuteOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ib_time_1:{
                    setDays(5);
                    break;
                }
                case R.id.ib_time_2:{
                    setDays(10);
                    break;
                }
                case R.id.ib_time_3:{
                    setDays(15);
                    break;
                }
                case R.id.ib_time_4:{
                    setDays(20);
                    break;
                }
                case R.id.ib_time_5:{
                    setDays(25);
                    break;
                }
                case R.id.ib_time_6:{
                    setDays(30);
                    break;
                }
                case R.id.ib_time_7:{
                    setDays(35);
                    break;
                }
                case R.id.ib_time_8:{
                    setDays(40);
                    break;
                }
                case R.id.ib_time_9:{
                    setDays(45);
                    break;
                }
                case R.id.ib_time_10:{
                    setDays(50);
                    break;
                }
                case R.id.ib_time_11:{
                    setDays(55);
                    break;
                }
                case R.id.ib_time_12:{
                    setDays(0);
                    break;
                }
                default:break;
            }

        }
    }

    private void setDays(int minute) {
        Log.d(LOG_TAG, "setDays()");
        this.minute = minute;
        ivMinute.setRotation(this.minute *6);
        ivHour.setRotation((hour*30)+(this.minute *0.5f));
        String am = "AM";
        if (amPm){
            am = "PM";
        }
        String time = String.format(Locale.getDefault(), "%02d", hour)
                + ":"
                + String.format(Locale.getDefault(), "%02d", this.minute)
                + am;
        tvNewTime.setText(time);
        setDaysOnClick(new DayOnTouchListener());
        floatingActionButton.show();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmClock alarmClock = new AlarmClock();
                alarmClock.setHour(hour);
                alarmClock.setMinute(MainActivity.this.minute);
                alarmClock.setPm(amPm);
                boolean[] days = new boolean[7];
                for (int i = 0; i < 7; i++){
                    days[i] = daysList.get(i).isPressed();
                }
                alarmClock.setDays(days);
                listAlarms.add(alarmClock);
                floatingActionButton.setOnClickListener(fabMainListener);
                resetDayButtons();
                showCurrentTime();
                tvNewTime.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "New Alarm added!", Toast.LENGTH_SHORT);
                toast.show();
                alarmAdapter.clearAlarms(); //TODO поменять логику
                alarmAdapter.setAlarms(listAlarms);


            }
        });
    }

    private void resetDayButtons() {
        for (View v : daysList){
            v.setPressed(false);
        }
    }

    private void showCurrentTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        ivHour.setRotation((hour*30)+(minute/2));
        ivMinute.setRotation(minute*6);
    }

    private void setDaysOnClick(View.OnTouchListener listener){
        Log.d(LOG_TAG, "setDaysOnClick");
        daysList.clear();
        daysList.add(btnMon);
        daysList.add(btnTue);
        daysList.add(btnWed);
        daysList.add(btnThu);
        daysList.add(btnFri);
        daysList.add(btnSat);
        daysList.add(btnSun);
        for (View v : daysList){
            v.setOnTouchListener(listener);

        }
    }

    private void setButtonsOnClick(View.OnClickListener ibListener){
        Log.d(LOG_TAG, "setButtonsOnClick" + ibListener.getClass().toString());
        ArrayList<View> viewList = new ArrayList<>();
        viewList.add(ibTime1);
        viewList.add(ibTime2);
        viewList.add(ibTime3);
        viewList.add(ibTime4);
        viewList.add(ibTime5);
        viewList.add(ibTime6);
        viewList.add(ibTime7);
        viewList.add(ibTime8);
        viewList.add(ibTime9);
        viewList.add(ibTime10);
        viewList.add(ibTime11);
        viewList.add(ibTime12);
        for (View v: viewList) {
            v.setOnClickListener(ibListener);

        }

    }

    private void newAlarm() {
        Log.d(LOG_TAG, "newAlarmStarted");
        String defaultTime = "00:00";
        tvNewTime.setVisibility(View.VISIBLE);
        switchAmPm.setVisibility(View.VISIBLE);
        tvNewTime.setText(defaultTime);
        ivMinute.setVisibility(View.INVISIBLE);

        setButtonsOnClick(new HourOnClickListener());
    }

}
