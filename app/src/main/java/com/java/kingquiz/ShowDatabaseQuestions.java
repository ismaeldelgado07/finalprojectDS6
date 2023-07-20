package com.java.kingquiz;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.java.kingquiz.database.DatabaseHelper;

public class ShowDatabaseQuestions extends AppCompatActivity {

    private TextView textViewInformacion;
    private Button buttonVolver;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_logs_layout);

        textViewInformacion = findViewById(R.id.tw_database_log);
        buttonVolver = findViewById(R.id.btn_volver);

        databaseHelper = new DatabaseHelper(this);
        //Crea una objeto en el que se cargará la información que se vaya obteniendo de la base de datos según la consulta hecha
        StringBuilder stringBuilder = new StringBuilder();
        //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos
        Cursor cursor = databaseHelper.verPreguntas();

        if (cursor.getCount() == 0) {
            stringBuilder.append("No hay información registrada.");
        } else {
            while (cursor.moveToNext()) {
                //Extrae la información obtenida en la consulta y la va cargando en orden en objetos de tipo cadena/texto
                String question  = cursor.getString(0);
                String question_id  = cursor.getString(1);
                String subject  = cursor.getString(2);


                //Arma una cadena con la información cargada en las variables anteriores, y hace un espaciado para el siguiente párrafo
                stringBuilder.append("question: ").append(question).append("\n");
                stringBuilder.append("question_id: ").append(question_id).append("\n");
                stringBuilder.append("subject: ").append(subject).append("\n");

            }
        }
        //Carga la información del objeto StringBuilder en el TextView para que sea mostrado en la pantalla
        textViewInformacion.setText(stringBuilder.toString());

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //Regresa al MainActivity
            }
        });

    }

}
