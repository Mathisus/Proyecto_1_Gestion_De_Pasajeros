package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
public class Boleto {

//    private String nombrePropietario;
//    private String RUT;
    private String ciudadDestino;
    private String terminalDestino;
    private String horarioDestino;
    private String valorBoleto;

    public Boleto(String ciudadDestino, String terminalDestino, String horarioDestino, String valorBoleto) {
//        this.nombrePropietario = nombrePropietario;
//        this.RUT = RUT;
        this.ciudadDestino = ciudadDestino;
        this.terminalDestino = terminalDestino;
        this.horarioDestino = horarioDestino;
        this.valorBoleto = valorBoleto;
    }
    
//     public String getNombrePropietario() {
//        return nombrePropietario;
//    }
//
//    public void setNombrePropietario(String nombrePropietario) {
//        this.nombrePropietario = nombrePropietario;
//    }

//    public String getRUT() {
//        return RUT;
//    }
//
//    public void setRUT(String RUT) {
//        this.RUT = RUT;
//    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getTerminalDestino() {
        return terminalDestino;
    }

    public void setTerminalDestino(String terminalDestino) {
        this.terminalDestino = terminalDestino;
    }

    public String getHorarioDestino() {
        return horarioDestino;
    }

    public void setHorarioDestino(String horarioDestino) {
        this.horarioDestino = horarioDestino;
    }

    public String getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(String valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

}

    

