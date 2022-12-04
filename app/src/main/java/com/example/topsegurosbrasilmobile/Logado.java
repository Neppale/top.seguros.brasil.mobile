package com.example.topsegurosbrasilmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Logado extends AppCompatActivity {

    private TextView nomeUser;
    private JSONObject responseLogin;
    private JSONObject responseApolices;
    private User cliente = new User();
    private ListView listaCoberturas;
    private Apolice[] apolices = new Apolice[5];
    private Cobertura cobertura = new Cobertura();
    private ResponseText respText = new ResponseText();
    private TextView textoCoberturaNome;
    private TextView textoCoberturaDescricao;
    private TextView textoCoberturaValor;

    public String URL_BASE = "https://tsb-api-policy-engine.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        getSupportActionBar().hide();
        IniciarComponentes();
        SetText();

        nomeUser.setText(cliente.getNome_completo()+"!");
    }

    private void IniciarComponentes(){
        listaCoberturas = findViewById(R.id.lista_coberturas);
        nomeUser = findViewById(R.id.text_nome_user);
        textoCoberturaNome = findViewById(R.id.text_cobertura_nome);
        textoCoberturaDescricao = findViewById(R.id.text_cobertura_descricao);
        textoCoberturaValor = findViewById(R.id.text_cobertura_valor);
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
                        trocaTexto(response);
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

    public void trocaTexto(String response){
        try {
            responseApolices = new JSONObject(response);

            if(responseApolices.getJSONArray("data").length() == 0){
                textoCoberturaNome.setText("");
                textoCoberturaDescricao.setText("Cliente ainda não possui uma apolice");
                textoCoberturaValor.setText("");
                return;
            }

            String nome = responseApolices.getJSONArray("data").getJSONObject(0).getJSONObject("cobertura").getString("nome");
            String descricao = responseApolices.getJSONArray("data").getJSONObject(0).getJSONObject("cobertura").getString("descricao");

            textoCoberturaNome.setText(nome);
            textoCoberturaDescricao.setText(descricao);
            textoCoberturaValor.setText("R$ " + responseApolices.getJSONArray("data").getJSONObject(0).getJSONObject("cobertura").getString("valor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class ResponseText{
        private String resp;
        public String getResp(){
            return resp;
        }
        public void setResp(String resp){
            this.resp = resp;
        }
    }
}