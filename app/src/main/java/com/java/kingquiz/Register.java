package com.java.kingquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.java.kingquiz.database.DatabaseHelper;

public class Register extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextNombres;
    private EditText editTextMail;
    private EditText editTextPass;
    private Button buttonRegistrarS;
   // private Button buttonVolver;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        editTextUsername = findViewById(R.id.et_username);
        editTextNombres = findViewById(R.id.et_name);
        editTextMail = findViewById(R.id.et_email);
        editTextPass = findViewById(R.id.et_password);
        buttonRegistrarS = findViewById(R.id.btn_registrarse);
       // buttonVolver = findViewById(R.id.buttonVolver);

        databaseHelper = new DatabaseHelper(this);

        buttonRegistrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String nombre = editTextNombres.getText().toString();
                String correo = editTextMail.getText().toString();
                String contrasena = editTextPass.getText().toString();
                //Envía los datos ingresados en los edittext y devuelve un booleano
                //Devuelve true si se cargaron los datos correctamente, y false en caso contrario
                boolean guardadoExitoso = databaseHelper.registrarUsuarios(username, nombre, correo, contrasena);
                if (guardadoExitoso) {
                    Toast.makeText(Register.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Regresa al MainActivity
                } else {
                    Toast.makeText(Register.this, "No se ha podido guardar correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresa al MainActivity
            }
        });**/
    }
}
