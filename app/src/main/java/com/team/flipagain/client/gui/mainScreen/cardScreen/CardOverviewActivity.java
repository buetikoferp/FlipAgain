package com.team.flipagain.client.gui.mainScreen.cardScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.team.flipagain.R;
import com.team.flipagain.client.application.mobile.ListHandler;
import com.team.flipagain.client.gui.mainScreen.cardGetter.CardGetterActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ListHandler fieldOfStudy = new ListHandler(this);
        ListView FieldOfStudyListView = (ListView) findViewById(R.id.cardOverview_list_bundles);
        FieldOfStudyListView.setAdapter(fieldOfStudy.getFieldOfStudyAdapter(this));
    }
}
