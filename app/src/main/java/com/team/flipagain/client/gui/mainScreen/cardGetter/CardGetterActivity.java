package com.team.flipagain.client.gui.mainScreen.cardGetter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.team.flipagain.R;
import com.team.flipagain.client.application.CardHandler;
import com.team.flipagain.client.application.CardHandlerInterface;
import com.team.flipagain.client.application.ListHandlerInterface;
import com.team.flipagain.client.application.ListHandlerInterfaceV1;
import com.team.flipagain.client.application.ListHandlerV1;
import com.team.flipagain.client.gui.mainScreen.MainScreenActivity;

public class CardGetterActivity extends AppCompatActivity {
    ListView listView;
    ListHandlerInterfaceV1 listHandler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_getter);

        listView =(ListView) findViewById(R.id.cardGetter_list_bundles);
        context = listView.getContext();
        listHandler = new ListHandlerV1(context, listView);
        listHandler.setFirstList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listHandler.setNextListView(parent.getAdapter().getItem(position).toString());
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
