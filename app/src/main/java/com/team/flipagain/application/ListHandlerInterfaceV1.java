package com.team.flipagain.application;

/**
 * Created by Anthony Delay on 28.04.2016.
 */
public interface ListHandlerInterfaceV1 {
    public void setNextListView(String selected);
    public boolean setPreviousListView();
    public void setFirstList();
    public void setState(int state);
    public void setCardHandler(CardHandlerInterface cardHandler);
}