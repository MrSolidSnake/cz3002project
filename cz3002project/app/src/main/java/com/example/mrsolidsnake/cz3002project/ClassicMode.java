package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClassicMode extends AppCompatActivity {

    TextView questionTextView;
    Button nextBtn;
    RadioGroup answersRadGrp;
    RadioButton answer1RadBtn, answer2RadBtn, answer3RadBtn;
    ImageView personImgView;


    int currentPerson = 0;
    public static int numberOfCorrect, numberOfWrong;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_mode_layout);
        questionTextView =(TextView)findViewById(R.id.classic_mode_question_txt_view);
        nextBtn =(Button)findViewById(R.id.classic_mode_next_btn);
        answersRadGrp =(RadioGroup)findViewById(R.id.classic_mode_ans_rad_grp);
        answer1RadBtn =(RadioButton)findViewById(R.id.classic_mode_ans_1_rad_btn);
        answer2RadBtn =(RadioButton)findViewById(R.id.classic_mode_ans_2_rad_btn);
        answer3RadBtn =(RadioButton)findViewById(R.id.classic_mode_ans_3_rad_btn);
        personImgView =(ImageView)findViewById(R.id.classic_mode_person_img_view);

        //fetch all persons from database
        MySQLiteHelper dbManager = MySQLiteHelper.getInstance(this);
        ArrayList<Person> persons = dbManager.getAllPerson();
        final ArrayList<Person> finalPersons = persons;

        questionTextView.setText(getString (R.string.question));
        answer1RadBtn.setText(persons.get(currentPerson).getAnswers()[0]);
        answer2RadBtn.setText(persons.get(currentPerson).getAnswers()[1]);
        answer3RadBtn.setText(persons.get(currentPerson).getAnswers()[2]);
        String uri = "@drawable/" + persons.get(currentPerson).getPicture();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        personImgView.setImageResource(imageResource);


        nextBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                RadioButton userAnsRadBtn = (RadioButton) findViewById(answersRadGrp.getCheckedRadioButtonId());
                String ansText = userAnsRadBtn.getText().toString();
                if(ansText.equalsIgnoreCase(finalPersons.get(currentPerson).getName())){
                    numberOfCorrect++;
                    Toast.makeText(getApplicationContext(), getString(R.string.correct_alert), Toast.LENGTH_SHORT).show();
                }else{
                    numberOfWrong++;
                    Toast.makeText(getApplicationContext(), getString(R.string.wrong_alert), Toast.LENGTH_SHORT).show();
                }
                if(currentPerson < finalPersons.size() - 1){
                    currentPerson++;
                    nextQuestion(finalPersons.get(currentPerson));
                }else{
                    personImgView.setImageResource(0);       // set imageview to use no resource
                    Intent gotomode1_result = new Intent(getApplicationContext(), ClassicModeResult.class);
                    startActivity(gotomode1_result);
                }

            }
        });
        // new method below


    }

    public void nextQuestion(Person person){
        //change answer
        int numberOfRadBtn = answersRadGrp.getChildCount();
        for(int i = 0; i < numberOfRadBtn; i++){
            View answer = answersRadGrp.getChildAt(i);
            if(answer instanceof RadioButton){
                ((RadioButton) answer).setText(person.getAnswers()[i]);
            }
        }

        //change image
        String uri = "@drawable/" + person.getPicture();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        personImgView.setImageResource(imageResource);
    }

    public static int getNumberOfCorrect(){
        return numberOfCorrect;
    }

    public static int getNumberOfWrong(){
        return numberOfWrong;
    }

    public static void resetScore(){
        numberOfCorrect = 0;
        numberOfWrong = 0;
    }

}
