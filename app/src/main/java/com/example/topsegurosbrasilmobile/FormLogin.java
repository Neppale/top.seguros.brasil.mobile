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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class FormLogin extends AppCompatActivity {

    private ImageView btn_voltar;
    private Button btn_entrar;
    private EditText Edit_email, Edit_senha;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};
    public String URL_BASE = "https://tsb-api-policy-engine.herokuapp.com";

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
                String email = Edit_email.getText().toString().trim();
                String senha = Edit_senha.getText().toString().trim();

                email = "llll@deee.com";
                senha = "Senha123-";

                if(ActivityCompat.checkSelfPermission(FormLogin.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(FormLogin.this, new String[]{Manifest.permission.INTERNET}, 1);
                } else {

                    if(email.isEmpty() || senha.isEmpty()){

                        Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.LTGRAY);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();

                    } else {
                        try {
                            postLogin("/usuario/login", email, senha, view);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public String postLogin(String urlPath, String email, String senha, View view) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(FormLogin.this);
        String url = URL_BASE + urlPath;

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("email", email);
        jsonBody.put("senha", senha);
        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jResponse = null;
                        String token = "";
                        try {
                            jResponse = new JSONObject(response);
                            //user.token = jResponse.getString("token");
                            token = jResponse.getJSONObject("user").getString("nome_completo");

                            Intent intent = new Intent(FormLogin.this, Logado.class);
                            intent.putExtra("nome", token);

                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String b = new String(error.networkResponse.data);

                Snackbar snackbar = Snackbar.make(view, b, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.RED);
                snackbar.setTextColor(Color.BLUE);
                snackbar.show();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };


        queue.add(stringRequest);

        return "";
    }

    public String getPadrao(String urlPath){

        RequestQueue queue = Volley.newRequestQueue(FormLogin.this);
        String url = URL_BASE + urlPath;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btn_entrar.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_entrar.setText("deu ruim");
            }
        });

        queue.add(stringRequest);
        return "";
    }

    private void IniciarComponentes(){
        btn_voltar = findViewById(R.id.arrow_left);
        btn_entrar = findViewById(R.id.btn_entrar_form);
        Edit_email = findViewById(R.id.login_text_email);
        Edit_senha = findViewById(R.id.login_text_senha);
    }


}