package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.client.application.ListHandler;
import com.team.flipagain.client.domain.DBManager;

public class CardOverviewActivity extends AppCompatActivity {



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

        setFieldOfStudyAdapter();

    }


    public  void setFieldOfStudyAdapter(){
        Context list = findViewById(R.id.cardOverview_list_bundles).getContext();
        ListHandler listHandler = new ListHandler(list, new DBManager(list));

        ListView fieldOfStudyListView = (ListView) findViewById(R.id.cardOverview_list_bundles);
        fieldOfStudyListView.setAdapter(listHandler.getFieldOfStudyAdapter());

        fieldOfStudyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               String s = parent.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position+"," +s , Toast.LENGTH_LONG)
                        .show();

            }
        });


    }

    
}
