package com.mycompany.proyecto1buses;

import com.mycompany.ventanas.portada;
import java.io.IOException;
import java.nio.file.Paths;

public class Proyecto1Buses {
    
  //private static final ArrayList<Ciudad> ciudades = new ArrayList<>();

  public static void main(String[] args) throws IOException{
    
    String currentFolder = Paths.get("").toAbsolutePath().toString();
    
    String txtCiudades = currentFolder + "/src/main/java/com/mycompany/data/ciudades.txt";
    String csvClientes = currentFolder + "/src/main/java/com/mycompany/data/Clientes.csv";
    
    
    data db = new data();
    
    db.importarCiudades(txtCiudades);
    db.importarClientes(csvClientes);
      
    Cliente cliente = new Cliente("default", "rutDefault", "contraDefault");
    
    

    portada port = new portada(db,cliente);
    port.setVisible(true);
    port.setLocationRelativeTo(null);
  }
}
