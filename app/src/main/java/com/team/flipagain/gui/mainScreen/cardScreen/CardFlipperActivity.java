package com.team.flipagain.gui.mainScreen.cardScreen;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.team.flipagain.R;
import com.team.flipagain.application.AnimationFactory;

import java.util.Calendar;

public class CardFlipperActivity extends AppCompatActivity implements CardScreenInterface {
    private String TAG = "FLIPPER";
    private ViewFlipper viewFlipper;
    private float lastX;
    private TextView question, solution, questionNext, solutionNext;
    private ProgressBar progressBar;
    private Button buttonSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flipper);
        /**
         * AlertDialog : lässt sich noch mit jeweiligem user verknüpfen das diese Meldung pro User nur einmal aufploppt.
         */
        new AlertDialog.Builder(this)
                .setTitle("Navigationshilfe")
                .setMessage("Lösung:\n\t\t- Klick auf die Karte\n\nNächste Karte:\n\t\t- Wisch die Karte in die\n\t\t\tentsprechende Richtung.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        question = (TextView)findViewById(R.id.TextView_question);
        solution = (TextView)findViewById(R.id.TextView_Solution);
        solutionNext = (TextView)findViewById(R.id.TextView_solutionNext);
        questionNext = (TextView)findViewById(R.id.TextView_questionNext);
        question.setText(CARD_HANDLER_INTERFACE.getQuestion());
        solution.setText(CARD_HANDLER_INTERFACE.getAnswer());
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            private static final int MAX_CLICK_DURATION = 100;
            private long startClickTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        lastX = event.getX();
                        startClickTime = Calendar.getInstance().getTimeInMillis();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;

                        if(clickDuration > MAX_CLICK_DURATION) {
                            onTouchEvent(event, viewFlipper);
                        }else{
                            AnimationFactory.flipTransition(viewFlipper, AnimationFactory.FlipDirection.LEFT_RIGHT);
                        }
                    }
                }
                return true;
            }
        });


    }

    // Using the following method, we will handle all screen swaps.
    public boolean onTouchEvent(MotionEvent touchevent, ViewFlipper viewFlipper) {
        View currentView = viewFlipper.getCurrentView();
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();
                Log.d(TAG, "Current X : " +  currentX+ " lastX : " + lastX );
                // Handling left to right screen swap.
                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    // if (viewFlipper.getDisplayedChild() == 0)
                    // break;

                    // Next screen comes in from left.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    // Display next screen.
                    switch(currentView.getId()){
                        case R.id.CardQuestion:{

                            if(CARD_HANDLER_INTERFACE.isLastCard()){
                                CARD_HANDLER_INTERFACE.restartBundle();
                                Intent intent = new Intent(CardFlipperActivity.this, CardOverviewActivity.class);
                                intent.putExtra("case", 1);
                                startActivity(intent);
                            }else{
                                if(CARD_HANDLER_INTERFACE.goToNextQuestion()){
                                    progressBar.setMax(CARD_HANDLER_INTERFACE.getCardMax() - 1);
                                    progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());

                                    solutionNext.setText(CARD_HANDLER_INTERFACE.getAnswer());
                                    questionNext.setText(CARD_HANDLER_INTERFACE.getQuestion());

                                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.CardChange)));
                                }
                            }


                            break;
                        }
                        case R.id.CardChange:{

                            if(CARD_HANDLER_INTERFACE.isLastCard()){
                                Intent intent = new Intent(CardFlipperActivity.this, CardOverviewActivity.class);
                                intent.putExtra("case", 1);
                                startActivity(intent);
                            } else {
                                if(CARD_HANDLER_INTERFACE.goToNextQuestion()){
                                    progressBar.setMax(CARD_HANDLER_INTERFACE.getCardMax() - 1);
                                    progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());

                                    solution.setText(CARD_HANDLER_INTERFACE.getAnswer());
                                    question.setText(CARD_HANDLER_INTERFACE.getQuestion());
                                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.CardQuestion)));
                                }

                            }

                            break;
                        }
                        case R.id.CardSolution:{

                            if(CARD_HANDLER_INTERFACE.isLastCard()){
                                Intent intent = new Intent(CardFlipperActivity.this, CardOverviewActivity.class);
                                intent.putExtra("case", 1);
                                startActivity(intent);
                            } else {
                                if(CARD_HANDLER_INTERFACE.goToNextQuestion()){
                                    progressBar.setMax(CARD_HANDLER_INTERFACE.getCardMax() - 1);
                                    progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());

                                    solution.setText(CARD_HANDLER_INTERFACE.getAnswer());
                                    question.setText(CARD_HANDLER_INTERFACE.getQuestion());
                                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.CardQuestion)));
                                }

                            }

                            break;
                        }
                        case R.id.CardnextSolution:{

                            if(CARD_HANDLER_INTERFACE.isLastCard()){
                                Intent intent = new Intent(CardFlipperActivity.this, CardOverviewActivity.class);
                                intent.putExtra("case", 1);
                                startActivity(intent);
                            } else {
                                if(CARD_HANDLER_INTERFACE.goToNextQuestion()){
                                    progressBar.setMax(CARD_HANDLER_INTERFACE.getCardMax() - 1);
                                    progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());

                                    solution.setText(CARD_HANDLER_INTERFACE.getAnswer());
                                    question.setText(CARD_HANDLER_INTERFACE.getQuestion());
                                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.CardQuestion)));
                                }

                            }

                            break;
                        }

                    }
                }

                // Handling right to left screen swap.
                if (lastX > currentX) {

                    // If there is a child (to the left), kust break.
                    //  if (viewFlipper.getDisplayedChild() == 1)
                    //  break;

                    // Next screen comes in from right.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    // Display previous screen.
                    switch(currentView.getId()){
                        case R.id.CardQuestion:{
                            if(CARD_HANDLER_INTERFACE.goToPreviousQuestion()){
                                progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());
                                questionNext.setText(CARD_HANDLER_INTERFACE.getQuestion());
                                solutionNext.setText(CARD_HANDLER_INTERFACE.getAnswer());
                                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.CardChange)));
                            }
                            break;
                        }
                        case R.id.CardChange:{
                            if(CARD_HANDLER_INTERFACE.goToPreviousQuestion()){
                                progressBar.setProgress(CARD_HANDLER_INTERFACE.getCardNr());
                                question.setText(CARD_HANDLER_INTERFACE.getQuestion());
                                solution.setText(CARD_HANDLER_INTERFACE.getAnswer());
                                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.CardQuestion)));
                            }
                            break;
                        }
                        case R.id.CardSolution:{
                            break;
                        }
                        default:{

                            break;
                        }

                    }
                }
                break;
        }
        return false;
    }
}
