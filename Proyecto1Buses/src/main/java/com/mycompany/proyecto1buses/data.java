package com.mycompany.proyecto1buses;

/**
 *
 * @author matia
 */
import java.util.ArrayList;
import java.util.Map;
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
    Map<String, Boleto> mapaBoletos = new HashMap<>();


    public data() {
        listaClientes = new ArrayList<>();
        listaCiudades = new ArrayList<>();
    }

    // Métodos 

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
                        int precio = Integer.parseInt(datosHorario[2]);
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
        if(mapaClientes.containsKey(cliente.getNombreUsuario())) {
            return false;
        } else {
            mapaClientes.put(cliente.getNombreUsuario(), cliente);
            listaClientes.add(cliente);
            return true;
        }
    }
    
    public boolean agregarCliente(String nombreUsuario, String contra, String rut) {
        Cliente cliente = new Cliente(nombreUsuario, contra, rut);
        return agregarCliente(cliente);
    }
    
    public boolean eliminarCliente(String key) {
        Cliente clienteAEliminar = null;
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombreUsuario().equals(key)) {
                clienteAEliminar = cliente;
                break;
            }
        }
        if (clienteAEliminar != null) {
            listaClientes.remove(clienteAEliminar);
            mapaClientes.remove(key);
            return true;
        } else {
            return false; 
        }
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
    
    public boolean mostrarCiudades() {
        System.out.println("Catálogo de ciudades del terminal de buses:");
        if (listaCiudades.isEmpty()) {
            System.out.println("No hay ciudades por el momento.");
            return false;
        } else {
            for (CiudadV ciudad : listaCiudades) {
                System.out.println("----------------------");
                System.out.println("Nombre: " + ciudad.getNombre());
                for (Terminal terminal : ciudad.getTerminales()) {
                    System.out.println("Terminal: " + terminal.getNombre());
                    for (HorarioV horario : terminal.getHorarios()) {
                        System.out.println("Horario: " + horario.getHora() + ", Cupos: " + horario.getCupos() + ", Precio: " + horario.getPrecio());
                    }
                }
                System.out.println("----------------------");
            }
            return true;
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
        if(mapaClientes.containsKey(nombre))
        {
            Cliente client = mapaClientes.get(nombre);

            if(client.getContra().equals(password)){
                return client;
            }
        }
        return null;
    }
    
    public boolean existeCliente(String nombre)
    {
        if(mapaClientes.containsKey(nombre))
        {
            return true;
        }
        else{
            return false;
        }
    }
    
    public void exportarClientes(String archivo) {
    //escritor
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) { // Aquí está la corrección
        //se exporta cada cliente en el bucle
        for (Cliente cliente : listaClientes) {
            writer.write(cliente.getNombreUsuario() + ",");
            writer.write(cliente.getRut() + ",");
            writer.write(cliente.getContra() + ",");

            //se obtienen los nombres de los boletos tomados por el cliente
            ArrayList<String> boletosCliente = cliente.boletosTomados();

            if(boletosCliente.size() > 0) {
                writer.write(",");
            }

            //se escriben en la linea de texto
            for(int i = 0 ; i < boletosCliente.size() ; i++) {
                writer.write(boletosCliente.get(i));

                if(boletosCliente.size() -1  == i) {
                    break;
                }
                writer.write(",");
            }

            writer.newLine();
        }
        
        listaClientes.clear();
        mapaClientes.clear();
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
                    if(parts.length >= 4) {

                        for (int i = 3; i < parts.length; i++) {
                            String nombreBoleto = parts[i];
                            // Buscar el boleto por nombre y agregarlo a la lista de boletos en posesión
                            Boleto boleto = mapaBoletos.get(nombreBoleto);
                            if (boleto != null) {
                                boletosPosecion.add(boleto);
                            }
                        }

                    }
                    // Crear un objeto Cliente y agregarlo a la lista y al mapa
                    Cliente cliente = new Cliente(nombreUsuario, rut, contra);
                    cliente.agregarBoletosImportados(boletosPosecion);
                    listaClientes.add(cliente);
                    mapaClientes.put(nombreUsuario, cliente);
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
    
   /* public ArrayList<String> obtenerTerminales()
    {
        Ciudad ciudad = listaCiudades;
        ArrayList<String> terminales = new ArrayList<String>();
        for(Terminal terminal : listaCiudades.getTerminales)
        {
            ciudades.add(ciudad.getNombre());
        }
        return ciudades;
    }*/
}

