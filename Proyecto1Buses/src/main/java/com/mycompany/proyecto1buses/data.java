package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class data {
    private ArrayList<CiudadV> listaCiudades;
    private Map<String, CiudadV> mapaCiudades = new HashMap<>();
    private ArrayList<Cliente> listaClientes;
    private Map<String, Cliente> mapaClientes = new HashMap<>();
    private Map<String, Boleto> mapaBoletos = new HashMap<>();
    


    public data() {
        listaClientes = new ArrayList<>();
        listaCiudades = new ArrayList<>();
    }

    //Métodos 

    // Agrega Ciudad al catálogo de ciudades
    public void agregarCiudad(CiudadV ciudad) {
        listaCiudades.add(ciudad);
        mapaCiudades.put(ciudad.getNombre(), ciudad);
    }

    // Importar de Ciudades
    public void importarCiudades(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length >= 3) {
                    // Asignación de los datos
                    String nombreCiudad = partes[0];
                    String nombreTerminal = partes[1];
                    Terminal terminal = new Terminal(nombreTerminal);
                    String[] horarios = partes[2].split(";");
                    for (String horario : horarios) {
                        String[] datosHorario = horario.trim().split(" ");
                        String hora = datosHorario[0];
                        int cupos = Integer.parseInt(datosHorario[1]);
                        String precio = datosHorario[2];
                        terminal.agregarHorario(new HorarioV(hora, cupos, precio));
                    }
                    CiudadV ciudad = mapaCiudades.get(nombreCiudad);
                    if (ciudad == null) {
                        ciudad = new CiudadV(nombreCiudad);
                        agregarCiudad(ciudad);
                    }
                    ciudad.agregarTerminal(terminal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Exportar ciudades al csv
    public void exportarCiudades(String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (CiudadV ciudad : listaCiudades) {
                for (Terminal terminal : ciudad.getTerminales()) {
                    writer.write(ciudad.getNombre() + ",");
                    writer.write(terminal.getNombre() + ",");
                    for (HorarioV horario : terminal.getHorarios()) {
                        writer.write(horario.getHora() + " " + horario.getCupos() + " " + horario.getPrecio() + ";");
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funciones propias del Gestor.

    public boolean agregarCliente(Cliente cliente) {
        if(mapaClientes.containsKey(cliente.getRut())) {
            return false;
        } else {
            mapaClientes.put(cliente.getRut(), cliente);
            listaClientes.add(cliente);
            return true;
        }
    }
    
    public boolean agregarCliente(String nombreUsuario, String contra, String rut) {
        Cliente cliente = new Cliente(nombreUsuario, contra, rut);
        return agregarCliente(cliente);
    }
    
    public void mostrarClientes() {
        System.out.println("Lista de clientes:");
        if (listaClientes.isEmpty()) {
            System.out.println("La lista de clientes está vacía.");
        } else {
            for (Cliente cliente : listaClientes) {
                System.out.println("Nombre de usuario: " + cliente.getNombreUsuario());
                System.out.println("Rut: " + cliente.getRut());
                System.out.println("----------------------");
            }
        }
    }

    public CiudadV buscarCiudadPorNombre(String nombre) {
        String nombreFormateado = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        CiudadV ciudad = mapaCiudades.get(nombreFormateado);
        if(ciudad != null){
            if (ciudad.getNombre().equalsIgnoreCase(nombreFormateado)) {
                return ciudad;
            }
        }
        return null;
    }
    
    public Cliente iniciarSesion(String nombre, String password)
    {
        try{
            if(nombre == null){
                throw new NombreInvalidoException("Nombre invalido, NO existe Cliente");
            }
            if(password == null){
                throw new ContraInvalidaException("Contraseña invalida, NO existe Cliente");
            }
            
        }catch (NombreInvalidoException i){
            System.out.println(i.getMessage());
        }catch (ContraInvalidaException k){
            System.out.println(k.getMessage());
        }
        if(mapaClientes.containsKey(nombre))
        {
            Cliente client = mapaClientes.get(nombre);

            if(client.getContra().equals(password)){
                return client;
            }
        }
        return null;
    }
    
    public boolean existeCliente(String rut)
    {
        try{
            if(rut == null){
                throw new RutInvalidoException("RUT invalido, NO existe Cliente");
            }
            
        }catch (RutInvalidoException a){
            System.out.println(a.getMessage());
            return true;
        }
        if(mapaClientes.containsKey(rut))
        {
            return true;
        }
        else{
            return false;
        }
    }
    
    public void exportarClientes(String archivo) {
    //escritor
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) { // Aquí está la corrección
        //se exporta cada cliente en el bucle
        for (Cliente cliente : listaClientes) {
            
            writer.write(cliente.getNombreUsuario() + ",");
            writer.write(cliente.getRut() + ",");
            writer.write(cliente.getContra() + ",");

            //se obtienen los nombres de los boletos tomados por el cliente
            ArrayList<Boleto> boletosCliente = cliente.getBoletosEnPosesion();

//            if(boletosCliente.size() > 0) {
//                writer.write(",");
//            }

            //se escriben en la linea de texto
            for(int i = 0 ; i < boletosCliente.size() ; i++) {
                Boleto boleto = boletosCliente.get(i);
                writer.write(boleto.getCiudadDestino() + ",");
                writer.write(boleto.getTerminalDestino() + ",");
                writer.write(boleto.getHorarioDestino() + ",");
                writer.write(boleto.getValorBoleto() + ",");

                if(boletosCliente.size() -1  == i) {
                    break;
                }
                writer.write(",");
            }

            writer.newLine();
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    public void importarClientes(String archivo) {
    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                // Asignación de los datos del cliente
                String nombreUsuario = parts[0];
                String rut = parts[1].trim();
                String contra = parts[2];

                ArrayList<Boleto> boletosPosecion = new ArrayList<>();
                if(parts.length >= 7) {
                    for (int i = 3; i < parts.length; i+=4) {
                        if(parts[i].isEmpty()) {
                            i++;
                        }
                        String ciudadBoleto = parts[i];
                        String terminalBoleto = parts[i+1];
                        String horarioBoleto = parts[i+2];
                        String precioBoleto = parts[i+3];

                        // Crear un nuevo boleto y agregarlo a la lista de boletos en posesión
                        Boleto boleto = new Boleto(ciudadBoleto, terminalBoleto, horarioBoleto, precioBoleto);
                        boletosPosecion.add(boleto);
                    }
                }
                // Crear un objeto Cliente y agregarlo a la lista y al mapa
                Cliente cliente = new Cliente(nombreUsuario, rut, contra);
                cliente.agregarBoletosImportados(boletosPosecion);
                listaClientes.add(cliente);
                mapaClientes.put(rut, cliente);
            }
        }
    } 
    catch (IOException e) {
        e.printStackTrace();
    }    
}
    
    public ArrayList<String> getCiudades()
    {
        ArrayList<String> ciudades = new ArrayList<String>();
        for(CiudadV ciudad : listaCiudades)
        {
            ciudades.add(ciudad.getNombre());
        }
        return ciudades;
    }
    
    public List<Terminal> getTerminales(String nombreCiudad) {
        CiudadV ciudad = mapaCiudades.get(nombreCiudad);
        if (ciudad != null) {
            return ciudad.getTerminales();
        }
        return new ArrayList<>(); // Retorna una lista vacía si la ciudad no se encuentra
    }
    
    public Terminal buscarTerminalPorNombre(String nombreTerminal) {
        for (CiudadV ciudad : listaCiudades) {
            for (Terminal terminal : ciudad.getTerminales()) {
                if (terminal.getNombre().equals(nombreTerminal)) {
                    return terminal;
                }
            }
        }
        return null; // Retorna null si el terminal no se encuentra
    }
    
    public HorarioV buscarHorarioPorHora(String horaBuscada) {
        for (CiudadV ciudad : listaCiudades) {
            for (Terminal terminal : ciudad.getTerminales()) {
                for (HorarioV horario : terminal.getHorarios()) {
                    if (horario.getHora().equals(horaBuscada)) {
                        return horario;
                    }
                }
            }
        }
        return null; // Retorna null si el horario no se encuentra
    }
}

