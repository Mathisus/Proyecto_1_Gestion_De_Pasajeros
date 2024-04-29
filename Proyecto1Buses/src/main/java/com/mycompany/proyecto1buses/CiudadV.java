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

    public static List<CiudadV> leerCiudadesDesdeArchivo(String nombreArchivo) {
        List<CiudadV> ciudades = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            CiudadV ciudadActual = null;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (!partes[0].isEmpty()) {
                    if (ciudadActual != null) {
                        ciudades.add(ciudadActual);
                    }
                    ciudadActual = new CiudadV(partes[0]);
                }
                Terminal terminal = new Terminal(partes[1]);
                String[] horariosYCupos = partes[2].split(";");
                for (String horarioYCupo : horariosYCupos) {
                    String[] datos = horarioYCupo.trim().split(" ");
                    Horario horario = new Horario(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
                    terminal.agregarHorario(horario);
                }
                ciudadActual.agregarTerminal(terminal);
            }
            if (ciudadActual != null) {
                ciudades.add(ciudadActual);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ciudades;
    }
}

class Terminal {
    private String nombre;
    private List<Horario> horarios;

    public Terminal(String nombre) {
        this.nombre = nombre;
        this.horarios = new ArrayList<>();
    }

    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    // Getters y setters
}

class Horario {
    private String hora;
    private int cupos;
    private int precio;

    public Horario(String hora, int cupos, int precio) {
        this.hora = hora;
        this.cupos = cupos;
        this.precio = precio;
    }

    // Getters y setters
}

