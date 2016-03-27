package com.example.mrsolidsnake.cz3002project.ApplicatonModes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrsolidsnake.cz3002project.Helper.MySQLiteHelper;
import com.example.mrsolidsnake.cz3002project.Model.Person;
import com.example.mrsolidsnake.cz3002project.R;

import java.util.ArrayList;

public class AdvanceMode extends AppCompatActivity {
    private TextView questionTextView;
    private Button nextBtn;
    private EditText answer;
    private ImageView personImgView;

    private int currentPerson = 0;
    public static int numberOfCorrect, numberOfWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advance_mode_layout);
        questionTextView = (TextView) findViewById(R.id.advance_mode_question_txt_view);
        nextBtn = (Button) findViewById(R.id.advance_mode_next_btn);
        answer = (EditText) findViewById(R.id.advance_mode_answer);
        personImgView = (ImageView) findViewById(R.id.advance_mode_person_img_view);

        MySQLiteHelper dbManager = MySQLiteHelper.getInstance(this);
        ArrayList<Person> persons = dbManager.getAllPerson();
        final ArrayList<Person> finalPersons = persons;

        //load first person
        questionTextView.setText(getString(R.string.question));
        loadPersonImage(persons.get(currentPerson).getPicture());

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ansText = answer.getText().toString().toLowerCase();
                if (ansText.equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.empty_alert), Toast.LENGTH_SHORT).show();
                } else {
                    if (finalPersons.get(currentPerson).getName().toLowerCase().contains(ansText)) {
                        numberOfCorrect++;
                        Toast.makeText(getApplicationContext(), getString(R.string.correct_alert), Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfWrong++;
                        Toast.makeText(getApplicationContext(), getString(R.string.wrong_alert), Toast.LENGTH_SHORT).show();
                    }
                    //check if all questions have been answered, if yes then move to result mode
                    if (currentPerson < finalPersons.size() - 1) {
                        currentPerson++;
                        nextQuestion(finalPersons.get(currentPerson));
                    } else {
                        personImgView.setImageResource(0);       // set imageview to use no resource
                        Intent goToAdvanceModeResult = new Intent(getApplicationContext(), AdvanceModeResult.class);
                        startActivity(goToAdvanceModeResult);
                    }
                }
            }
        });
    }

    public void nextQuestion(Person person) {
        // reset answer
        answer.setText("");
        // change image
        loadPersonImage(person.getPicture());
    }

    public void loadPersonImage(String image) {
        if (!image.contains("://")) {
            //this image is in drawable folder
            String uri = "@drawable/" + image;
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            personImgView.setImageResource(imageResource);
        } else {
            //this image is uploaded by users
            personImgView.setImageURI(Uri.parse(image));
        }
    }

    public static int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public static int getNumberOfWrong() {
        return numberOfWrong;
    }

    public static void resetScore() {
        numberOfCorrect = 0;
        numberOfWrong = 0;
    }
}
