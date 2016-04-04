package com.team.flipagain.client.gui.mainScreen.cardGetter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team.flipagain.R;
import com.team.flipagain.client.gui.mainScreen.MainScreenActivity;

public class CardGetterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_getter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CardGetterActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }
}
