package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Logado extends AppCompatActivity {

    private TextView nomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        getSupportActionBar().hide();
        IniciarComponentes();
    }

    private void IniciarComponentes(){
        nomeUser = findViewById(R.id.text_nome_user);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUser.setText(extras.getString("nome"));
        }
    }
}