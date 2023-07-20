package com.java.kingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.java.kingquiz.database.DatabaseHelper;

public class Maintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_layout);

        TextView btn_show_me_login= findViewById(R.id.btn_db_login);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_show_me_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDBLogin();
            }
        });

        TextView btn_show_me_questions= findViewById(R.id.btn_preguntas);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_show_me_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDBQuestions();
            }
        });

        TextView btn_showAnswers= findViewById(R.id.btn_ver_respuestas);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_showAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDBAnswers();
            }
        });

        TextView btn_add_data= findViewById(R.id.btn_agregar_data);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                takeMeToAddData();
            }
        });
    }

    public void showMeDBLogin(){
        Intent i = new Intent (this, ShowDatabaseLogin.class);
        startActivity(i);
    }

    public void showMeDBQuestions(){
        Intent i = new Intent (this, ShowDatabaseQuestions.class);
        startActivity(i);
    }

    public void showMeDBAnswers(){
        Intent i = new Intent (this, ShowDatabaseAnswers.class);
        startActivity(i);
    }

    public void takeMeToAddData(){
        Intent i = new Intent (this, AddData.class);
        startActivity(i);
    }

}
