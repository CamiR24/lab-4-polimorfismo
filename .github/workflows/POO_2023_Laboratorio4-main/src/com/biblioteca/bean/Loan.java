package com.biblioteca.bean;

public class Loan {
    private int diasEntrega; 
    private String horario;
    private String sucursal;
    private String direccion;
    private String pasarHoras;

    public Loan(int diasEntrega, String horario, String sucursal, String direccion, String pasarHoras){
        this.diasEntrega = diasEntrega; 
        this.horario = horario;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.pasarHoras = pasarHoras;
    }

    public void setDiasEntrega(int diasEntrega) {
        this.diasEntrega = diasEntrega;
    }

    public int getDiasEntrega() {
        return diasEntrega;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setPasarHoras(String pasarHoras) {
        this.pasarHoras = pasarHoras;
    }

    public String getPasarHoras() {
        return pasarHoras;
    }

    @Override
    public String toString() {
        return "Dias de entrega: " + diasEntrega + ", Horario de entrega: " + horario + ", Sucursal: " + sucursal + ", Direccion: " + direccion + ", Pasara por el prestamo a las: " + pasarHoras + " horas";
    }
}
