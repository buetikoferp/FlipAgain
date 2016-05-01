package com.team.flipagain.client.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.team.flipagain.R;
import com.team.flipagain.client.domain.Bundle;
import com.team.flipagain.client.domain.DBManager;
import com.team.flipagain.client.domain.DomainInterface;
import com.team.flipagain.client.domain.FieldOfStudy;
import com.team.flipagain.client.domain.Module;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Anthony Delay on 28.04.2016.
 */
public class ListHandlerV1 implements  ListHandlerInterfaceV1{
    private String TAG = "listhandler";

    private ArrayAdapter fieldofStudyAdapter;
    private ArrayAdapter moduleAdapter;
    private ArrayAdapter bundleAdapter;
    private int state = 1;

    private String moduleNameForDownload;
    private String bundleNameForDownload;

    private ListView listView;

    private Context context;
    private DomainInterface dbManager;
    private Activity activity;
    private Button downloadButton;

    public ListHandlerV1(Context context, ListView listView, Activity activity, Button downloadButton){
        this.activity = activity;
        this.context = context;
        this.listView = listView;
        fieldofStudyAdapter = getFieldOfStudyAdapter();
        this.downloadButton = downloadButton;
    }

    public void setFirstList(){
        Log.d(TAG, "state of FirstList: " + state);
        listView.setAdapter(fieldofStudyAdapter);
    }

    public void setNextListView(String selected){
        if(state < 4){
            state++;
        }
        Log.d(TAG, "state: " + state);
        switch (state){
            case 1:
                listView.setAdapter(fieldofStudyAdapter);
                break;
            case 2:
                moduleAdapter = getModuleAdapter(selected);
                listView.setAdapter(moduleAdapter);
                break;
            case 3:
                moduleNameForDownload = selected;
                if(activity.getLocalClassName().equals("client.gui.mainScreen.cardGetter.CardGetterActivity")){
                    bundleAdapter = getBundleAdapter(selected);
                    listView.setAdapter(bundleAdapter);
                }else{
                    bundleAdapter = getBundleAdapter(selected);
                    listView.setAdapter(bundleAdapter);
                }
                break;
            case 4:
                bundleNameForDownload = selected;
                downloadButton.setEnabled(true);

                Log.d(TAG, "activity" + activity.getLocalClassName());
                break;
        }
    }
    public boolean setPreviousListView(){
        switch (state){
            case 1:
                return false;
            case 2:
                listView.setAdapter(fieldofStudyAdapter);
                state--;
                return true;
            case 3:
                listView.setAdapter(moduleAdapter);
                state--;
                return true;
            case 4:
                state = 2;
                return true;
            default:
                return false;
        }
    }

    //LOKAL
    private ArrayAdapter getFieldOfStudyAdapter(){
        dbManager = new DBManager(context);
        List<String> ListOfFOSname = new ArrayList<>();
        ArrayList<FieldOfStudy> fosList = dbManager.getListOfStudy();

        for(FieldOfStudy fos : fosList ){
            ListOfFOSname.add(fos.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListOfFOSname);

        return adapter;

    }
    private ArrayAdapter getModuleAdapter(String fieldOfStudyName){
        dbManager = new DBManager(context);
        List<String> ListOfModuleName = new ArrayList<>();
        Set<Module> ListOfModule = dbManager.getListOfModlue(fieldOfStudyName);

        for(Module module : ListOfModule ){
            ListOfModuleName.add(module.getModuleName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListOfModuleName);

        return adapter;

    }

    private ArrayAdapter getBundleAdapter(String moduleName){
        dbManager = new DBManager(context);
        List<String> ListOfBundleName = new ArrayList<>();
        ArrayList<Bundle> ListOfBundle = dbManager.getListOfBundle(moduleName);

        for(Bundle bundle : ListOfBundle ){
            ListOfBundleName.add(bundle.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListOfBundleName);

        return adapter;

    }
    //Server
    private ArrayAdapter getBundleAdapterOfServer(String moduleNameForDownload){
        dbManager = new DBManager(context);
        List<String> ListOfBundleName = new ArrayList<>();
        ArrayList<Bundle> ListOfBundle = dbManager.getServerListofBundle(moduleNameForDownload);

        for(Bundle bundle : ListOfBundle ){
            ListOfBundleName.add(bundle.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListOfBundleName);

        return adapter;
    }
}
