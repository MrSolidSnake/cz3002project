package com.example.mrsolidsnake.cz3002project.ApplicatonModes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mrsolidsnake.cz3002project.Menus.ModeSelectionScreen;
import com.example.mrsolidsnake.cz3002project.R;

/**
 * Created by Pham Vu Tuan on 3/27/2016.
 */
public class AdvanceModeResult extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advance_mode_result_layout);
        resultTextView =(TextView)findViewById(R.id.advance_mode_result_txt_view);

        StringBuffer sb = new StringBuffer();
        sb.append(getString(R.string.correct_answer) + AdvanceMode.getNumberOfCorrect() + "\n");
        sb.append(getString(R.string.wrong_answer) + AdvanceMode.getNumberOfWrong() + "\n");
        sb.append(getString(R.string.final_score) + AdvanceMode.getNumberOfCorrect());
        resultTextView.setText(sb);
        AdvanceMode.resetScore();
    }

    public void goToModeSelectionFromAdvanceMode(View view) {
        Intent classicModeButton = new Intent(this, ModeSelectionScreen.class);
        startActivity(classicModeButton);
    }
}
