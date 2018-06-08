package com.example.vuongphusanhhoangthien.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddTask extends AppCompatActivity implements View.OnClickListener {
    private EditText etTaskName, etNum;
    private RadioButton rdoSeconds, rdoMinutes, rdoHours, rdoDays, rdoMonths, rdoYears;
    private TextView tvOk, tvCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initView();
        radioRuleCheck();
        tvOk.setOnClickListener(this);
        tvCancel.setOnClickListener(this);


        rdoSeconds.setOnClickListener(this);
        rdoMinutes.setOnClickListener(this);
        rdoHours.setOnClickListener(this);
        rdoDays.setOnClickListener(this);
        rdoMonths.setOnClickListener(this);
        rdoYears.setOnClickListener(this);

    }

    private void initView() {
        etTaskName = findViewById(R.id.add_task_et_task_name);
        etNum = findViewById(R.id.add_task_et_num);

        rdoSeconds = findViewById(R.id.rdo_seconds);
        rdoMinutes = findViewById(R.id.rdo_minutes);
        rdoHours = findViewById(R.id.rdo_hours);
        rdoDays = findViewById(R.id.rdo_days);
        rdoMonths = findViewById(R.id.rdo_months);
        rdoYears = findViewById(R.id.rdo_years);

        tvOk = findViewById(R.id.add_task_tv_ok);
        tvCancel = findViewById(R.id.add_task_tv_cancel);
    }
    private void radioRuleCheck(){
        if (rdoSeconds.isChecked()){
            rdoMinutes.setChecked(false);
            rdoHours.setChecked(false);
            rdoDays.setChecked(false);
            rdoMonths.setChecked(false);
            rdoYears.setChecked(false);
        }else if (rdoMinutes.isChecked()){
            rdoSeconds.setChecked(false);
            rdoHours.setChecked(false);
            rdoDays.setChecked(false);
            rdoMonths.setChecked(false);
            rdoYears.setChecked(false);
        }else if (rdoHours.isChecked()){
            rdoSeconds.setChecked(false);
            rdoMinutes.setChecked(false);
            rdoDays.setChecked(false);
            rdoMonths.setChecked(false);
            rdoYears.setChecked(false);
        }else if (rdoDays.isChecked()){
            rdoSeconds.setChecked(false);
            rdoMinutes.setChecked(false);
            rdoHours.setChecked(false);
            rdoMonths.setChecked(false);
            rdoYears.setChecked(false);
        }
        else if (rdoMonths.isChecked()){
            rdoSeconds.setChecked(false);
            rdoMinutes.setChecked(false);
            rdoHours.setChecked(false);
            rdoDays.setChecked(false);
            rdoYears.setChecked(false);
        }else if (rdoYears.isChecked()){
            rdoSeconds.setChecked(false);
            rdoMinutes.setChecked(false);
            rdoHours.setChecked(false);
            rdoDays.setChecked(false);
            rdoMonths.setChecked(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_task_tv_ok:
                startAlarm();
                doneAndBackToMain();
                break;
            case R.id.add_task_tv_cancel:
                backToMain();
                break;
            case R.id.rdo_seconds:
                radioRuleCheck();
                break;
            case R.id.rdo_minutes:
                radioRuleCheck();
                break;
            case R.id.rdo_hours:
                radioRuleCheck();
                break;
            case R.id.rdo_days:
                radioRuleCheck();
                break;
            case R.id.rdo_months:
                radioRuleCheck();
                break;
            case R.id.rdo_years:
                radioRuleCheck();
                break;
            default:
                break;
        }
    }

    private void doneAndBackToMain() {
    }

    private void backToMain() {
    }

    private int timeType(){
        int timeSet = 1000;
        if (rdoSeconds.isChecked()){
            timeSet = 1000;
        }else if (rdoMinutes.isChecked()){
            timeSet = 1000 * 60;
        }else if (rdoHours.isChecked()){
            timeSet = 1000 * 60 * 60;
        }else if (rdoDays.isChecked()){
            timeSet = 1000 * 60 * 60 * 24;
        }else if (rdoMonths.isChecked()){
            timeSet = 1000 * 60 * 60 * 24 * 30;
        }else if (rdoYears.isChecked()){
            timeSet = 1000 * 60 * 60 * 24 * 30 * 12;
        }
        return timeSet;
    }

    private void startAlarm() {
        int time = timeType();
        int num  = Integer.parseInt(etNum.getText().toString());
        Intent i = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,696969, i, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * num), pendingIntent);

    }
}
