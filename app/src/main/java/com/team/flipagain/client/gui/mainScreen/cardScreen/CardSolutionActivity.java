package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team.flipagain.R;

public class CardSolutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_solution);

        // Button Listener to Card Question Activity
        Button cardNextQuestion = (Button) findViewById(R.id.cardSolution_btn_nextQuestion);
        cardNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardSolutionActivity.this, CardQuestionActivity.class);
                startActivity(intent);
            }
        });

    }
}
