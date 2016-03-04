package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void gototutorial(View view){
        Intent tutorial_button = new Intent(this , Instruction_Page.class);
        startActivity(tutorial_button);
    }

    public void mainmenustartgame(View view){
        Intent mainmenustartgame_button = new Intent(this , Mode_Selection_Screen.class);
        startActivity(mainmenustartgame_button);
    }

    public void mainmenuuploadphoto(View view){
        Intent mainmenuuploadphoto_button = new Intent(this , upload_photo.class);
        startActivity(mainmenuuploadphoto_button);
    }

}
