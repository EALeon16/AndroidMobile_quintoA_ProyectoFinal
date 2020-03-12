package com.example.cine_android.modelo;

public class Comprobante {
    private int cantN;
    private int cantE;
    private int cantTotal;
    private double precio;
    private String pelicula;
    private String fecha;
    private String hora;
    private String sala;

    public Comprobante(){}

    public Comprobante(int cantN, int cantE, int cantTotal, double precio, String pelicula, String fecha, String hora, String sala) {
        this.cantN = cantN;
        this.cantE = cantE;
        this.cantTotal = cantTotal;
        this.precio = precio;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.sala = sala;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public int getCantN() {
        return cantN;
    }

    public void setCantN(int cantN) {
        this.cantN = cantN;
    }

    public int getCantE() {
        return cantE;
    }

    public void setCantE(int cantE) {
        this.cantE = cantE;
    }

    public int getCantTotal() {
        return cantTotal;
    }

    public void setCantTotal(int cantTotal) {
        this.cantTotal = cantTotal;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
