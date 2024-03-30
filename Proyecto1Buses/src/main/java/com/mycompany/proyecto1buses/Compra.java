package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
public class Compra {

    private Pasajero pasajero;
    private Destino destino;
    private Terminal terminalDestino;
    private Horario horario;
    private int asiento;
    private FechaHora fechaHora;

    public Compra(Pasajero pasajero, Destino destino, Terminal terminalDestino, Horario horario, int asiento, FechaHora fechaHora) {
        this.pasajero = pasajero;
        this.destino = destino;
        this.terminalDestino = terminalDestino;
        this.horario = horario;
        this.asiento = asiento;
        this.fechaHora = fechaHora;
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

    public Terminal getTerminalDestino() {
        return terminalDestino;
    }

    public void setTerminalDestino(Terminal terminalDestino) {
        this.terminalDestino = terminalDestino;
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

}