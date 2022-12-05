package com.example.topsegurosbrasilmobile;

public class DadosCadastro {
    private String data_nasc;
    private String nome_completo;
    private String email;
    private String senha;
    private String cpf;
    private String cnh;

    public String getData_nasc() {
        return data_nasc;
    }
    public void setData_nasc(String x) {
        this.data_nasc = x;
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

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCnh(){
        return cnh;
    }
    public void setCnh(String cnh){
        this.cnh = cnh;
    }
}
