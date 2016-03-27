package com.example.mrsolidsnake.cz3002project.Menus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.mrsolidsnake.cz3002project.R;

import java.io.File;

public class InstructionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_page);

        File imgFile = new File(Environment.getExternalStorageDirectory()+File.separator+"temp_picture");

        if(imgFile.exists())
        {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);

        }

    }



}
