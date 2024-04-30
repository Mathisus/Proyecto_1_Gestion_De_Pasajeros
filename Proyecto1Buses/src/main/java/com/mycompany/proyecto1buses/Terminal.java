package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
import java.util.List;
import java.util.ArrayList;

public class Terminal {
    private String nombre;
    private List<HorarioV> horarios;

    public Terminal(String nombre) {
        this.nombre = nombre;
        this.horarios = new ArrayList<>();
    }

    public void agregarHorario(HorarioV horario) {
        horarios.add(horario);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<HorarioV> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioV> horarios) {
        this.horarios = horarios;
    }
}
    

