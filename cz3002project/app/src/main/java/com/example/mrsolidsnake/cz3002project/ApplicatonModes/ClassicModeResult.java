package com.example.mrsolidsnake.cz3002project.ApplicatonModes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mrsolidsnake.cz3002project.Menus.ModeSelectionScreen;
import com.example.mrsolidsnake.cz3002project.R;

public class ClassicModeResult extends AppCompatActivity {

    private TextView resultTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_mode_result_layout);

        resultTxtView =(TextView)findViewById(R.id.classic_mode_result_txt_view);

        StringBuffer sb = new StringBuffer();
        sb.append(getString(R.string.correct_answer) + ClassicMode.getNumberOfCorrect() + "\n");
        sb.append(getString(R.string.wrong_answer) + ClassicMode.getNumberOfWrong() + "\n");
        sb.append(getString(R.string.final_score) + ClassicMode.getNumberOfCorrect());
        resultTxtView.setText(sb);

        ClassicMode.resetScore();

    }

    public void goToModeSelection(View view){
        Intent classicModeButton = new Intent(this , ModeSelectionScreen.class);
        startActivity(classicModeButton);
    }


}
