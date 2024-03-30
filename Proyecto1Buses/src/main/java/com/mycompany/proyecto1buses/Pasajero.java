package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
public class Pasajero {

    private String nombre;
    private String apellido;
    private String rut;
    private String asiento;

    public Pasajero(String nombre, String apellido, String rut, String asiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.asiento = asiento;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n"
                + "Apellido: " + apellido + "\n"
                + "RUT: " + rut + "\n"
                + "Asiento: " + asiento;
    }

}
