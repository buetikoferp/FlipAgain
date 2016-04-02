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
import com.team.flipagain.client.gui.mainScreen.MainScreenActivity;

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

        int numberOfCase = getIntent().getIntExtra("case", 1);
        Log.d(TAG,"numberOfCase = " + numberOfCase);
        setListviewAdapter(numberOfCase, null);

    }
    

    private   void setListviewAdapter(final int numberOfCase, String name){
        final Context list = findViewById(R.id.cardOverview_list_bundles).getContext();
        final ListHandler listHandler = new ListHandler(list, new DBManager(list));
        final ListView cardOverviewListView = (ListView) findViewById(R.id.cardOverview_list_bundles);

        int count = numberOfCase;

        switch (count) {
            case 1:
                cardOverviewListView.setAdapter(listHandler.getFieldOfStudyAdapter());

                cardOverviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        setListviewAdapter(2, name);
                    }
                });
                break;
            case 2:
                cardOverviewListView.setAdapter(listHandler.getModuleAdapter(name));
                cardOverviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        setListviewAdapter(3, name);
                    }
                });
                break;
            case 3:
                cardOverviewListView.setAdapter(listHandler.getBundleAdapter(name));
                cardOverviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        Button button = (Button) findViewById(R.id.cardOverview_btn_start);
                        button.setEnabled(true);
                        startCardHandler(name, list);
                    }
                });
                break;
            default:
                break;
        }

    }

    private void startCardHandler(String selectedItem, Context context){
        applicationInterface.fillUpList(selectedItem, context);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CardOverviewActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }

}
