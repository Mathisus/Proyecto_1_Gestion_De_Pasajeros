package com.mycompany.proyecto1buses;

import java.io.IOException;
import java.util.ArrayList;


public class Cliente {
    protected String nombreUsuario;
    protected String rut;
    protected String contra;
    protected ArrayList<Boleto> boletosEnPosesion;

    public Cliente(String nombreUsuario, String rut,String contra){
        this.nombreUsuario = nombreUsuario;
        this.rut = rut;
        this.contra = contra;
        boletosEnPosesion = new ArrayList<>();
    }

    /*public Cliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/ 

    //getters
    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public int getSizeBoletosEnPosesion()
    {
        return boletosEnPosesion.size();
    }
    
    public String getRut(){
        return rut;
    }
    
    public String getContra(){
        return contra;
    }
    
    //setter
    public void setNombreUsuario(String nombre){
        nombreUsuario = nombre;
    }
    
    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setContra(String contra){
        this.contra = contra;
    }    
    
    public ArrayList<String> boletosTomados() {
        ArrayList<String> nombreBoletos = new ArrayList<String>();

        for(int i = 0 ; i < boletosEnPosesion.size(); i++){
            nombreBoletos.add(boletosEnPosesion.get(i).getCiudadDestino());
        }
        return nombreBoletos;

    }
    public void agregarBoletosImportados(ArrayList<Boleto> lista) {
        //verificamos que la lista de boletos del cliente tenga boletos tomados
        if(lista.isEmpty()){
            return;
        }

        //recorremos la lista de boletos del cliente
        for(int i = 0; i < lista.size(); i++){
            Boleto boleto = lista.get(i);
            boletosEnPosesion.add(boleto);
        }
    }
    
    public ArrayList<Boleto> getBoletosEnPosesion() {
        return boletosEnPosesion;
    }

    
    
}    
/*    public boolean tomarBoleto(data terminal, String nombre) {
    Boleto boleto = terminal.buscarBoletoPorNombre(nombre);

    try {
        if (boleto == null) {
            throw new BoletoNoExisteException("El boleto no existe en el sistema");
        }

        if (boleto.getCantidad() == 0) {
            throw new SinExistenciasExcepcion("Lo sentimos, no quedan boletos disponibles");
        }

        boleto.reducirExistencias(1);
        System.out.println(boleto.getExistencias());
        boletosEnPosesion.add(boleto);

        System.out.println("Boleto tomado exitosamente.");
        return true;
    } catch (BoletoNoExisteException e) {
        System.out.println(e.getMessage());
        return false;
    } catch (SinExistenciasExcepcion e) {
        System.out.println(e.getMessage());
        return false;
    }
    }
    
    public boolean mostrarBoletosEnPosesion() {
    System.out.println("Boletos que has tomado");

    if (boletosEnPosesion.isEmpty()) {
        System.out.println("No has tomado ningún boleto");
        return false;
    } else {
        for (Boleto boleto : boletosEnPosesion) {
            System.out.println("----------------------");
            System.out.println("Nombre: " + boleto.getCiudadDestino());
            System.out.println("Destino: " + boleto.getTerminalDestino());
            System.out.println("----------------------");
        }
        return true;
    }
}

public boolean anularBoleto(Gestor terminal, String nombreBoleto) {
    Boleto boleto = terminal.buscarBoletoPorNombre(nombreBoleto);

    if(boletosEnPosesion.contains(boleto)) {
        boletosEnPosesion.remove(boleto);
        boleto.aumentarExistencias(1);
        return true;
    } else {
        System.out.println("No has tomado ese boleto, inténtelo de nuevo");
        return false;
    }
    
}
    
*/



