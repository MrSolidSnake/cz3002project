package com.example.mrsolidsnake.cz3002project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class mode1_result extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode1_result);

        tv=(TextView)findViewById(R.id.textView2);

        StringBuffer sb = new StringBuffer();
        sb.append("Correct Ans : " + mode_1_screen.correct + "\n");
        sb.append("Wrong Ans : " + mode_1_screen.wrong + "\n");
        sb.append("Final Score : " + mode_1_screen.correct);
        tv.setText(sb);

        mode_1_screen.correct=0;
        mode_1_screen.wrong=0;



    }


}
