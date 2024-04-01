

import java.util.*;

/**
 *
 * @author matia
 * 
 
public class Ciudad {
    private String nombre;
    private List<Terminal> terminales;
    
    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.terminales = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<Terminal> getTerminales() {
        return terminales;
    }
    
    public void addTerminal(Terminal terminal) {
        this.terminales.add(terminal);
    }
    
    public String toString() {
        return nombre;
    }
} /*
public class Ciudad {

    private String nombre;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}*/

