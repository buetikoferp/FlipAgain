package com.team.flipagain.client.gui.mainScreen.cardCreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team.flipagain.R;

public class CardCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_cards);

        // Button Listener to Create Cards Activity
        Button createBundle = (Button) findViewById(R.id.creatorCards_btn_newBundle);
        createBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardCreatorActivity.this, BundleCreatorActivity.class);
                startActivity(intent);
            }
        });
    }
}
