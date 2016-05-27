package com.team.flipagain.application;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.team.flipagain.R;
import com.team.flipagain.domain.Bundle;
import com.team.flipagain.domain.DBManager;
import com.team.flipagain.domain.DomainInterface;
import com.team.flipagain.domain.FieldOfStudy;
import com.team.flipagain.domain.Module;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Anthony Delay on 28.04.2016.
 */
public class ListHandler  implements ListHandlerInterface {
    private String TAG = "listhandler";

    private ArrayAdapter fieldofStudyAdapter;
    private ArrayAdapter moduleAdapter;
    private ArrayAdapter bundleAdapter;



    private int state = 1;

    private String moduleNameForDownload;

    private String fos;

    public String getModule() {
        return module;
    }

    public String getBundle() {
        return bundle;
    }

    private String module;
    private String bundle;

    private ListView listView;

    private Context context;
    private DomainInterface dbManager;
    private Activity activity;
    private Button button;
    private CardHandlerInterface cardHandler;
    private TextView textView;

    public ListHandler(Context context, ListView listView, Activity activity, Button button, TextView textView){
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
        button.setVisibility(View.INVISIBLE);
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
                button.setVisibility(View.INVISIBLE);
                break;
            case 2:
                fos = selected;
                button.setVisibility(View.INVISIBLE);
                textView.setText(selected + "\\");
                moduleAdapter = getModuleAdapter(selected);
                listView.setAdapter(moduleAdapter);
                break;

            case 3:
                module = selected;
                textView.setText(fos + "\\" + selected);
                moduleNameForDownload = selected;
                button.setVisibility(View.VISIBLE);

                /* --------------------------------------------- SERVER LISTE --> Bundles --------------------------------------------*/
                if(activity.getLocalClassName().equals("gui.mainScreen.cardGetter.CardGetterActivity")){
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
                }


                    /* ----------------------------------------- LOKALE LISTE --> Bundles ----------------------------------*/

                if(activity.getLocalClassName().equals("gui.mainScreen.cardCreator.SelectBundleActivity")){
                    bundleAdapter = getBundlesOfUser(selected);
                    listView.setAdapter(bundleAdapter);
                }


                if(activity.getLocalClassName().equals("gui.mainScreen.cardScreen.CardOverviewActivity")  || activity.getLocalClassName().equals("gui.mainScreen.cardCreator.BundleCreatorActivity")){
                    bundleAdapter = getBundleAdapter(selected);
                    listView.setAdapter(bundleAdapter);
                }
                if(activity.getLocalClassName().equals("gui.mainScreen.cardCreator.BundleCreatorActivity")){
                    listView.setEnabled(false);
                    button.setEnabled(true);
                }
                break;


            case 4:
                textView.setText(fos +"\\" + module + "\\" + selected);
                bundle = selected;
                // SERVER LISTE
                if(activity.getLocalClassName().equals("gui.mainScreen.cardGetter.CardGetterActivity")) {
                    button.setEnabled(true);
                    ApplicationManager aM = new ApplicationManager();
                    aM.saveBundle(context,module,selected );
                }
                // LOKALE LISTE
                if(activity.getLocalClassName().equals("gui.mainScreen.cardScreen.CardOverviewActivity")){
                    cardHandler.fillUpList(selected, context);
                    button.setEnabled(true);
                }
                if(activity.getLocalClassName().equals("gui.mainScreen.cardCreator.SelectBundleActivity")){
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
                button.setVisibility(View.INVISIBLE);
                listView.setAdapter(fieldofStudyAdapter);
                textView.setText("Wähle einen Studiengang:");
                state--;
                return true;
            case 3:
                button.setVisibility(View.INVISIBLE);
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
        Set<Module> ListOfModule = dbManager.getListOfModule(fieldOfStudyName);

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

    private ArrayAdapter getBundlesOfUser(String moduleName){
        dbManager = new DBManager(context);
        List<String> ListOfBundleName = new ArrayList<>();
        ArrayList<Bundle> ListOfBundle = dbManager.getListOfBundleFromUser(moduleName);

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

   /* -------------------------------SERVER----------------------------------------------------------------------------*/
    ArrayList<Bundle> ListOfBundle;

    private ArrayAdapter getBundleAdapterOfServer(String moduleNameForDownload){
        dbManager = new DBManager(context);
        List<String> ListOfBundleName = new ArrayList<>();
        BundleList bundleList = new BundleList();
        bundleList.execute(moduleNameForDownload);


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
    public class BundleList extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... params) {
            ListOfBundle = dbManager.getServerListofBundle(moduleNameForDownload);
            if(ListOfBundle == null ){
                Log.d("ListHandler", "ListofBundle ist null");
            }
            return null;
        }
    }




}
