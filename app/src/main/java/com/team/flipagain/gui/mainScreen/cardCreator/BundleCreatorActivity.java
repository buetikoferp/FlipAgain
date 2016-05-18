package com.team.flipagain.gui.mainScreen.cardCreator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.team.flipagain.R;
import com.team.flipagain.application.ListHandlerInterface;
import com.team.flipagain.application.ListHandler;

public class BundleCreatorActivity extends AppCompatActivity {

    public String TAG = "BUNDLECREATOR";
    private ListView listView;
    private ListHandlerInterface listHandler;
    private Context context;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_bundle);

        button = (Button) findViewById(R.id.creatorBundle_btn);
        listView =(ListView) findViewById(R.id.creatorBundle_listView);
        context = listView.getContext();
        textView = (TextView)findViewById(R.id.creatorBundle_textView_title);
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
                Intent intent = new Intent(BundleCreatorActivity.this, CardCreatorActivity.class);
                EditText editText = (EditText)findViewById(R.id.creatorBundle_txtF_bundlename);
                String bundleName = editText.getText().toString();
                intent.putExtra("nameOfModule", listHandler.getModule());
                intent.putExtra("nameOfBundle", bundleName);
                intent.putExtra("isNewBundle", true);
                if(bundleName != null && !bundleName.isEmpty()){
                    startActivity(intent);
                }else{
                    Toast.makeText(button.getContext(), "Geben Sie eine Bezeichnung ein",Toast.LENGTH_LONG).show();
                }


            }
        });



    }

    @Override
    public void onBackPressed() {
        if(!listHandler.setPreviousListView()){
            Intent intent = new Intent(BundleCreatorActivity.this,  CardCreatorActivity.class);
            startActivity(intent);
        }
    }
}
