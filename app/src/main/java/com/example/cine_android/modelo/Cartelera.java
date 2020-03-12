package com.example.cine_android.modelo;

public class Cartelera {

    private String hora_pelicula;
    private String fecha_pelicua;
    private  String pelicula_id;
    private  String sala_id;


    public Cartelera(){

    }

    public Cartelera(String hora_pelicula, String fecha_pelicua, String pelicula_id, String sala_id) {
        this.hora_pelicula = hora_pelicula;
        this.fecha_pelicua = fecha_pelicua;
        this.pelicula_id = pelicula_id;
        this.sala_id = sala_id;
    }

    public String getHora_pelicula() {
        return hora_pelicula;
    }

    public void setHora_pelicula(String hora_pelicula) {
        this.hora_pelicula = hora_pelicula;
    }

    public String getFecha_pelicua() {
        return fecha_pelicua;
    }

    public void setFecha_pelicua(String fecha_pelicua) {
        this.fecha_pelicua = fecha_pelicua;
    }

    public String getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(String pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public String getSala_id() {
        return sala_id;
    }

    public void setSala_id(String sala_id) {
        this.sala_id = sala_id;
    }
}
