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

public class Maintenance  extends AppCompatActivity {


    private EditText editTextPregunta;
    private EditText editTextRespuesta;
    private Button buttonRegistrarPregunta;
    // private Button buttonVolver;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_layout);

        editTextPregunta = findViewById(R.id.et_agregar_pregunta);
        editTextRespuesta = findViewById(R.id.et_agregar_respuesta);

        buttonRegistrarPregunta = findViewById(R.id.btn_agregar_pregunta);
        // buttonVolver = findViewById(R.id.buttonVolver);

        databaseHelper = new DatabaseHelper(this);

        buttonRegistrarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pregunta = editTextPregunta.getText().toString();
                String respuesta = editTextRespuesta.getText().toString();

                //Envía los datos ingresados en los edittext y devuelve un booleano
                //Devuelve true si se cargaron los datos correctamente, y false en caso contrario
                boolean guardadoExitoso = databaseHelper.RegistrarPreguntasCorrectas(pregunta, respuesta);
                if (guardadoExitoso) {
                    Toast.makeText(Maintenance.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Regresa al MainActivity
                } else {
                    Toast.makeText(Maintenance.this, "No se ha podido guardar correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView btn_show_me_login= (TextView) findViewById(R.id.btn_db_login);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_show_me_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDBLogin("login");
            }
        });

        TextView btn_show_me_questions= (TextView) findViewById(R.id.btn_preguntas);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_show_me_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                showMeDBQuestions("questions");
            }
        });

    }

    public void showMeDBLogin(String db){
        Intent i = new Intent (this, ShowDatabaseLogin.class);
        i.putExtra("tipodb", db);
        startActivity(i);
    }

    public void showMeDBQuestions(String db){
        Intent i = new Intent (this, ShowDatabaseQuestions.class);
        i.putExtra("tipodb", db);
        startActivity(i);
    }
}
