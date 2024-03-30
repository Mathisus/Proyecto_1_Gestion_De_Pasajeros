package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
public class Destino {
    
    private Ciudad ciudad;
    private Terminal terminal;
    
    public Destino(Ciudad ciudad, Terminal terminal) {
        this.ciudad = ciudad;
        this.terminal = terminal;
    }
    
    public Ciudad getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    public Terminal getTerminal() {
        return terminal;
    }
    
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
    
    public String toString() {
        return ciudad.getNombre() + " (" + terminal.getNombre() + ")";
    }
    
}
