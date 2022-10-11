package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FormLogin extends AppCompatActivity {

    private ImageView btn_voltar;
    private Button btn_entrar;
    private EditText email;
    private String email_txt;
    public String teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        //permite usar a internet a hora que quiser
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportActionBar().hide();
        IniciarComponentes();

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, Inicial.class);
                startActivity(intent);
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(FormLogin.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(FormLogin.this, new String[]{Manifest.permission.INTERNET}, 1);
                }
                else {
                    carga();
                    email_txt = email.getText().toString();
                }
                Toast.makeText(FormLogin.this, teste, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void carga(){
        teste = Conexao.getParadas("https://tsb-api-policy-engine.herokuapp.com/fipe/marcas/1/modelos/1/anos/");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    carga();
                } else {
                    Toast.makeText(FormLogin.this, "negadoooo deu ruim", Toast.LENGTH_LONG);
                }
                return;
            }
        }

    }

    private void IniciarComponentes(){

        btn_voltar = findViewById(R.id.arrow_left);
        btn_entrar = findViewById(R.id.btn_entrar_form);
        email = findViewById(R.id.login_text_email);
    }
}