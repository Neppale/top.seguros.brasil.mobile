package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Logado extends AppCompatActivity {

    private TextView nomeUser;
    private JSONObject responseLogin;
    private User cliente = new User();
    private ListView listaCoberturas;
    private Apolice apolice = new Apolice();
    private Cobertura cobertura = new Cobertura();
    private ResponseText respText = new ResponseText();

    public String URL_BASE = "https://tsb-api-policy-engine.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        getSupportActionBar().hide();
        IniciarComponentes();
        SetText();


        nomeUser.setText(cliente.getNome_completo()+"!"+respText.getResp());
    }

    private void IniciarComponentes(){
        listaCoberturas = findViewById(R.id.lista_coberturas);
        nomeUser = findViewById(R.id.text_nome_user);
    }

    private void SetText(){
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            try {
                responseLogin = new JSONObject(extras.getString("responseLogin"));
                cliente.setToken(responseLogin.getString("token"));
                cliente.setNome_completo(responseLogin.getJSONObject("client").getString("nome_completo"));
                cliente.setId_usuario(Integer.parseInt(responseLogin.getJSONObject("client").getString("id_cliente")));

                int id_user = cliente.getId_usuario();

                Get(URL_BASE, "/apolice/cliente/"+id_user, cliente.getToken(),Logado.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void Get(String URL_BASE, String urlPath, String token, Context contexto){

        RequestQueue queue = Volley.newRequestQueue(contexto);
        String url = URL_BASE + urlPath;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        respText.setResp(response);
                        try{
                            JSONObject a = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                respText.setResp(error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private class ResponseText{
        public String resp;

        public String getResp(){
            return resp;
        }
        public void setResp(String resp){
            this.resp = resp;
        }
    }
}