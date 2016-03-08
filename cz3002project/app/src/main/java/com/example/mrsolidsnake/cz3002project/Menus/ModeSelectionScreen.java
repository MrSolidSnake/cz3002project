package com.example.mrsolidsnake.cz3002project.Menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mrsolidsnake.cz3002project.ApplicatonModes.AdvanceMode;
import com.example.mrsolidsnake.cz3002project.ApplicatonModes.ClassicMode;
import com.example.mrsolidsnake.cz3002project.R;

public class ModeSelectionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode__selection__screen);
    }

    public void goToClassicMode(View view){
        Intent mode1_button = new Intent(this , ClassicMode.class);
        startActivity(mode1_button);
    }

    public void goToAdvanceMode(View view){
        Intent advance_mode_button = new Intent(this , AdvanceMode.class);
        startActivity(advance_mode_button);
    }

    public void goToMainMenu(View view){
        Intent mmButton = new Intent(this , MainPage.class);
        startActivity(mmButton);
    }


}
