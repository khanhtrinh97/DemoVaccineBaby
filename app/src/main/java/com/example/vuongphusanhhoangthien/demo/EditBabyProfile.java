package com.example.vuongphusanhhoangthien.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

public class EditBabyProfile extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private CircleImageView babyCimAva;
    private TextView tvChangeDay, tvOk, tvCancel;
    private EditText etBabyName;
    private Calendar cal;
    private Date date;
    private int mYear, mMonth, mDay;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_baby_profile);
        initView();


        babyCimAva.setOnClickListener(this);
        tvChangeDay.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    private void initView() {
        babyCimAva = findViewById(R.id.editbaby_cim_baby_ava);


        tvChangeDay = findViewById(R.id.editbaby_tv_baby_date_change);
        tvOk = findViewById(R.id.editbaby_tv_ok);
        tvCancel = findViewById(R.id.editbaby_tv_cancel);


        etBabyName = findViewById(R.id.editbaby_et_baby_name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editbaby_cim_baby_ava:
                chooseImage();
                break;
            case R.id.editbaby_tv_baby_date_change:
                getDate();
                break;
            case R.id.editbaby_tv_ok:
                saveBabyData();
                break;
            case  R.id.editbaby_tv_cancel:
                Intent i = new Intent();
                setResult(RESULT_CANCELED, i);
                finish();
                break;
            default:
                break;
        }
    }


    private void getDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tvChangeDay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data.getData() !=null){
            Uri uri = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                babyCimAva.setImageBitmap(bitmap);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void saveBabyData() {
        String strBitmap = BitMapToString(bitmap);
        String strName = etBabyName.getText().toString();
        String date = tvChangeDay.getText().toString();
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("baby_info" ,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bitmap_Img", strBitmap);
        editor.putString("baby_name", strName);
        editor.putString("baby_yob",date);
        editor.apply();
        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
    }


    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
