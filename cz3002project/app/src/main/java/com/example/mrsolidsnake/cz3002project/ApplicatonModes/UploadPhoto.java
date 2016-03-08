package com.example.mrsolidsnake.cz3002project.ApplicatonModes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mrsolidsnake.cz3002project.R;

public class UploadPhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
    }

    public void cameraMode(View view){
        Intent cameraModeButton = new Intent(this , CameraMode.class);
        startActivity(cameraModeButton);
    }

    public void loadFromDirectoryMode(View view){
        Intent loadButton = new Intent(this , LoadFromDirMode.class);
        startActivity(loadButton);
    }



}
