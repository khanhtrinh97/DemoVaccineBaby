package com.example.vuongphusanhhoangthien.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE =69 ;
    private static final int REQUEST_CODE1 =6969 ;

    private LinearLayout lnProfile;
    private CircleImageView cimBabyAva;
    private TextView tvBabyName, tvBabyYob, tvAddtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadBabyProfile();
        lnProfile.setOnClickListener(this);
        tvAddtask.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            loadBabyProfile();
        }
    }

    private void loadBabyProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("baby_info",MODE_PRIVATE);

            String babyName = sharedPreferences.getString("baby_name","");
            String babyBirth = sharedPreferences.getString("baby_yob","");
            String strbitmap = sharedPreferences.getString("bitmap_Img","");
            Bitmap bitmap = StringToBitMap(strbitmap);
        if (babyName != "" && babyBirth == "" && strbitmap == ""){
            tvBabyName.setText(babyName);
            tvBabyYob.setText("Baby Year Of Birth");
            cimBabyAva.setImageDrawable(getResources().getDrawable(R.drawable.baby_ava));
        }else if(babyName != "" && babyBirth != "" && strbitmap == ""){
            tvBabyName.setText(babyName);
            tvBabyYob.setText(babyBirth);
            cimBabyAva.setImageDrawable(getResources().getDrawable(R.drawable.baby_ava));
        }
        else if(babyName != "" && babyBirth != "" && strbitmap != ""){
            tvBabyName.setText(babyName);
            tvBabyYob.setText(babyBirth);
            cimBabyAva.setImageBitmap(bitmap);
        }
        else{
            tvBabyName.setText("Baby Name");
            tvBabyYob.setText("Baby Year Of Birth");
            cimBabyAva.setImageDrawable(getResources().getDrawable(R.drawable.baby_ava));
        }
    }

    private void initView() {
        tvAddtask = findViewById(R.id.main_tv_add_task);
        lnProfile = findViewById(R.id.main_ln_baby_profile);
        cimBabyAva = findViewById(R.id.main_cim_ava);
        tvBabyName = findViewById(R.id.main_tv_baby_name);
        tvBabyYob = findViewById(R.id.main_tv_baby_yob);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_ln_baby_profile:
                setBabyProfile();
                break;
            case R.id.main_tv_add_task:
                addNewTask();
                break;
            default:
                break;
        }
    }

    private void addNewTask() {
        Intent i = new Intent();
        i.setClass(this,AddTask.class);
        startActivityForResult(i,REQUEST_CODE1);
    }

    private void setBabyProfile() {
        Intent i = new Intent();
        i.setClass(this,EditBabyProfile.class);
        startActivityForResult(i, REQUEST_CODE);
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

}
