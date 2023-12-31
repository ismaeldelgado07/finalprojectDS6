package com.java.kingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button btn_game_details = (Button) findViewById(R.id.btn_jugar_conocimiento_general);
        btn_game_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo

                showGameDetails();

            }
        });
    }

    public void showGameDetails(){
        Intent i = new Intent (this, GameDetails.class);
        startActivity(i);
    }

}
