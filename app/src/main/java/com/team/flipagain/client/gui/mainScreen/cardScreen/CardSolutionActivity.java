package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.client.application.ApplicationInterface;
import com.team.flipagain.client.application.CardHandler;

public class CardSolutionActivity extends AppCompatActivity implements CardScreenInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_solution);




        handleAnswer();


    }

    private void handleAnswer(){
        TextView textView = (TextView)findViewById(R.id.cardSolution_txtV_solution);
        textView.setText(applicationInterface.getAnswer());
        if(applicationInterface.isLastCard()){
            Button changeToFinish = (Button) findViewById(R.id.cardSolution_btn_nextQuestion);
            changeToFinish.setText("Karten abschliessen");

            changeToFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CardSolutionActivity.this, CardOverviewActivity.class);
                    startActivity(intent);
                }
            });
        }else{


            // Button Listener to TBL_Card Question Activity
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
    @Override
    public void onBackPressed() {
        if(applicationInterface.goBackToQuestion()){
            Intent intent = new Intent(CardSolutionActivity.this, CardQuestionActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(CardSolutionActivity.this, CardOverviewActivity.class);
            intent.putExtra("case", 1);
            startActivity(intent);
        }



    }
}
