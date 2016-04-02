package com.team.flipagain.client.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.team.flipagain.R;
import com.team.flipagain.client.domain.Card;
import com.team.flipagain.client.domain.DBManager;
import com.team.flipagain.client.domain.DomainInterface;
import com.team.flipagain.client.gui.mainScreen.cardScreen.CardOverviewActivity;
import com.team.flipagain.client.gui.mainScreen.cardScreen.CardQuestionActivity;
import com.team.flipagain.server.domain.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Anthony Delay on 01.04.2016.
 */
public class CardHandler extends AppCompatActivity implements ApplicationInterface  {
    private String TAG = "CARDHANDLER";
    private ArrayList<Object> cardList = new ArrayList<>();


    private int cardMax;
    private int cardNr = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        Card card =(Card) cardList.get(cardNr);
        return card.getQuestion();
    }

    @Override
    public String getAnswer() {

        if(cardNr <= cardMax){
            Card card = (Card) cardList.get(cardNr);
            Log.d(TAG , "Antwort = " + card.getAnswer() + " cardMax = "  + cardMax + " cardNR = " + cardNr);
            cardNr++;
            return card.getAnswer();
        }else{

            /**
             *  Möglichkeit einer Statistik o.ä.
             */
            return "fehler";
        }
    }

    @Override
    public boolean isLastCard() {
        if(cardMax == cardNr){
            try{
                return true;
            }finally {
                cardNr = 0;
            }
        }else{
            return false;
        }
    }



    @Override
    public boolean goBackToQuestion() {
        if(cardNr > 0 ){
            cardNr--;
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean getAuthorization(User u) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CardHandler Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.team.flipagain.client.application/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CardHandler Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.team.flipagain.client.application/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
