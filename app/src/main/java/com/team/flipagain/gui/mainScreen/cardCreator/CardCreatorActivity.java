package com.team.flipagain.gui.mainScreen.cardCreator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.application.ApplicationManager;
import com.team.flipagain.application.CardHandler;
import com.team.flipagain.application.CardHandlerInterface;
import com.team.flipagain.gui.mainScreen.MainScreenActivity;

import java.util.ArrayList;

public class CardCreatorActivity extends AppCompatActivity {
    private Button newComplete, saveSelect;
    private EditText question , solution;
    private TextView selectedTitle;
    private LinearLayout title;
    private ArrayList<String> questionList;
    private ArrayList<String> solutionList;
    private boolean isNewBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_cards);



        saveSelect = (Button)findViewById(R.id.creatorCards_btn_save);
        newComplete = (Button)findViewById(R.id.creatorCards_btn_finish);
        question = (EditText)findViewById(R.id.creatorCards_txtf_question);
        solution = (EditText)findViewById(R.id.creatorCards_txtf_solution);
        selectedTitle = (TextView)findViewById(R.id.creatorCards_textV_title);
        title = (LinearLayout)findViewById(R.id.LinearLayout_title);
        isNewBundle = getIntent().getBooleanExtra("isNewBundle", false);

        // Button Listener
        newComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardCreatorActivity.this, BundleCreatorActivity.class);
                startActivity(intent);
            }
        });
        // Button Listener
        saveSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardCreatorActivity.this, SelectBundleActivity.class);
                startActivity(intent);
            }
        });




        final String nameOfBundle = (String)getIntent().getStringExtra("nameOfBundle");
        final String nameofModule = (String)getIntent().getStringExtra("nameOfModule");
        /**
         * STATUS -- BUNDLE Gewaehlt und bereit zur Speicherung von Karten
         */
        if(nameOfBundle != null && !nameOfBundle.equals("start")){
            selectedTitle.setText(nameOfBundle);
            saveSelect.setText("Speichern");
            newComplete.setText("Fertigstellen");
            questionList = new ArrayList<>();
            solutionList = new ArrayList<>();

            saveSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (question.getText().toString().isEmpty()) {
                        Toast.makeText(saveSelect.getContext(), "Bitte geben Sie eine Frage ein", Toast.LENGTH_LONG).show();
                    } else if (solution.getText().toString().isEmpty()) {
                        Toast.makeText(saveSelect.getContext(), "Bitte geben Sie eine Lösung ein", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(saveSelect.getContext(), "Die Karte wurde erstellt und dem Bundle: \"" + nameOfBundle + "\" hinzugefügt", Toast.LENGTH_LONG).show();
                        questionList.add(question.getText().toString());
                        solutionList.add(solution.getText().toString());

                        question.setText("");
                        solution.setText("");
                        question.requestFocus();
                    }

                }
            });

            newComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(questionList == null || questionList.size() <= 2){
                        Toast.makeText(newComplete.getContext(), "Bitte erstellen Sie mindestens 3 Karten", Toast.LENGTH_LONG).show();
                    }else{
                        new AlertDialog.Builder(newComplete.getContext())
                                .setTitle("Überblick")
                                .setMessage("Modul:\t\t\t " + nameofModule + "\nBundle:\t\t\t" + nameOfBundle + "\nAnzahl:\t\t\t" + solutionList.size())
                                .setPositiveButton("Bestätigen", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ApplicationManager appManager = new ApplicationManager();
                                        if(isNewBundle){
                                            appManager.createNewBundle(nameOfBundle, nameofModule, newComplete.getContext());
                                        }


                                        for(int i = 0; i < questionList.size() ; i++){
                                            appManager.addNewCard(nameOfBundle, questionList.get(i), solutionList.get(i), newComplete.getContext());

                                        }
                                        Intent intent = new Intent(CardCreatorActivity.this, MainScreenActivity.class);
                                        startActivity(intent);

                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .show();
                    }
                }
            });

        }else{
            title.setVisibility(View.INVISIBLE);
            newComplete.setText("Bundle erstellen");
            saveSelect.setText("Bundle auswählen");
            question.setEnabled(false);
            solution.setEnabled(false);
            question.setText("Wählen oder Erstellen Sie ein Bundle");
            solution.setVisibility(View.INVISIBLE);
        }






    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CardCreatorActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }
}
