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

import com.team.flipagain.R;
import com.team.flipagain.client.application.ListHandler;
import com.team.flipagain.client.application.ListHandlerInterface;
import com.team.flipagain.client.gui.mainScreen.MainScreenActivity;

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
        TextView textView = (TextView)findViewById(R.id.selectBundle_textView_title);
        ListHandlerInterface listHandlerInterface = new ListHandler(list);
        final ListView selectBundleListView = (ListView) findViewById(R.id.selectBundle_listV);
        final String nameOfModule = name;
        int count = numberOfCase;

        switch (count) {
            case 1:
                textView.setText("Studiengänge:");
                selectBundleListView.setAdapter(listHandlerInterface.getFieldOfStudyAdapter());

                selectBundleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String nameofStudy = parent.getAdapter().getItem(position).toString();
                        setListviewAdapter(2, nameofStudy);
                    }
                });
                break;
            case 2:
                textView.setText("Module:");
                setTitle(name);
                selectBundleListView.setAdapter(listHandlerInterface.getModuleAdapter(name));
                selectBundleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String nameOfModule = parent.getAdapter().getItem(position).toString();
                        setListviewAdapter(3, nameOfModule);
                    }
                });
                break;
            case 3:
                textView.setText("Bundleliste:");
                setTitle(name);
                selectBundleListView.setAdapter(listHandlerInterface.getBundleAdapter(name));
                selectBundleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        final String nameofBundle = parent.getAdapter().getItem(position).toString();
                        Intent intent = new Intent(SelectBundleActivity.this, CardCreatorActivity.class );

                        intent.putExtra("nameOfModule", nameOfModule);
                        intent.putExtra("nameOfBundle", nameofBundle);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SelectBundleActivity.this, CardCreatorActivity.class);
        intent.putExtra("nameOfBundle" , "start");
        startActivity(intent);
    }
}
