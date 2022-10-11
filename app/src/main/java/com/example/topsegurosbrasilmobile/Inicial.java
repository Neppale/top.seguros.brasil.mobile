package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicial extends AppCompatActivity {

    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        getSupportActionBar().hide();
        IniciarComponentes();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicial.this, FormLogin.class);
                startActivity(intent);
            }
        });
    }

    private void IniciarComponentes(){
        entrar = findViewById(R.id.btn_entrar);
    }
}