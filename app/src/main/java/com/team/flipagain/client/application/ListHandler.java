package com.team.flipagain.client.application;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.team.flipagain.R;
import com.team.flipagain.client.domain.DBManager;
import com.team.flipagain.client.domain.DomainInterface;
import com.team.flipagain.client.domain.TBL_FOStudy;
import com.team.flipagain.client.domain.TBL_Module;

import java.util.List;

/**
 * Created by delay on 28.03.2016.
 */
public class ListHandler extends AppCompatActivity {

    private Context context;
    private DBManager manager;
    private DomainInterface domain;


    public ListHandler(Context context, DBManager manager) {
        this.context = context;
        this.manager = manager;
    }


    public ArrayAdapter getFieldOfStudyAdapter(){
        List<String> ListFieldOfStudy = manager.getNamesofSelectedTable(TBL_FOStudy.getTableName(), TBL_FOStudy.getRowNameOfStudy(), TBL_FOStudy.getRowStudyID(),null);

        ArrayAdapter<String> studyAdapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListFieldOfStudy);

        return studyAdapter;

    }
    public ArrayAdapter getModuleAdapter(String fieldOfStudyName){
        List<String> ModuleList = manager.getNamesofSelectedTable(TBL_Module.getTableName(), TBL_Module.getRowName(), TBL_Module.getRowModuleID(), fieldOfStudyName);

        ArrayAdapter<String> studyAdapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ModuleList);

        return studyAdapter;

    }
}
