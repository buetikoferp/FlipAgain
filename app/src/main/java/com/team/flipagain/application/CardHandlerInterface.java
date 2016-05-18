package com.team.flipagain.application;

import android.content.Context;



/**
 * Created by Raffaele on 23.03.2016.
 */
public interface CardHandlerInterface {




    //Methoden der Klasse CardHandler
    public void restartBundle();
    /**
     * Füllt die Karten des gewählten Bundles in eine Liste.
     * @param nameOfBundle
     * @param context
     */
    public void fillUpList(String nameOfBundle, Context context);

    /**
     * True wenn das bundle ausgewählt wurde.
     * @return boolean
     */
    public boolean bundleSelected();

    /**
     * gibt die Frage der jeweiligen Karte zurück
     * @return
     */
    public String getQuestion();

    /**
     * gibt die Antwort der jeweiligen Karte zurück
     * @return
     */
    public String getAnswer();

    /**
     * True falls die Karte die letzte vom Bundle ist.
     * @return
     */
    public boolean isLastCard();

    /**
     * lässt den den zähler im Kartenhändler 1 zurückspringen
     * @return
     */
    public boolean goToPreviousQuestion();

    /**
     * gibt die Anzahl Karten des Bundles zurück
     * @return
     */
    public int getCardMax();

    /**
     * gibt während dem lernprozess die Position der Karte zurück
     * @return
     */
    public int getCardNr();

    public boolean goToNextQuestion();



}
