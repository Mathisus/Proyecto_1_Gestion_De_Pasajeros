package com.mycompany.proyecto1buses;

public class Compra {

    private String nombre;
    private String apellido;
    private String rut;
    private Destino destino;
    private Horario horario;

    public Compra(String nombre, String apellido, String rut, Destino destino, Horario horario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.destino = destino;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public Destino getDestino() {
        return destino;
    }

    public Horario getHorario() {
        return horario;
    }
}
