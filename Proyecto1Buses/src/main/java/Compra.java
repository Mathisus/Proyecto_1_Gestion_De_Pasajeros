

/**
 *
 * @author matia
 */
public class Compra {

    private Pasajero pasajero;
    private Destino destino;
    private Horario horario;
    private int asiento;
    private FechaHora fechaHora;
    private int precioViaje;

    public Compra(Pasajero pasajero, Destino destino, Horario horario, int asiento, FechaHora fechaHora, int precioViaje) {
        this.pasajero = pasajero;
        this.destino = destino;
        this.horario = horario;
        this.asiento = asiento;
        this.fechaHora = fechaHora;
        this.precioViaje = precioViaje;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public FechaHora getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(FechaHora fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getPrecioViaje() {
        return precioViaje;
    }

    public void setPrecioViaje(int precioViaje) {
        this.precioViaje = precioViaje;
    }

    public String toString() {
        return "**Compra**\n" +
                "Pasajero: " + pasajero + "\n" +
                "Destino: " + destino + "\n" +
                "Horario: " + horario + "\n" +
                "Asiento: " + asiento + "\n" +
                "Fecha y hora: " + fechaHora + "\n" +
                "Precio: $" + precioViaje;
    }
}

