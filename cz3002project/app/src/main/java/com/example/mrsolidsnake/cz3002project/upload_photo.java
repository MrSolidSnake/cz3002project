package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class upload_photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
    }

    public void cameramode(View view){
        Intent cameramode_button = new Intent(this , camera_mode.class);
        startActivity(cameramode_button);
    }

    public void loadfromdirectorymode(View view){
        Intent loadfromdirectorymode_button = new Intent(this , load_fromdirmode.class);
        startActivity(loadfromdirectorymode_button);
    }



}
