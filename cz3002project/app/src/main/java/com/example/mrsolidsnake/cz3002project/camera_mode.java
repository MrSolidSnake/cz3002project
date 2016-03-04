package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class camera_mode extends AppCompatActivity {

    ImageView imgView;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;

    static String strSDCardPathName = Environment.getExternalStorageDirectory() + "/temp_picture" + "/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_mode);

        //*** Create Folder
        createFolder();

        //*** ImageView
        imgView = (ImageView) findViewById(R.id.cameramode_imgView);

        //*** Take Photo
        final Button btnTakePhoto = (Button) findViewById(R.id.takephoto_btn);
        // Perform action on click
        btnTakePhoto.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Toast.makeText(getApplicationContext(), "Opening Camera", Toast.LENGTH_SHORT).show();
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {}
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }

            }

        });


    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(strSDCardPathName);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    int i=0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            //*** Rename File
            //String strNewName = "MyPicture.jpg";
            String strNewName = "mode2_" + i + ".jpg";
            String NewPath = strSDCardPathName + strNewName;

            //*** Resize Images
            try {
                ResizeImages(mCurrentPhotoPath,NewPath);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeFile(NewPath);

            imgView.setImageBitmap(bitmap);
        }
        i++;
    }

    public static void ResizeImages(String sPath,String sTo) throws IOException {

        Bitmap photo = BitmapFactory.decodeFile(sPath);
        photo = Bitmap.createScaledBitmap(photo, 600 , 600, false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File f = new File(sTo);
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();

        File file =  new File(sPath);
        file.delete();

    }

    public static void createFolder()
    {
        File folder = new File(strSDCardPathName);
        try
        {
            // Create folder
            if (!folder.exists()) {
                folder.mkdir();
            }
        }catch(Exception ex){}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void go_to_main_menu(View view){
        Intent mm_button = new Intent(this , MainPage.class);
        startActivity(mm_button);
    }
}
