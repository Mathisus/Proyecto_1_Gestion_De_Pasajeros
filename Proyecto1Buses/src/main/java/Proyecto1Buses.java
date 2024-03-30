

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.*;

public class Proyecto1Buses {
    
    private static List<Ciudad> ciudades = new ArrayList<>();
    private static List<Terminal> terminales = new ArrayList<>();
    private static List<Viaje> viajes = new ArrayList<>();
    private static List<Pasajero> pasajeros = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {
        // Leemos archivo "ciudadesDestinos.txt"
        leerCiudadesTerminalesHorarios("CiudadesDestino.txt");

        Scanner scanner = new Scanner(System.in);

        //Mostramos el menu
        int opcion;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Comprar boleto");
            System.out.println("2. Salir");
            System.out.print("Ingrese su opcion: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    comprarBoleto(scanner);
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 2);

        scanner.close();
    }

    private static void comprarBoleto(Scanner scanner) {
        //seleccionar una ciudad de destino
        System.out.println("Seleccione ciudad de destino:");
        for (Ciudad ciudad : ciudades) {
            System.out.println(" - " + ciudad.getNombre());
        }
        String nombreCiudadDestino = scanner.nextLine();

        Ciudad ciudadDestino = buscarCiudad(nombreCiudadDestino);
        if (ciudadDestino == null) {
            System.out.println("Ciudad no válida.");
            return;
        }

        // Seleccionar el terminal de destino
        System.out.println("Seleccione terminal de destino:");
        List<Terminal> terminalesDestino = obtenerTerminales(ciudadDestino);
        for (Terminal terminal : terminalesDestino) {
            System.out.println(" - " + terminal.getNombre());
        }
        String nombreTerminalDestino = scanner.nextLine();

        Terminal terminalDestino = buscarTerminal(terminalesDestino, nombreTerminalDestino);
        if (terminalDestino == null) {
            System.out.println("Terminal no válida.");
            return;
        }

        // seleccionar un horario
        System.out.println("Seleccione horario de viaje:");
        List<Horario> horariosDisponibles = obtenerHorariosDisponibles(terminalDestino);
        for (Horario horario : horariosDisponibles) {
            System.out.println(" - " + horario.getHora() + " (" + horario.getAsientosDisponibles() + " cupos disponibles)");
        }
        String horaStr = scanner.nextLine();

        Horario horarioSeleccionado = buscarHorario(horariosDisponibles, horaStr);
        if (horarioSeleccionado == null) {
            System.out.println("Horario no válido.");
            return;
        }

        // Seleccionar el asiento 
        System.out.println("Seleccione asiento (1-" + horarioSeleccionado.getAsientosDisponibles() + "):");
        int asiento = scanner.nextInt();

        if (asiento < 1 || asiento > horarioSeleccionado.getAsientosDisponibles()) {
            System.out.println("Asiento no válido.");
            return;
        }

        // Obtener precio del viaje
        int precioViaje = obtenerPrecioViaje(terminalDestino);

        //Ingresar datos personales
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su apellido:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese su RUT:");
        String rut = scanner.nextLine();

        // Buscar o crear pasajero
        Pasajero pasajero = buscarPasajero(rut);
        if (pasajero == null) {
            pasajero = new Pasajero(nombre, apellido, rut);
        }

        //Registramos la compra
        registrarCompra(pasajero, ciudadDestino, terminalDestino, horarioSeleccionado, asiento, precioViaje);

        // Mostramos el resumen de la compra
        System.out.println("**Resumen de compra**");
        System.out.println("Destino: " + ciudadDestino.getNombre() + " (" + terminalDestino.getNombre() + ")");
        System.out.println("Fecha y hora: " + FechaHora.now());
        System.out.println("Horario: " + horarioSeleccionado.getHora());
        System.out.println("Asiento: " + asiento);
        System.out.println("Precio: $" + precioViaje);
        System.out.println("**Compra exitosa!**");
    }

    private static int obtenerPrecioViaje(Terminal terminalDestino) {
        // Leer archivo "CiudadesDestino.txt"
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("ciudadesDestino.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        //buscamos la linea que corresponde a "terminal"
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            String[] partes = linea.split(",");

            if (partes.length > 0 && partes[0].equals(terminalDestino.getNombre())) {
                // El precio es el último valor de la línea
                return Integer.parseInt(partes[partes.length - 1]);
            }
        }

        //Si no se encuentra la terminal, retornamos -1
        return -1;
    }
    
    private static void registrarCompra(Pasajero pasajero, Ciudad ciudadDestino, Terminal terminalDestino, Horario horarioSeleccionado, int asiento, int precioViaje) {
        //Se debe actualizar la cantidad de asientos disponibles
        horarioSeleccionado.setAsientosDisponibles(horarioSeleccionado.getAsientosDisponibles() - 1);

        //Creamos el objeto de compra

        Compra compra = new Compra(pasajero, new Destino(ciudadDestino,terminalDestino), horarioSeleccionado, asiento, FechaHora.now(), precioViaje);

        // Registrar la compra en un archivo
        try (FileWriter fileWriter = new FileWriter("compras.txt", true)) {
            fileWriter.write(compra.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al registrar la compra.");
        }

        // Simular el pago
        simularPago(precioViaje);
    }

    private static void simularPago(int precioViaje) {
        System.out.println("Precio del viaje: $" + precioViaje);
        System.out.println("Simulando pago...");
        // Aqui simulamos la interacción con un sistema de pago
        System.out.println("Pago exitoso!");
    }

    private static Ciudad buscarCiudad(String nombre) {
        for (Ciudad ciudad : ciudades) {
            if (ciudad.getNombre().equals(nombre)) {
                return ciudad;
            }
        }
        return null;
    }

    private static List<Terminal> obtenerTerminales(Ciudad ciudad) {
        List<Terminal> terminalesCiudad = new ArrayList<>();
        for (Terminal terminal : terminales) {
            if (terminal.getCiudad().equals(ciudad)) {
                terminalesCiudad.add(terminal);
            }
        }
        return terminalesCiudad;
    }

    private static List<Horario> obtenerHorariosDisponibles(Terminal terminal) {
        List<Horario> horariosDisponibles = new ArrayList<>();
        for (Viaje viaje : viajes) {
            if (viaje.getTerminal().equals(terminal) && viaje.getAsientosDisponibles() > 0) {
                horariosDisponibles.add(viaje.getHorario());
            }
        }
        return horariosDisponibles;
    }

    private static Horario buscarHorario(List<Horario> horarios, String hora) {
        for (Horario horario : horarios) {
            if (horario.getHora().equals(hora)) {
                return horario;
            }
        }
        return null;
    }

    private static Pasajero buscarPasajero(String rut) {
    // Implementar búsqueda por RUT en la lista de pasajeros
    for (Pasajero pasajero : pasajeros) {
        if (pasajero.getRut().equals(rut)) {
            return pasajero;
        }
    }
    return null;
    }
    
    private static Terminal buscarTerminal(List<Terminal> terminales, String nombre) {
    for (Terminal terminal : terminales) {
        if (terminal.getNombre().equals(nombre)) {
            return terminal;
        }
    }
    return null;
}

    private static void leerCiudadesTerminalesHorarios(String archivo) throws FileNotFoundException {
    try (Scanner scanner = new Scanner(new File(archivo))) {
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            String[] partes = linea.split(",");

            // Crear objetos Ciudad, Terminal y Horario
            Ciudad ciudad = new Ciudad(partes[0]);
            Terminal terminal = new Terminal(partes[1], ciudad);
            Horario horario = new Horario(partes[2], Integer.parseInt(partes[3]));

            // Agregar objetos a las listas
            ciudades.add(ciudad);
            terminales.add(terminal);
            viajes.add(new Viaje(terminal, horario));
        }
    }
    }
}
   
