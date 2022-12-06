package com.example.topsegurosbrasilmobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class FormCadastro extends AppCompatActivity {
    public String URL_BASE = "https://tsb-api-policy-engine.herokuapp.com";
    private TextView textoCima;
    private ImageView btn_voltar;
    private Button btn_continuar;
    private Button btn_cancela;
    private DadosCadastro form = new DadosCadastro();
    private EditText campo1;
    private EditText campo2;
    private String[] hints = {
        "Nome Completo",
        "Data de nascimento",
        "CPF",
        "CNH",
        "Email",
        "Senha"
    };
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancela();
            }
        });
        btn_cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancela();
            }
        });
        btn_continuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String campo01 = campo1.getText().toString();
                String campo02 = campo2.getText().toString();

                if(campo01.isEmpty() || campo02.isEmpty()){
                    mensagens("Preencha todos os campos", view);
                } else {
                    if(contador == 0){
                        form.setNome_completo(campo01);
                        form.setData_nasc(campo02);
                        campo1.setInputType(InputType.TYPE_CLASS_NUMBER);
                        campo2.setInputType(InputType.TYPE_CLASS_NUMBER);
                        campo1.setHint(hints[2]);
                        campo2.setHint(hints[3]);
                        contador++;
                        campo1.setText("");
                        campo2.setText("");
                    } else if( contador == 1){
                        ValidaCpf validador = new ValidaCpf();
                        if (!validador.isCPF(campo01)){
                            mensagens("O CPF é invalido", view);
                        } else if (!validador.isCNH(campo02)){
                            mensagens("A CNH é invalida", view);
                        } else {
                            form.setCpf(validador.mascaraCPF(campo01));
                            form.setCnh(campo02);
                            campo1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                            campo2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            campo1.setHint(hints[4]);
                            campo2.setHint(hints[5]);
                            contador++;
                            campo1.setText("");
                            campo2.setText("");
                            btn_continuar.setText("CADASTRAR");
                        }
                    } else if( contador == 2){
                        if(!Patterns.EMAIL_ADDRESS.matcher(campo01).matches()){
                            mensagens("O email é invalido", view);
                        } else if (!campo02.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&?!@_#+-]{8,}")){
                            mensagens("A senha deve conter Letras, Numeros e caracteres especiais", view);
                        }else {
                            form.setEmail(campo01);
                            form.setSenha(campo02);
                            contador++;
                            try{
                                postLogin("/cliente/", form, view);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        //btn_continuar.setText("CADASTRAR");
                    }

                }
            }
        });
    }

    private void cancela(){
        Intent intent = new Intent(FormCadastro.this, Inicial.class);
        startActivity(intent);
    }

    private void IniciarComponentes(){
        btn_voltar = findViewById(R.id.arrow_left);
        btn_continuar = findViewById(R.id.btn_continuar_form);
        btn_cancela = findViewById(R.id.btn_cancela_form);

        campo1 = findViewById(R.id.form_text_nome);
        campo2 = findViewById(R.id.form_text_data_nasci);

        textoCima = findViewById(R.id.frase_top);
    }



    public void postLogin(String urlPath, DadosCadastro dados, View view) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(FormCadastro.this);
        String url = URL_BASE + urlPath;

        String telefone = "(55) 9" + dados.getCnh().substring(4,8) +"-"+dados.getCnh().substring(0,4);

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("email", dados.getEmail());
        jsonBody.put("nome_completo", dados.getNome_completo());
        jsonBody.put("senha", dados.getSenha());
        jsonBody.put("cpf", dados.getCpf());
        jsonBody.put("cnh", dados.getCnh());
        jsonBody.put("cep", "79300-090");
        jsonBody.put("data_nascimento", "2003-01-01");
        jsonBody.put("telefone1", telefone);
        jsonBody.put("telefone2", "");


        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btn_cancela.setText("CONTINUAR");

                        btn_continuar.setVisibility(View.GONE);
                        btn_voltar.setVisibility(View.GONE);
                        campo1.setVisibility(View.GONE); //View.VISIBLE
                        campo2.setVisibility(View.GONE); //View.VISIBLE
                        textoCima.setText("Cadastrado com sucesso");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String b = new String(error.networkResponse.data).replace("message", "").replace("\"","").replace("{:", "").replace("}","");

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
    }

    public void mensagens(String texto, View view){
        Snackbar snackbar = Snackbar.make(view, texto, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.LTGRAY);
        snackbar.setTextColor(Color.BLACK);
        snackbar.show();
    }



}