package com.java.kingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ShowResults extends AppCompatActivity {

    private Button btn_playAgain;
    private TextView tw_backtomehomepage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showresults_activity);

        Button btn_playAgain = findViewById(R.id.btn_playagain);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                takeMeBack();
            }
        });

        TextView tw_backtomehomepage = (TextView) findViewById(R.id.tw_backtomepage);
        //Se realiza la acción de escuchar cuando el boton es clickeado
        tw_backtomehomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cuando se hace click, se llamada este metodo
                takeMeBack();
            }
        });

}

    public void takeMeBack(){
        Intent i = new Intent (this, Menu.class);
        startActivity(i);
    }



}
