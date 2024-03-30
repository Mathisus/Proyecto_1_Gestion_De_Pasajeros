package com.mycompany.proyecto1buses;

import java.time.*;
/**
 *
 * @author matia
 */
public class FechaHora {
    
    private LocalDate fecha;
    private LocalTime hora;
    
    public FechaHora(LocalDate fecha, LocalTime hora) {
        this.fecha = fecha;
        this.hora = hora;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
