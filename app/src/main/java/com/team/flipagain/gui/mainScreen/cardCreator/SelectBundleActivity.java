package com.team.flipagain.gui.mainScreen.cardCreator;

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
import com.team.flipagain.application.ListHandlerInterface;
import com.team.flipagain.application.ListHandler;

public class SelectBundleActivity extends AppCompatActivity {
    private String TAG = "SELECTBUNDLE";
    private ListView listView;
    private ListHandlerInterface listHandler;
    private Context context;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bundle);

        button = (Button) findViewById(R.id.selectBundle_btn_select);
        listView =(ListView) findViewById(R.id.selectBundle_listV);
        context = listView.getContext();
        textView = (TextView)findViewById(R.id.selectBundle_textView_title);

        listHandler = new ListHandler(context, listView, this, button, textView);
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
                Intent intent = new Intent(SelectBundleActivity.this, CardCreatorActivity.class);

                intent.putExtra("nameOfModule", listHandler.getModule());
                intent.putExtra("nameOfBundle" , listHandler.getBundle());
                intent.putExtra("isNewBundle", false);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onBackPressed() {
        if(!listHandler.setPreviousListView()){
            Intent intent = new Intent(SelectBundleActivity.this,  CardCreatorActivity.class);
            startActivity(intent);
        }
    }
}
