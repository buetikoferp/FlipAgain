package com.team.flipagain.client.application.mobile;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.team.flipagain.R;
import com.team.flipagain.client.domain.MRY_FieldOfStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by delay on 28.03.2016.
 */
public class ListHandler{
    private Context context;





    /**
     * Spaeter dann von der DATABASE
     */
    String [] ListArrayFieldOfStudy = {
            "Informatik",
            "Maschinenbau",
            "Elektrotechnik",
            "Raumplanung"
    };

    public ListHandler(Context context){
        this.context = context;
    }

    public ArrayAdapter getFieldOfStudyAdapter(Context context){
        List<String> ListFieldOfStudy = new ArrayList<>(Arrays.asList(ListArrayFieldOfStudy));

        ArrayAdapter<String> studyAdapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListFieldOfStudy);

        return studyAdapter;

    }
}
