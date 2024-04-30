package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
import java.io.*;
import java.util.*;

public class CiudadV {
    private String nombre;
    private List<Terminal> terminales;

    public CiudadV(String nombre) {
        this.nombre = nombre;
        this.terminales = new ArrayList<>();
    }

    public void agregarTerminal(Terminal terminal) {
        terminales.add(terminal);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Terminal> getTerminales() {
        return terminales;
    }

    public void setTerminales(List<Terminal> terminales) {
        this.terminales = terminales;
    }
    
    
}