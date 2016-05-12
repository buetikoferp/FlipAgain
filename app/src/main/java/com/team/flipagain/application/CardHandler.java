package com.team.flipagain.application;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.team.flipagain.domain.Card;
import com.team.flipagain.domain.DBManager;
import com.team.flipagain.domain.DomainInterface;


import java.util.ArrayList;

/**
 * Created by Anthony Delay on 01.04.2016.
 */
public class CardHandler extends AppCompatActivity implements CardHandlerInterface {
    private String TAG = "CARDHANDLER";
    private ArrayList<Object> cardList = new ArrayList<>();
    private int cardMax;
    private int cardNr = 0;

    public int getCardNr() {
        return cardNr;
    }

    @Override
    public void addNewCard(String bundle, String question, String solution, Context context) {
        DomainInterface domainInterface = new DBManager(context);
        domainInterface.insertCard(bundle,question,solution);
    }


    /**
     *
     * @param nameOfBundle
     * @param context
     */
    public void fillUpList(String nameOfBundle, Context context) {
        DomainInterface domainInterface = new DBManager(context);
        cardList = domainInterface.getClassListofSelectedTable("card", nameOfBundle);
        cardMax = cardList.size();
        cardNr = 0;
    }


    public boolean bundleSelected(){
        if(cardList.isEmpty()){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public String getQuestion() {
            Card card = (Card) cardList.get(cardNr);
            return card.getQuestion();
    }

    @Override
    public String getAnswer() {
        Card card = (Card) cardList.get(cardNr);
        return card.getAnswer();
    }

    @Override
    public boolean isLastCard() {
        if(cardNr < cardMax -1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void restartBundle(){
        cardNr = 0;
    }

    public boolean goToNextQuestion(){
        if(cardNr <= cardMax){
            cardNr++;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean goToPreviousQuestion() {
        if(cardNr > 0 ){
            cardNr--;
            return true;
        }else{
            return false;
        }
    }

    public int getCardMax() {
        return cardMax;
    }

}
