package com.java.kingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_details_layout);

        Button btn_play_now = (Button) findViewById(R.id.btn_playnow);
        btn_play_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                playNow();

            }
        });
    }

    public void playNow(){
        Intent i = new Intent (this, Gameplay.class);
        startActivity(i);
    }
}
