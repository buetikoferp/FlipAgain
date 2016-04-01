package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.client.application.ApplicationInterface;
import com.team.flipagain.client.application.CardHandler;
import com.team.flipagain.client.application.ListHandler;
import com.team.flipagain.client.domain.Card;
import com.team.flipagain.client.domain.DBManager;

import java.io.Serializable;
import java.lang.ref.Reference;

public class CardOverviewActivity extends AppCompatActivity implements CardScreenInterface{
   // private ApplicationInterface applicationInterface = new CardHandler();
    private String TAG = "CARDOVERVIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_overview);

        final Button cardQuestion = (Button) findViewById(R.id.cardOverview_btn_start);

        cardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent question = new Intent(CardOverviewActivity.this, CardQuestionActivity.class);
                if (applicationInterface.bundleSelected()) {

                    startActivity(question);
                }else{
                    Toast.makeText(cardQuestion.getContext(), "Leider beinhaltet dieses Bundle noch keine Karten", Toast.LENGTH_LONG).show();
                }
            }

        });

        setListviewAdapter();

    }



    private   void setListviewAdapter(){
        final Context list = findViewById(R.id.cardOverview_list_bundles).getContext();
        final ListHandler listHandler = new ListHandler(list, new DBManager(list));


        final ListView cardOverviewListView = (ListView) findViewById(R.id.cardOverview_list_bundles);
        cardOverviewListView.setAdapter(listHandler.getFieldOfStudyAdapter());

        cardOverviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int count = 1;
            Button button = (Button) findViewById(R.id.cardOverview_btn_start);

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String name = parent.getAdapter().getItem(position).toString();

                switch (count) {

                    case 1:
                        cardOverviewListView.setAdapter(listHandler.getModuleAdapter(name));
                        count = 2;
                        Log.d(TAG, " CASE 1 MODULE " + count);
                        break;
                    case 2:
                        cardOverviewListView.setAdapter(listHandler.getBundleAdapter(name));
                        count = 3;
                        Log.d(TAG, " CASE 2 BUNDLE " + count);
                        break;
                    case 3:

                        button.setEnabled(true);
                        startCardHandler(name, list);
                        count = -1;
                        break;
                    default:
                        break;
                }


            }

        });

    }
    private void startCardHandler(String selectedItem, Context context){
        applicationInterface.fillUpList(selectedItem, context);


    }

}
