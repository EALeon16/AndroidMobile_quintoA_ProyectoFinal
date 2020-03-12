package com.example.cine_android.modelo;

public class Persona {
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private int persona_id;
    private String direccion;
    private int cedula;

    public  Persona (){}

    public Persona(String nombre, String apellido, String email, int edad, int persona_id, String direccion, int cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.persona_id = persona_id;
        this.direccion = direccion;
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPersona_id() {
        return persona_id;
    }

    public int setPersona_id(int persona_id) {
        this.persona_id = persona_id;
        return persona_id;
    }
}
