package com.mycompany.proyecto1buses;

public class Compra {

    private String nombre;
    private String apellido;
    private String rut;
    private Ciudad ciudad;
    private String terminal;
    private Horario horario;

    public Compra(String nombre, String apellido, String rut, Ciudad ciudad, String terminal, Horario horario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.ciudad = ciudad;
        this.terminal = terminal;
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

    public Ciudad getDestino() {
        return ciudad;
    }

    public String getTerminal() {
        return terminal;
    }

    public Horario getHorario() {
        return horario;
    }
}