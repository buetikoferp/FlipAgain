package com.team.flipagain.client.application;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Anthony Delay on 28.04.2016.
 */
public interface ListHandlerInterfaceV1 {
    public void setNextListView(String selected);
    public boolean setPreviousListView();
    public void setFirstList();
}
