

import java.time.*;
/**
 *
 * @author matia
 */
public class FechaHora {

    static FechaHora now() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
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
