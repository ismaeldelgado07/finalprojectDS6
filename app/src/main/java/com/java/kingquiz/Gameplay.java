package com.java.kingquiz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.java.kingquiz.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gameplay extends AppCompatActivity {

    public TextView tw_pregunta;
    public TextView tw_counterSlides;
    public Button btn_opcion1;
    public Button btn_opcion2;
    public Button btn_opcion3;
    public Button btn_opcion4;

    public Button btn_nextQuestion;

    private DatabaseHelper databaseHelper;

    public int counter;
    TextView tw_timer;
    int clickcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay_layout);

        tw_timer = (TextView) findViewById(R.id.tw_timer);
        startCountDown(tw_timer);

        tw_pregunta = findViewById(R.id.tw_question);
        tw_counterSlides = findViewById(R.id.tw_countSlides);

        btn_opcion1 = findViewById(R.id.btn_opcion1);
        btn_opcion2 = findViewById(R.id.btn_opcion2);
        btn_opcion3= findViewById(R.id.btn_opcion3);
        btn_opcion4 = findViewById(R.id.btn_opcion4);
        btn_nextQuestion = findViewById(R.id.btn_nextQuestion);

       // buttonVolver = findViewById(R.id.btn_volver);

        databaseHelper = new DatabaseHelper(this);
        //Crea una objeto en el que se cargará la información que se vaya obteniendo de la base de datos según la consulta hecha
        StringBuilder stringBuilder = new StringBuilder();
        //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos

        playGame();

        btn_nextQuestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_opcion1.setClickable(true);
                btn_opcion2.setClickable(true);
                btn_opcion3.setClickable(true);
                btn_opcion4.setClickable(true);

                playGame();
                clickcount=clickcount+1;
                if(clickcount==15)
                {
                    showMeResults();
                }
                else
                {
                    tw_counterSlides.setText(clickcount+"/15");
                }

            }
        });

    }

    public void playGame(){
        Cursor cursorQuestion = databaseHelper.bringMeQuestion();
        StringBuilder stringBuilder = new StringBuilder();

        if (cursorQuestion.getCount() == 0) {
            stringBuilder.append("No hay información registrada.");
        } else {
            while (cursorQuestion.moveToNext()) {
                //Extrae la información obtenida en la consulta y la va cargando en orden en objetos de tipo cadena/texto
                String question = cursorQuestion.getString(0);
                String questionid = cursorQuestion.getString(1);

                //Arma una cadena con la información cargada en las variables anteriores, y hace un espaciado para el siguiente párrafo
                stringBuilder.append("¿ ").append(question).append(" ?");

               // gimmeAnswers(questionid);
                showMeCorrectAnswer(questionid);
              //  showMeWrongOptions(questionid);
                showIfAnswerIsCorrect(questionid);
            }
        }
        //Carga la información del objeto StringBuilder en el TextView para que sea mostrado en la pantalla
        tw_pregunta.setText(stringBuilder.toString());
    }

    public void startCountDown(View v) {
        new CountDownTimer(300000, 1000){
            public void onTick(long millisUntilFinished){
                tw_timer.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                tw_timer.setText("FINISH!!");
            }
        }.start();
    }

    public void showMeCorrectAnswer(String id){
        databaseHelper = new DatabaseHelper(this);
        //Crea una objeto en el que se cargará la información que se vaya obteniendo de la base de datos según la consulta hecha
        StringBuilder stringBuilder = new StringBuilder();
        //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos

        try {
            Cursor cursorQuestion = databaseHelper.bringMeCorrectAnswer(id);
            if (cursorQuestion.getCount() == 0) {
                stringBuilder.append("No hay información registrada.");
            } else {
                while (cursorQuestion.moveToNext()) {
                    //Extrae la información obtenida en la consulta y la va cargando en orden en objetos de tipo cadena/texto
                    String iscorrect = cursorQuestion.getString(0);

                    //Arma una cadena con la información cargada en las variables anteriores, y hace un espaciado para el siguiente párrafo
                    stringBuilder.append(iscorrect);
                    showMeWrongOptions(iscorrect, id);
                }
            }
            //Carga la información del objeto StringBuilder en el TextView para que sea mostrado en la pantalla
          //  suffleWrongAnswersRandom(stringBuilder.toString());
            btn_opcion1.setText(stringBuilder.toString());

        }
        catch(Exception e) {
            e.printStackTrace(System.out);
        }

    }

    public void showMeWrongOptions(String rightAnswer, String id) {
        StringBuilder stringBuilder = new StringBuilder();
        //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos
        Cursor cursor = databaseHelper.bringMeWrongAnswers(id);
        int i = 0;
     //   String[] intArray = {};
       // List<String> list = new ArrayList<String>();
       // list.add(rightAnswer);

        if (cursor.getCount() == 0) {
            stringBuilder.append("No hay información registrada.");
        } else {
            while (cursor.moveToNext()) {
                //Extrae la información obtenida en la consulta y la va cargando en orden en objetos de tipo cadena/texto
                String respuestaIncorrecta = cursor.getString(0);
         //       list.add(respuestaIncorrectas);
           //     Collections.shuffle(list);
             //   list.toArray(intArray);

                //Arma una cadena con la información cargada en las variables anteriores, y hace un espaciado para el siguiente párrafo

                if (cursor.getCount() == 3) {
                    {
                        if (i == 0) {
                            btn_opcion2.setText(respuestaIncorrecta);
                        } else if (i == 1) {
                            btn_opcion3.setText(respuestaIncorrecta);
                        } else if (i == 2) {
                            btn_opcion4.setText(respuestaIncorrecta);
                        }
                    }
                } i++;
            } //btn_opcion4.setText(rightAnswer);

               /*  if (i == 2) {

                    //btn_opcion4.setText(list.get(3));

                    for (int x = 0; x < list.size(); x++) {
                        System.out.println(list.get(x));
                        if (x == 0) {
                            btn_opcion1.setText(list.get(x));
                        } else if (x == 1) {
                            btn_opcion2.setText(list.get(x));
                        } else if (x == 2) {
                            btn_opcion3.setText(list.get(x));
                        } else if (x == 3) {
                            btn_opcion4.setText(list.get(x));
                        }
                    }*/

                }
        //Carga la información del objeto StringBuilder en el TextView para que sea mostrado en la pantalla
        // suffleWrongAnswersRandom(stringBuilder.toString());

            }

    public void suffleWrongAnswersRandom(String respuestasIncorrectas){
        String str = respuestasIncorrectas;
        String[] firstName = new String[] {Arrays.toString(str.split("(?=[A-Z])"))};
        List<String> strList = Arrays.asList(firstName);

        String r;
        Collections.shuffle(strList);
        for (int i = 0; i < strList.size(); i++) {
           System.out.println(strList.get(i));
           if(i==1){
               btn_opcion1.setText(strList.get(i));
           }else if(i==2){
               r=strList.get(i);
               btn_opcion2.setText(r);
           }
           else if(i==0){
               r=strList.get(i);
               btn_opcion3.setText(r);
           }
           else if(i==4){
               btn_opcion4.setText(strList.get(i));
           }
        }

}

    public void showIfAnswerIsCorrect (String id){
        btn_opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String respuestaElegida = btn_opcion1.getText().toString();
                    StringBuilder stringBuilder = new StringBuilder();
                    //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos
                    Cursor cursor = databaseHelper.validateAnswer(respuestaElegida,id);

                    if(cursor.getCount() == 2){
                        Toast.makeText(Gameplay.this,"¡Respuesta correcta!" , Toast.LENGTH_LONG).show();
                        btn_opcion1.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_secondary_variant));
                      //  btn_opcion1.setText("¡Respuesta Correcta!");
                        //btn_opcion1.setTextColor(Color.WHITE);

                    }else{
                        Toast.makeText(Gameplay.this,"¡Respuesta Incorrecta!" , Toast.LENGTH_LONG).show();
                        //btn_opcion1.setTextColor(Color.WHITE);
                    }
                }
        });

        btn_opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaElegida = btn_opcion2.getText().toString();
                StringBuilder stringBuilder = new StringBuilder();
                //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos
                Cursor cursor = databaseHelper.validateAnswer(respuestaElegida,id);

                if(cursor.getCount() == 2){
                    Toast.makeText(Gameplay.this,"¡Respuesta correcta!" , Toast.LENGTH_LONG).show();
                    btn_opcion2.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_secondary_variant));
                }else{
                    Toast.makeText(Gameplay.this,"¡Respuesta Incorrecta!" , Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaElegida = btn_opcion2.getText().toString();
                StringBuilder stringBuilder = new StringBuilder();
                //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos
                Cursor cursor = databaseHelper.validateAnswer(respuestaElegida,id);

                if(cursor.getCount() == 2){
                    Toast.makeText(Gameplay.this,"¡Respuesta correcta!" , Toast.LENGTH_LONG).show();
                    btn_opcion3.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_secondary_variant));
                }else{
                    Toast.makeText(Gameplay.this,"¡Respuesta Incorrecta!" , Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaElegida = btn_opcion2.getText().toString();
                StringBuilder stringBuilder = new StringBuilder();
                //Aquí creamos un objeto Cursor para cargar la información obtenida de la base de datos
                Cursor cursor = databaseHelper.validateAnswer(respuestaElegida,id);

                if(cursor.getCount() == 2){
                    Toast.makeText(Gameplay.this,"¡Respuesta correcta!" , Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Gameplay.this,"¡Respuesta Incorrecta!" , Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    public void showMeResults(){
        Intent i = new Intent (this, ShowResults.class);
        startActivity(i);
    }

}
