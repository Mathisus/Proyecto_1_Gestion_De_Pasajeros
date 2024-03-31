package com.mycompany.proyecto1buses;


public class Horario {
    private String hora;
    private int cupoDisponible;
    public Horario(String hora, int cupoDisponible) {
    this.hora = hora;
    this.cupoDisponible = cupoDisponible;
    }

    public String getHora() {
        return hora;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }
}
