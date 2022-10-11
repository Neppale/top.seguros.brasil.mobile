package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login2 extends AppCompatActivity {

    private Button me_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        getSupportActionBar().hide();
        IniciarComponentes();

        me_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login2.this, FormCadastro.class);
                startActivity(intent);
            }
        });
    }

    private void IniciarComponentes(){
        me_cadastrar = findViewById(R.id.me_cadastrar);
    }
}