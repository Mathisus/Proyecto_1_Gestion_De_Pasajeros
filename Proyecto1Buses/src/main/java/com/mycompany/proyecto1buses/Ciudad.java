package com.mycompany.proyecto1buses;

import java.util.*;

public class Ciudad {

    private String nombre;
    private ArrayList<String> terminales;
    private HashMap<String, ArrayList<Horario>> horarios;

    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.terminales = new ArrayList<>();
        this.horarios = new HashMap<>();
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

    public void addHorario(String terminal, String hora, int cupoDisponible) {
        horarios.computeIfAbsent(terminal, k -> new ArrayList<>()).add(new Horario(hora, cupoDisponible));
    }

    public ArrayList<Horario> getHorarios(String terminal) {
        return horarios.getOrDefault(terminal, new ArrayList<>());
    }
}