package com.team.flipagain.client.application;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


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

    private String fos;
    private String module;

    private ListView listView;

    private Context context;
    private DomainInterface dbManager;
    private Activity activity;
    private Button button;
    private CardHandlerInterface cardHandler;
    private TextView textView;

    public ListHandlerV1(Context context, ListView listView, Activity activity, Button button, TextView textView){
        this.activity = activity;
        this.context = context;
        this.listView = listView;
        fieldofStudyAdapter = getFieldOfStudyAdapter();
        this.button = button;
        this.textView = textView;
    }

    public void setCardHandler(CardHandlerInterface cardHandler){
        this.cardHandler = cardHandler;
    }

    public void setState(int state) {
        this.state = state;
    }



    public void setFirstList(){
        Log.d(TAG, "state of FirstList: " + state);
        textView.setText("Wähle einen Studiengang:");
        listView.setAdapter(fieldofStudyAdapter);
    }

    public void setNextListView(String selected){
        if(state < 4){
            state++;
        }
        Log.d(TAG, "state: " + state);
        switch (state){
            case 1:
                textView.setText("Wähle einen Studiengang:");
                listView.setAdapter(fieldofStudyAdapter);
                break;
            case 2:
                fos = selected;
                textView.setText(selected + "\\");
                moduleAdapter = getModuleAdapter(selected);
                listView.setAdapter(moduleAdapter);
                break;
            case 3:
                module = selected;
                textView.setText(fos +"\\" + selected);
                moduleNameForDownload = selected;
                // SERVER LISTE --> Bundles
                if(activity.getLocalClassName().equals("client.gui.mainScreen.cardGetter.CardGetterActivity")){
                    bundleAdapter = getBundleAdapterOfServer(moduleNameForDownload);
                    if(bundleAdapter.isEmpty()){
                        listView.setEnabled(false);
                        bundleAdapter.add("...Serververbindung fehlgeschlagen...");
                        listView.setAdapter(bundleAdapter);
                        button.setEnabled(true);
                        button.setText("Neu laden");
                    }else{
                        listView.setAdapter(bundleAdapter);
                    }
                // LOKALE LISTE --> Bundles
                }else{
                    bundleAdapter = getBundleAdapter(selected);
                    listView.setAdapter(bundleAdapter);
                }
                break;
            case 4:
                textView.setText(fos +"\\" + module + "\\" + selected);
                bundleNameForDownload = selected;
                // SERVER LISTE
                if(activity.getLocalClassName().equals("client.gui.mainScreen.cardGetter.CardGetterActivity")) {
                    // Bei Selektiertem Bundle enable Download and send request getBundle
                    button.setEnabled(true);
                }
                // LOKALE LISTE
                if(activity.getLocalClassName().equals("client.gui.mainScreen.cardScreen.CardOverviewActivity")){
                    cardHandler.fillUpList(selected, context);
                    button.setEnabled(true);
                }
                break;
        }
    }
    public boolean setPreviousListView(){
        listView.setEnabled(true);
        switch (state){
            case 1:
                return false;
            case 2:
                listView.setAdapter(fieldofStudyAdapter);
                textView.setText("Wähle einen Studiengang:");
                state--;
                return true;
            case 3:
                listView.setAdapter(moduleAdapter);
                textView.setText(fos + "\\");
                state--;
                return true;
            case 4:
                state = 3;
                return true;
            default:
                return false;
        }
    }

    /* -------------------------------LOKAL----------------------------------------------------------------------------*/

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

   /* -------------------------------SERVER----------------------------------------------------------------------------*/

    private ArrayAdapter getBundleAdapterOfServer(String moduleNameForDownload){
        dbManager = new DBManager(context);
        List<String> ListOfBundleName = new ArrayList<>();
        ArrayList<Bundle> ListOfBundle = dbManager.getServerListofBundle(moduleNameForDownload);

        if(ListOfBundle != null){
            for(Bundle bundle : ListOfBundle ){
                ListOfBundleName.add(bundle.getName());
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_card_overview,
                R.id.list_item_card_overview_textview,
                ListOfBundleName);

        return adapter;
    }
}
