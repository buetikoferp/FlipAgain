package com.team.flipagain.client.application;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.team.flipagain.server.domain.User;

/**
 * Created by Raffaele on 23.03.2016.
 */
public interface CardHandlerInterface {
    /**
     * Check if user and password are correct.
     * @param User
     * @return
     */
   public boolean getAuthorization(User u);



    //Methoden der Klasse CardHandler
    public void fillUpList(String nameOfBundle, Context context);
    public boolean bundleSelected();
    public String getQuestion();
    public String getAnswer();
    public boolean isLastCard();
    public boolean goBackToQuestion();
    public int getCardMax();
    public int getCardNr();



}
