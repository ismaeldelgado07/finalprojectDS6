package com.java.kingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Maintenance  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_layout);

        TextView btn_show_me_login= (TextView) findViewById(R.id.btn_db_login);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_show_me_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDB();
            }
        });

        TextView btn_show_me_questions= (TextView) findViewById(R.id.btn_preguntas);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_show_me_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDB();
            }
        });

    }

    public void showMeDB(){
        Intent i = new Intent (this, ShowDatabaseInformation.class);
        startActivity(i);
    }
}
