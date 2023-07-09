package com.java.kingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.java.kingquiz.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPass;
    private Button buttonIniciarS;
    //private Button buttonVolver;

    private DatabaseHelper databaseHelper;

    String[] roles={"Estudiante","Profesor","Trabajo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        editTextUsername = findViewById(R.id.et_username);
        editTextPass = findViewById(R.id.et_password);
        buttonIniciarS = findViewById(R.id.btn_login);
       // buttonVolver = findViewById(R.id.buttonVolver);
        databaseHelper = new DatabaseHelper(this);

       /** Spinner spin1 = (Spinner) findViewById(R.id.spinner_roles);
        spin1.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,roles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);**/



        Button btn_login = (Button) findViewById(R.id.btn_login);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Declaramos estas variables de tipo texto y les damos el valor del texto ingresado en los EditText
                String username = editTextUsername.getText().toString();
                String contrasena = editTextPass.getText().toString();

                //Declaramos esta variable de tipo booleano enviándole los textos para ejecutar el método "Validar", en la
                // clase DataBaseHelper, lo que hace es validar que efectivamente se hayan registrado esos datos a la base de datos y devolver un true or false
                boolean iniciarSesionExitoso = databaseHelper.Validar(username, contrasena);

                if (iniciarSesionExitoso) {
                    iniciarSesion();
                    //Si se devuelve un true, que significa que los datos sí estaban registrados en la base de datos, entonces ejecutará estas funciones

                    //Esta sección establece la variable "Loggeado" del MainActivity como verdadero, lo que significa que se ha iniciado sesión correctamente
                    //setLoggeado(true);

                    //Esta sección envía el número de cédula ingresado en el EditTextCed y devuelve la cadena del nombre almacenado en la base de datos en la misma fila que la cédula
                    String nombre = databaseHelper.obtenerNombre(username);

                    //Esta sección crea una cadena formada por el nombre recibido del método anterior y envía la cadena al MainActivity para mostrarla en un TextView
                  //  String mensajeBienvenida = "Bienvenido, " + nombre;
                    //setWelcomeMessage(mensajeBienvenida);
                    //Imprime un Toast con la misma cadena de arriba
                   // Toast.makeText(LoginActivity.this, mensajeBienvenida, Toast.LENGTH_SHORT).show();
                    //finish(); // Devuelve al MainActivity
                } else {
                    //Si se devuelve un false entonces mostrará este Toast
                    Toast.makeText(LoginActivity.this, "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView btn_registrarse = (TextView) findViewById(R.id.tw_createNewAccount);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo

                registrarUsuario();

            }
        });

    }

    public void iniciarSesion(){
        Intent i = new Intent (this, Menu.class);
        startActivity(i);
    }

    public void registrarUsuario(){
        Intent i = new Intent (this, Register.class);
        startActivity(i);
    }

}

