package com.team.flipagain.client.application;


import android.content.Context;
import android.widget.ArrayAdapter;

import com.team.flipagain.R;
import com.team.flipagain.client.domain.DBManager;
import com.team.flipagain.client.domain.FieldOfStudy;
import com.team.flipagain.client.domain.TBL_FOStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by delay on 28.03.2016.
 */
public class ListHandler {

    private Context context;
    private DBManager manager;


    // private ArrayList<FieldOfStudy> fieldOfStudies = manager.getSomeShitFromDatabase(TBL_FOStudy.TABLE_NAME, TBL_FOStudy.rowNameOfStudy, TBL_FOStudy.rowStudyID);
    /**
     * Spaeter dann von der DATABASE
     */
    String [] ListArrayFieldOfStudy = {
            "Informatik",
            "Maschinenbau",
            "Elektrotechnik",
            "Raumplanung"
    };



    public ListHandler(Context context, DBManager manager) {
        this.context = context;
        this.manager = manager;
    }


    public ArrayAdapter getFieldOfStudyAdapter(){
        List<String> ListFieldOfStudy = new ArrayList<>(Arrays.asList(ListArrayFieldOfStudy));

        ArrayAdapter<String> studyAdapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListFieldOfStudy);

        return studyAdapter;

    }
}
