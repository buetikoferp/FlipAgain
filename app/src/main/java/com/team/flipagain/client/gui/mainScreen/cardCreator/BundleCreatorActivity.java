package com.team.flipagain.client.gui.mainScreen.cardCreator;

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

public class BundleCreatorActivity extends AppCompatActivity {
    public String TAG = "BUNDLECREATOR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_bundle);



        int numberOfCase = getIntent().getIntExtra("case", 1);
        Log.d(TAG, "numberOfCase = " + numberOfCase);
        setListviewAdapter(numberOfCase, null);

    }

    private   void setListviewAdapter(final int numberOfCase, final String name){
        final Context list = findViewById(R.id.creatorBundle_listView).getContext();
        final ListHandlerInterface listHandlerInterface = new ListHandler(list);
        final ListView cardOverviewListView = (ListView) findViewById(R.id.creatorBundle_listView);
        TextView textView = (TextView)findViewById(R.id.creatorBundle_textView_title);

        int count = numberOfCase;

        switch (count) {
            case 1:
                textView.setText("Studiengänge");
                Log.d(TAG,"reached case 1");
                setTitle("Navigation");
                cardOverviewListView.setAdapter(listHandlerInterface.getFieldOfStudyAdapter());

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
                textView.setText("Module:");
                setTitle(name);
                Log.d(TAG,"reached case 2");
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
                textView.setText("Bundleliste:");
                Log.d(TAG,"reached case 3");
                cardOverviewListView.setAdapter(listHandlerInterface.getBundleAdapter(name));
                cardOverviewListView.setEnabled(false);
                final String nameofModule = name;
                Toast.makeText(this, nameofModule,Toast.LENGTH_LONG);

                final Button button = (Button) findViewById(R.id.creatorBundle_btn_save);
                button.setEnabled(true);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BundleCreatorActivity.this, CardCreatorActivity.class);
                        TextView save = (TextView) findViewById(R.id.creatorBundle_txtF_bundlename);
                        if(save.getText().toString().isEmpty() || save.getText().toString().equals(" ")){
                            Toast.makeText(button.getContext() , "Geben Sie bitte den Namen des Bundles ein", Toast.LENGTH_LONG).show();

                        }else{
                            listHandlerInterface.createNewBundle(save.getText().toString(),nameofModule);
                            intent.putExtra("nameOfBundle" , save.getText().toString() );
                            startActivity(intent);
                        }
                    }
                });

                break;
            default:
                break;
        }

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BundleCreatorActivity.this, CardCreatorActivity.class);
        intent.putExtra("nameOfBundle" , "start");
        startActivity(intent);
    }
}
