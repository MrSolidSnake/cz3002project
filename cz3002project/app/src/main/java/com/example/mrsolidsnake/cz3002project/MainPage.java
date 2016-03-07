package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void goToTutorial(View view){
        Intent tutorial_button = new Intent(this , InstructionPage.class);
        startActivity(tutorial_button);
    }

    public void startGame(View view){
        Intent startGameIntent = new Intent(this , ModeSelectionScreen.class);
        startActivity(startGameIntent);
    }

    public void loadPhoto(View view){
        Intent loadPhotoActivityIntent = new Intent(this , upload_photo.class);
        startActivity(loadPhotoActivityIntent);
    }

}
