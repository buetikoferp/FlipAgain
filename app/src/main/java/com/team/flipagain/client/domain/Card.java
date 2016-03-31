package com.team.flipagain.client.domain;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class Card {
    // ATTRIBUT
    private int cardId;
    private String question;
    private String answer;

    //REFERENCE
    private Bundle bundle;

    //GETTER SETTER
    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
