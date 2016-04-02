package com.team.flipagain.client.application;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.team.flipagain.R;
import com.team.flipagain.client.domain.Bundle;
import com.team.flipagain.client.domain.DBManager;
import com.team.flipagain.client.domain.DomainInterface;
import com.team.flipagain.client.domain.FieldOfStudy;
import com.team.flipagain.client.domain.Module;
import com.team.flipagain.client.domain.TBL_Bundle;
import com.team.flipagain.client.domain.TBL_FOStudy;
import com.team.flipagain.client.domain.TBL_Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by delay on 28.03.2016.
 */
public class ListHandler extends AppCompatActivity implements ListHandlerInterface {
    private String TAG = "LISTHANDLER";
    private Context context;

    private DomainInterface domain;


    public ListHandler(Context context) {
        this.context = context;
        domain = new DBManager(context);
    }


    public ArrayAdapter getFieldOfStudyAdapter(){
        List<String> ListFieldOfStudy = getNamesOfClass(domain.getClassListofSelectedTable(TBL_FOStudy.getTableName(), null), "FieldOfStudy");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListFieldOfStudy);

        return adapter;

    }
    public ArrayAdapter getModuleAdapter(String fieldOfStudyName){
        List<String> ModuleList = getNamesOfClass(domain.getClassListofSelectedTable(TBL_Module.getTableName(), fieldOfStudyName), "Module");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ModuleList);

        return adapter;

    }

    public ArrayAdapter getBundleAdapter(String moduleName){
        List<String> bundleList = getNamesOfClass(domain.getClassListofSelectedTable(TBL_Bundle.getTableName(), moduleName), "Bundle");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                bundleList);

        return adapter;

    }

    @Override
    public void createNewBundle(String name, String modulName) {
        domain.insertBundle(name,modulName );
    }

    private ArrayList<String> getNamesOfClass(ArrayList<Object> object, String instance){
        ArrayList<String> names = new ArrayList<>();
        if(object != null){
            switch(instance){
                case "FieldOfStudy":
                    for(Object objects : object ){
                        FieldOfStudy study = (FieldOfStudy)objects;
                        names.add(study.getName());
                    }
                    break;
                case "Module":
                    for(Object objects : object ){
                        Module module = (Module)objects;
                        names.add(module.getModuleName());
                    }
                    break;

                case "Bundle":
                    for(Object objects : object ){
                        Bundle bundle = (Bundle)objects;
                        names.add(bundle.getName());
                    }
                    break;
                default:
                    Log.d(TAG, instance);

            }
        }else{
            return null;
        }




        return names;
    }


}
