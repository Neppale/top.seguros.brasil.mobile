package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Logado extends AppCompatActivity {

    private TextView nomeUser;
    private JSONObject responseLogin;
    private User cliente = new User();

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

            try {

                responseLogin = new JSONObject(extras.getString("responseLogin"));

                cliente.setToken(responseLogin.getString("token"));
                cliente.setNome_completo(responseLogin.getJSONObject("client").getString("nome_completo"));

                cliente.setId_usuario(Integer.parseInt(responseLogin.getJSONObject("client").getString("id_cliente")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            nomeUser.setText(cliente.getNome_completo()+"!");
        }
    }
}