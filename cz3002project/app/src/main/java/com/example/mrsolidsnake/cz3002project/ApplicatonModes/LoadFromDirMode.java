package com.example.mrsolidsnake.cz3002project.ApplicatonModes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrsolidsnake.cz3002project.Helper.MySQLiteHelper;
import com.example.mrsolidsnake.cz3002project.Model.Person;
import com.example.mrsolidsnake.cz3002project.R;

public class LoadFromDirMode extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;
    private static final int NUMBER_OF_ANSWER = 3;
    private Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_fromdirmode);

        /**
         * These TODO is NOT neccessary for demo purpose only, ie do if have free time
         * TODO: This is hard code for 3 possible answer, if have time can change the limit
         * TODO: Have not checked for empty image selected
         * TODO: Change the order of the answers randomly
         * TODO: Replace depricated method
         */
        final Button chooseImageBtn = (Button) findViewById(R.id.chooseImageBtn);
        final EditText correctAnsTxtView = (EditText) findViewById(R.id.correctAnsEditText);
        final EditText altAns1TxtView = (EditText) findViewById(R.id.altAns1EditText);
        final EditText altAns2TxtView = (EditText) findViewById(R.id.altAns2EditText);
        final Button submitBtn = (Button) findViewById(R.id.submitBtn);

        //open default gallery of Android to choose image
        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String correctAns = correctAnsTxtView.getText().toString();
                String altAns1 = altAns1TxtView.getText().toString();
                String altAns2 = altAns2TxtView.getText().toString();

                //combine all possible answer to 1 array to add to database
                String[] answers = new String[NUMBER_OF_ANSWER];
                answers[0] = correctAns;
                answers[1] = altAns1;
                answers[2] = altAns2;
                boolean isEmpty = false;
                for(int i = 0; i < answers.length; i++){
                    if(answers[i].isEmpty()){
                        isEmpty = true;
                    }
                }
                if(isEmpty == true){
                    Toast.makeText(getApplicationContext(), R.string.empty_answer_alert, Toast.LENGTH_LONG).show();
                }else{
                    //add new person to database
                    Person uploadPerson = new Person(correctAns, selectedImageUri.toString(), answers);
                    MySQLiteHelper dbManager = MySQLiteHelper.getInstance(getBaseContext());
                    dbManager.addPerson(uploadPerson);
                    Toast.makeText(getApplicationContext(), R.string.added_success_alert, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                getContentResolver().takePersistableUriPermission(selectedImageUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                final ImageView imageDisplayImgView = (ImageView) findViewById(R.id.uploadImageView);
                imageDisplayImgView.setImageURI(selectedImageUri);
            }
        }
    }




}
