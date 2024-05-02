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
