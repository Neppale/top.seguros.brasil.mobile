package com.example.topsegurosbrasilmobile;

public class Apolice {

    private String data_inicio;
    private String data_fim;
    private String status;

    private int id_apolice;
    private int id_cobertura;
    private int id_usuario;
    private int id_cliente;
    private int id_veiculo;

    private double premio;
    private double indenizacao;

    public String getData_inicio(){
        return data_inicio;
    }
    public void setData_inicio(String nome){
        this.data_inicio = nome;
    }

    public String getData_fim(){
        return data_fim;
    }
    public void setData_fim(String email){
        this.data_fim = email;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int x) {
        this.id_usuario = x;
    }

    public int getId_apolice() {
        return id_apolice;
    }
    public void setId_apolice(int x) {
        this.id_apolice = x;
    }

    public int getId_cobertura() {
        return id_cobertura;
    }
    public void setId_cobertura(int x) {
        this.id_cobertura = x;
    }

    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int x) {
        this.id_cliente = x;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }
    public void setId_veiculo(int x) {
        this.id_veiculo = x;
    }

    public double getPremio() {
        return premio;
    }
    public void setPremio(double x) {
        this.premio = x;
    }

    public double getIndenizacao() {
        return indenizacao;
    }
    public void setIndenizacao(double x) {
        this.indenizacao = x;
    }

}
