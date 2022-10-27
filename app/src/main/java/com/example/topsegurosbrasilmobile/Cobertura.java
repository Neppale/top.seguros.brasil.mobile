package com.example.topsegurosbrasilmobile;

public class Cobertura {

    private String nome;
    private String descricao;

    private boolean status;
    private int id_cobertura;

    private double valor;
    private double taxa_indenizacao;

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }

    public int getId_cobertura() {
        return id_cobertura;
    }
    public void setId_cobertura(int x) {
        this.id_cobertura = x;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double x) {
        this.valor = x;
    }

    public double getTaxa_indenizacao() {
        return taxa_indenizacao;
    }
    public void setTaxa_indenizacao(double x) {
        this.taxa_indenizacao = x;
    }

}


