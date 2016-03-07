package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    public void go_to_mode_2(View view){
        Intent mode2_button = new Intent(this , mode_2_screen.class);
        startActivity(mode2_button);
    }

    public void goToMainMenu(View view){
        Intent mm_button = new Intent(this , MainPage.class);
        startActivity(mm_button);
    }


}
