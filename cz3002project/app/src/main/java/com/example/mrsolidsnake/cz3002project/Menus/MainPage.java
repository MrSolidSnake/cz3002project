package com.example.mrsolidsnake.cz3002project.Menus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mrsolidsnake.cz3002project.R;
import com.example.mrsolidsnake.cz3002project.ApplicatonModes.UploadPhoto;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void goToTutorial(View view){
        Intent tutorialButton = new Intent(this , InstructionPage.class);
        startActivity(tutorialButton);
    }

    public void startGame(View view){
        Intent startGameIntent = new Intent(this , ModeSelectionScreen.class);
        startActivity(startGameIntent);
    }

    public void loadPhoto(View view){
        Intent loadPhotoActivityIntent = new Intent(this , UploadPhoto.class);
        startActivity(loadPhotoActivityIntent);
    }

}
