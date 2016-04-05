package com.team.flipagain.client.domain;

import java.io.Serializable;



/**
 * Created by Raffaele on 23.03.2016.
 */
public class Card implements Serializable{
    // ATTRIBUT
    private int cardId;
    private String question;
    private String answer;
    private int rating;

    /**
     *
     * @param cardId
     * @param question
     * @param answer
     * @param rating
     */
    public Card(int cardId, String question, String answer, int rating){
        this.cardId = cardId;
        this.question = question;
        this.answer = answer;
        this.rating = rating;
    }

    //GETTER SETTER
    public String getAnswer() {
        return answer;
    }
    public String getQuestion() {
        return question;
    }
    public int getCardId() {
        return cardId;
    }
    public int getRating() {
        return rating;
    }
}
