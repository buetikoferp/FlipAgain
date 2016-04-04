package com.team.flipagain.client.gui.mainScreen.cardCreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.client.application.CardHandler;
import com.team.flipagain.client.application.CardHandlerInterface;
import com.team.flipagain.client.gui.mainScreen.MainScreenActivity;

public class CardCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_cards);

        // Button Listener to Create Cards Activity
        Button createBundle = (Button) findViewById(R.id.creatorCards_btn_newBundle);
        createBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardCreatorActivity.this, BundleCreatorActivity.class);
                startActivity(intent);
            }
        });
        // Button Listener to save Card
        final EditText question = (EditText)findViewById(R.id.creatorCards_txtf_question);
        final EditText solution = (EditText)findViewById(R.id.creatorCards_txtf_solution);
        // Button Listener to select Bundle Activity
        Button selectBundle = (Button) findViewById(R.id.creatorCards_btn_selectBundle);
        final String nameOfBundle = (String)getIntent().getStringExtra("nameOfBundle");
        if(!nameOfBundle.equals("start")){
            selectBundle.setText("Bundle: \n " + nameOfBundle);
        }else{
            question.setEnabled(false);
            solution.setEnabled(false);
            question.setText("Wählen oder Erstellen Sie ein Bundle");
            solution.setVisibility(View.INVISIBLE);
        }

        selectBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardCreatorActivity.this, SelectBundleActivity.class);
                startActivity(intent);
            }
        });







        final Button save = (Button) findViewById(R.id.creatorCards_btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameOfBundle.equals("start")){
                    Toast.makeText(save.getContext(), "Bitte wählen Sie ein Bundle", Toast.LENGTH_LONG).show();
                }
                else if(question.getText().toString().isEmpty()){
                    Toast.makeText(save.getContext(), "Bitte geben Sie eine Frage ein", Toast.LENGTH_LONG).show();
                }
                else if(solution.getText().toString().isEmpty()){
                    Toast.makeText(save.getContext(), "Bitte geben Sie eine Lösung ein", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(save.getContext(), "Die Karte wurde erstellt und dem Bundle: \"" +nameOfBundle + "\" hinzugefügt" , Toast.LENGTH_LONG).show();
                    CardHandlerInterface cardHandlerInterface = new CardHandler();
                    cardHandlerInterface.addNewCard(nameOfBundle, question.getText().toString(), solution.getText().toString(), save.getContext());
                    question.setText("");
                    solution.setText("");
                    question.requestFocus();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CardCreatorActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }
}
