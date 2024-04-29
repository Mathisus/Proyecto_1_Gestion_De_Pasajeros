package com.mycompany.proyecto1buses;

import java.io.IOException;
import java.util.ArrayList;


public class Cliente {
    protected String nombreUsuario;
    protected String contra;
    protected String rut;
    protected ArrayList<Boleto> boletosEnPosesion;

    public Cliente(String nombreUsuario, String rut,String contra){
        this.nombreUsuario = nombreUsuario;
        this.contra = contra;
        this.rut = rut;
        boletosEnPosesion = new ArrayList<>();
    }

    public Cliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public String getContra(){
        return contra;
    }
    
    public String getRut(){
        return rut;
    }

    //setter
    public void setNombreUsuario(String nombre){
        nombreUsuario = nombre;
    }

    public void setContra(String contra){
        this.contra = contra;
    }
    
    public void setRut(String rut) {
        this.rut = rut;
    }

}      

