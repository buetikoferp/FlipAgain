package com.team.flipagain.gui.mainScreen.cardGetter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.team.flipagain.R;
import com.team.flipagain.application.ListHandlerInterfaceV1;
import com.team.flipagain.application.ListHandlerV1;
import com.team.flipagain.gui.mainScreen.MainScreenActivity;

public class CardGetterActivity extends AppCompatActivity {

    private ListView listView;
    private ListHandlerInterfaceV1 listHandler;
    private Context context;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_getter);


        button = (Button) findViewById(R.id.cardGetter_btn_download);
        listView =(ListView) findViewById(R.id.cardGetter_list_bundles);
        context = listView.getContext();
        textView = (TextView)findViewById(R.id.cardGetter_textView_title);

        listHandler = new ListHandlerV1(context, listView, this, button, textView);
        listHandler.setFirstList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listHandler.setNextListView(parent.getAdapter().getItem(position).toString());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  downloadNewBundle()
                if(button.getText().equals("Neu laden")){
                    listHandler.setState(3);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        if(!listHandler.setPreviousListView()){
            Intent intent = new Intent(CardGetterActivity.this, MainScreenActivity.class);
            startActivity(intent);
        }
    }
}
