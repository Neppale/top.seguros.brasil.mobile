package com.example.topsegurosbrasilmobile;

public class User {
    private int id_usuario;
    private String nome_completo;
    private String email;
    private String tipo;
    private Boolean status;
    private String token;


    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int x) {
        this.id_usuario = x;
    }

    public String getNome_completo(){
        return nome_completo;
    }
    public void setNome_completo(String nome){
        this.nome_completo = nome;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getToken(){
        return token;
    }
    public void setToken(String token){
        this.token = token;
    }

    public Boolean getStatus(){
        return status;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }
}
