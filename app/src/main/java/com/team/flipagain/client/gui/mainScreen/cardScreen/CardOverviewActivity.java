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
import com.team.flipagain.client.application.ListHandler;
import com.team.flipagain.client.domain.DBManager;

public class CardOverviewActivity extends AppCompatActivity {

    private String TAG = "CARDOVERVIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_overview);



        // Button Listener to TBL_Card Question Activity
        Button cardQuestion = (Button) findViewById(R.id.cardOverview_btn_start);
        cardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardOverviewActivity.this, CardQuestionActivity.class);
                startActivity(intent);
            }
        });

        setListviewAdapter();

    }


    public  void setListviewAdapter(){
        Context list = findViewById(R.id.cardOverview_list_bundles).getContext();
        final ListHandler listHandler = new ListHandler(list, new DBManager(list));


        final ListView cardOverviewListView = (ListView) findViewById(R.id.cardOverview_list_bundles);
        cardOverviewListView.setAdapter(listHandler.getFieldOfStudyAdapter());

        cardOverviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int count = 1;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String name = parent.getAdapter().getItem(position).toString();

                if(count > 2){
                    count = 1;
                }
                switch (count){

                    case 1:
                        cardOverviewListView.setAdapter(listHandler.getModuleAdapter(name));
                        count++;
                        Log.d(TAG, " CASE 1 MODULE " + count);
                        break;
                    case 2:
                        cardOverviewListView.setAdapter(listHandler.getBundleAdapter(name));
                        count++;
                        Log.d(TAG, " CASE 2 BUNDLE " + count);
                        break;
                    default:
                        break;
                }


            }

        });






    }




}
