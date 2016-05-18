package com.team.flipagain.gui.mainScreen.cardScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.application.ListHandlerInterface;
import com.team.flipagain.application.ListHandler;
import com.team.flipagain.gui.mainScreen.MainScreenActivity;

public class CardOverviewActivity extends AppCompatActivity implements CardScreenInterface{

    private String TAG = "CARDOVERVIEW";
    private ListView listView;
    private ListHandlerInterface listHandler;
    private Context context;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_overview);

        button = (Button)findViewById(R.id.cardOverview_btn_start);
        listView = (ListView)findViewById(R.id.cardOverview_list_bundles);
        context = listView.getContext();
        textView = (TextView) findViewById(R.id.cardOverview_txtV_title);

        listHandler = new ListHandler(context, listView, this, button, textView);
        listHandler.setFirstList();
        listHandler.setCardHandler(CARD_HANDLER_INTERFACE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listHandler.setNextListView(parent.getAdapter().getItem(position).toString());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(CardOverviewActivity.this, CardFlipperActivity.class);
                if (CARD_HANDLER_INTERFACE.bundleSelected()) {
                    startActivity(start);
                } else {
                    Toast.makeText(button.getContext(), "Leider beinhaltet dieses Bundle noch keine Karten", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    @Override
    public void onBackPressed() {
        if(!listHandler.setPreviousListView()){
            Intent intent = new Intent(CardOverviewActivity.this, MainScreenActivity.class);
            startActivity(intent);
        }
    }

}
