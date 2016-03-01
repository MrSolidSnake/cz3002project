package com.example.mrsolidsnake.cz3002project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class mode_1_screen extends AppCompatActivity {

    TextView mode1_tv;
    Button mode1_btn_next;
    RadioGroup mode1_rg;
    RadioButton mode1_rb1,mode1_rb2,mode1_rb3;
    String mode1_questions [] = {"Who is this person ?" , "Who is this person ?"};
    String mode1_ans [] = {"obama" , "donald"};
    String mode1_option [] = {"obama" , "person a" , "person b" , "person c" , "donald" , "person d"};


    int mode1_flag;
    public static int marks,correct,wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_1_screen);

        mode1_tv=(TextView)findViewById(R.id.mode1_textView);
        mode1_btn_next=(Button)findViewById(R.id.mode1_submitbutton);
        mode1_rg=(RadioGroup)findViewById(R.id.mode1_rg);
        mode1_rb1=(RadioButton)findViewById(R.id.mode1_radioButton);
        mode1_rb2=(RadioButton)findViewById(R.id.mode1_radioButton2);
        mode1_rb3=(RadioButton)findViewById(R.id.mode1_radioButton3);

        mode1_tv.setText(mode1_questions[mode1_flag]);
        mode1_rb1.setText(mode1_option[0]);
        mode1_rb2.setText(mode1_option[1]);
        mode1_rb3.setText(mode1_option[2]);

        mode1_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton userans = (RadioButton)findViewById(mode1_rg.getCheckedRadioButtonId());
                String ansText = userans.getText().toString();

                if(ansText.equalsIgnoreCase(mode1_ans[mode1_flag]))
                {
                    correct++;
                }
                else
                {
                    wrong++;
                }
                mode1_flag++;
                if(mode1_flag<mode1_questions.length)
                {
                    mode1_tv.setText(mode1_questions[mode1_flag]);
                    mode1_rb1.setText(mode1_option[mode1_flag*3]);
                    mode1_rb2.setText(mode1_option[(mode1_flag*3)+1]);
                    mode1_rb3.setText(mode1_option[(mode1_flag*3)+2]);
                }
                else
                {
                    Intent gotomode1_result = new Intent(getApplicationContext(),mode1_result.class);
                    startActivity(gotomode1_result);
                }


            }
        });

    }
}