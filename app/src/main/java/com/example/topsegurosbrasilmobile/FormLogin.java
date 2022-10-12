package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class FormLogin extends AppCompatActivity {

    private ImageView btn_voltar;
    private Button btn_entrar;
    private EditText Edit_email, Edit_senha;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};

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
                } else {
                    String email = Edit_email.getText().toString();
                    String senha = Edit_senha.getText().toString();
                    if(email.isEmpty() || senha.isEmpty()){
                        Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.LTGRAY);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                    } else {
                        FazerLogin();
                    }
                }
            }
        });
    }

    public void carga(){
        //teste = Conexao.getParadas("https://tsb-api-policy-engine.herokuapp.com/fipe/marcas/1/modelos/1/anos/");
    }

    /* //verifica se tem as permissões de uso da internet
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
    */
    private void FazerLogin(){
        String email = Edit_email.getText().toString();
        String senha = Edit_senha.getText().toString();


    }

    private void IniciarComponentes(){
        btn_voltar = findViewById(R.id.arrow_left);
        btn_entrar = findViewById(R.id.btn_entrar_form);
        Edit_email = findViewById(R.id.login_text_email);
        Edit_senha = findViewById(R.id.login_text_senha);
    }


}