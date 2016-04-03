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

import com.team.flipagain.R;
import com.team.flipagain.client.application.ListHandler;
import com.team.flipagain.client.application.ListHandlerInterface;

public class SelectBundleActivity extends AppCompatActivity {
    private String TAG = "SELECTBUNDLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bundle);
        int numberOfCase = getIntent().getIntExtra("case", 1);
        Log.d(TAG, "numberOfCase = " + numberOfCase);
        setListviewAdapter(numberOfCase, null);
    }

    private   void setListviewAdapter(final int numberOfCase, String name){
        final Context list = findViewById(R.id.selectBundle_listV).getContext();
        ListHandlerInterface listHandlerInterface = new ListHandler(list);
        final ListView selectBundleListView = (ListView) findViewById(R.id.selectBundle_listV);

        int count = numberOfCase;

        switch (count) {
            case 1:
                selectBundleListView.setAdapter(listHandlerInterface.getFieldOfStudyAdapter());

                selectBundleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        setListviewAdapter(2, name);
                    }
                });
                break;
            case 2:
                selectBundleListView.setAdapter(listHandlerInterface.getModuleAdapter(name));
                selectBundleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        setListviewAdapter(3, name);
                    }
                });
                break;
            case 3:
                selectBundleListView.setAdapter(listHandlerInterface.getBundleAdapter(name));
                selectBundleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String name = parent.getAdapter().getItem(position).toString();
                        Intent intent = new Intent(SelectBundleActivity.this, CardCreatorActivity.class );
                        intent.putExtra("nameOfBundle", name);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }

    }
}
