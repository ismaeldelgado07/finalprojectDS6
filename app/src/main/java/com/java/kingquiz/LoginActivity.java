package com.java.kingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo

                iniciarSesion();
            }
        });


    }

    public void iniciarSesion(){
        Intent i = new Intent (this, Menu.class);
        startActivity(i);
    }

}

