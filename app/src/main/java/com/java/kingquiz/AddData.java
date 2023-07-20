package com.java.kingquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.java.kingquiz.database.DatabaseHelper;

public class AddData extends AppCompatActivity {
    private EditText editTextQuestion;
    private EditText ediTextSubjectQuestion;
    private EditText ediTextQuestionId;
    private EditText editTextResponse;
    private EditText editTextResponseId;
    private EditText editTextIsCorrect;
    private EditText editTextSubjectAnswers;
    private Button buttonRegistrarPregunta;
    private Button buttonRegistrarRespuesta;
    // private Button buttonVolver;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_layout);

        editTextQuestion = findViewById(R.id.et_agregar_pregunta);
        ediTextSubjectQuestion = findViewById(R.id.et_subject_question);
        ediTextQuestionId = findViewById(R.id.et_questionid);

        buttonRegistrarPregunta = findViewById(R.id.btn_agregar_pregunta);
        // buttonVolver = findViewById(R.id.buttonVolver);

        databaseHelper = new DatabaseHelper(this);

        buttonRegistrarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = editTextQuestion.getText().toString();
                String subject = ediTextSubjectQuestion.getText().toString();
                String questionid = ediTextQuestionId.getText().toString();

                //Envía los datos ingresados en los edittext y devuelve un booleano
                //Devuelve true si se cargaron los datos correctamente, y false en caso contrario
                boolean guardadoExitoso = databaseHelper.registrarPreguntas(question, questionid, subject);
                if (guardadoExitoso) {
                    Toast.makeText(AddData.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Regresa al MainActivity
                } else {
                    Toast.makeText(AddData.this, "No se ha podido guardar correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editTextResponse = findViewById(R.id.et_agregar_respuesta);
        editTextResponseId = findViewById(R.id.tw_answerid);
        editTextIsCorrect = findViewById(R.id.et_iscorrect);
        editTextSubjectAnswers = findViewById(R.id.tw_subject_answers);


        buttonRegistrarRespuesta = findViewById(R.id.btn_agregar_respuesta);
        buttonRegistrarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = editTextResponse.getText().toString();
                String responseId = editTextResponseId.getText().toString();
                String isCorrect = editTextIsCorrect.getText().toString();
                String SubjectAnswers = editTextSubjectAnswers.getText().toString();

                //Envía los datos ingresados en los edittext y devuelve un booleano
                //Devuelve true si se cargaron los datos correctamente, y false en caso contrario
                boolean guardadoExitoso = databaseHelper.registrarRespuestas(responseId, response, isCorrect, SubjectAnswers);
                if (guardadoExitoso) {
                    Toast.makeText(AddData.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Regresa al MainActivity
                } else {
                    Toast.makeText(AddData.this, "No se ha podido guardar correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
