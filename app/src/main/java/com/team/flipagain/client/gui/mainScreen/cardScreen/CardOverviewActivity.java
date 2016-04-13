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
import android.widget.TextView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.client.application.ListHandler;
import com.team.flipagain.client.application.ListHandlerInterface;
import com.team.flipagain.client.domain.DBManager;
import com.team.flipagain.client.gui.mainScreen.MainScreenActivity;

public class CardOverviewActivity extends AppCompatActivity implements CardScreenInterface{
   // private CardHandlerInterface CARD_HANDLER_INTERFACE = new CardHandler();
    private String TAG = "CARDOVERVIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_overview);

        final Button startbutton = (Button) findViewById(R.id.cardOverview_btn_start);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(CardOverviewActivity.this, CardFlipperActivity.class);
                if (CARD_HANDLER_INTERFACE.bundleSelected()) {

                    startActivity(start);
                } else {
                    Toast.makeText(startbutton.getContext(), "Leider beinhaltet dieses Bundle noch keine Karten", Toast.LENGTH_LONG).show();
                }
            }

        });

        int numberOfCase = getIntent().getIntExtra("case", 1);
        String nameOfListener = getIntent().getStringExtra("nameOfListener");
        if(nameOfListener == null){
        Log.d(TAG,"numberOfCase = " + numberOfCase);
        setListviewAdapter(numberOfCase, null);}
        else{
            setListviewAdapter(numberOfCase,nameOfListener);
        }

    }
    

    private   void setListviewAdapter(final int numberOfCase, String name){
        final Context list = findViewById(R.id.cardOverview_list_bundles).getContext();
        ListHandlerInterface listHandlerInterface = new ListHandler(list);
        final ListView cardOverviewListView = (ListView) findViewById(R.id.cardOverview_list_bundles);
        TextView title = (TextView)findViewById(R.id.cardOverview_txtV_title);
        int count = numberOfCase;

        switch (count) {
            case 1:
                setTitle("Navigation");
                title.setText("Studiengänge:");
                cardOverviewListView.setAdapter(listHandlerInterface.getFieldOfStudyAdapter());

                cardOverviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        Intent caseTwo = new Intent(CardOverviewActivity.this, CardOverviewActivity.class);
                        caseTwo.putExtra("nameOfListener", name);
                        caseTwo.putExtra("case", 2);
                        startActivity(caseTwo);
                        //setListviewAdapter(2, name);
                    }
                });
                break;
            case 2:
                title.setText("Module:");
                setTitle(name);
                cardOverviewListView.setAdapter(listHandlerInterface.getModuleAdapter(name));
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
                setTitle(name);
                title.setText("Bundle:");
                cardOverviewListView.setAdapter(listHandlerInterface.getBundleAdapter(name));
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
        CARD_HANDLER_INTERFACE.fillUpList(selectedItem, context);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CardOverviewActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }

}
