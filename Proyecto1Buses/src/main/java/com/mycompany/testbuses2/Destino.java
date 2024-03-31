package com.mycompany.testbuses2;

import java.util.*;

public class Destino {

    private String nombre;
    private ArrayList<String> terminales;
    private ArrayList<Horario> horarios;

    public Destino(String nombre) {
        this.nombre = nombre;
        this.terminales = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void addTerminal(String nombreTerminal) {
        terminales.add(nombreTerminal);
    }

    public ArrayList<String> getTerminales() {
        return terminales;
    }

    public void addHorario(String hora, int cupoDisponible) {
        horarios.add(new Horario(hora, cupoDisponible));
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }
}
