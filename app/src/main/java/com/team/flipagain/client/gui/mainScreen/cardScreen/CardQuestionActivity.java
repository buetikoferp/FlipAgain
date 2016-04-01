package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team.flipagain.R;
import com.team.flipagain.client.application.ApplicationInterface;
import com.team.flipagain.client.application.CardHandler;

public class CardQuestionActivity extends AppCompatActivity implements CardScreenInterface  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_question);



        TextView textView = (TextView)findViewById(R.id.cardQuestion_txt_question);
        textView.setText(applicationInterface.getQuestion());
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


}
