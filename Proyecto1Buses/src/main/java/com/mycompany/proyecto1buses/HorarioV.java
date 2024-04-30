package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
public class HorarioV {
    private String hora;
    private int cupos;
    private int precio;

    public HorarioV(String hora, int cupos, int precio) {
        this.hora = hora;
        this.cupos = cupos;
        this.precio = precio;
    }

    // Getters y setters
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}