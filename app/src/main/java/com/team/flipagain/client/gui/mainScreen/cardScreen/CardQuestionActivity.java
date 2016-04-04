package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.team.flipagain.R;

public class  CardQuestionActivity extends AppCompatActivity implements CardScreenInterface  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_question);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.cardQuestion_progressBar);
        progressBar.setMax(CARD_HANDLER_INTERFACE.getCardMax() -1);
        progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());


        TextView textView = (TextView)findViewById(R.id.cardQuestion_txt_question);
        textView.setText(CARD_HANDLER_INTERFACE.getQuestion());

        // Button Listener to TBL_Card Solution Activity
        Button getSolution = (Button) findViewById(R.id.cardQuestion_btn_getSolution);
        getSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CardQuestionActivity.this, CardSolutionActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(CARD_HANDLER_INTERFACE.goBackToQuestion()){
        Intent intent = new Intent(CardQuestionActivity.this, CardQuestionActivity.class);
        startActivity(intent);}else{
            Intent intent = new Intent(CardQuestionActivity.this, CardOverviewActivity.class);
            intent.putExtra("case", 1);
            startActivity(intent);
        }

    }
}
