package com.mycompany.proyecto1buses;

import java.util.ArrayList;
import java.util.Scanner;

public class Proyecto1Buses {

    private static final String CIUDAD_ORIGEN = "Valparaíso";
    private static final ArrayList<Ciudad> ciudades = new ArrayList<>();

    public static void main(String[] args) {
        cargarCiudades();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\nMenú:");
            System.out.println("1. Comprar boleto");
            System.out.println("2. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            switch (opcion) {

                case 1:
                    //Seleccionar ciudad destino
                    System.out.println("Seleccione una ciudad destino:");
                    for (int i = 0; i < ciudades.size(); i++) {
                        System.out.println((i + 1) + ". " + ciudades.get(i).getNombre());
                    }
                    int opcionCiudad = scanner.nextInt();
                    Ciudad ciudadElegida = ciudades.get(opcionCiudad - 1);

                    //Seleccionar terminal destino
                    System.out.println("Seleccione un terminal en " + ciudadElegida.getNombre() + ":");
                    for (int i = 0; i < ciudadElegida.getTerminales().size(); i++) {
                        System.out.println((i + 1) + ". " + ciudadElegida.getTerminales().get(i));
                    }
                    int opcionTerminal = scanner.nextInt();
                    String terminalElegido = ciudadElegida.getTerminales().get(opcionTerminal - 1);

                    // Seleccionar horario
                    System.out.println("Seleccione un horario para " + ciudadElegida.getNombre() + " (" + terminalElegido + "):");
                    for (Horario horario : ciudadElegida.getHorarios(terminalElegido)) {
                        System.out.println(horario.getHora() + " - " + (horario.getCupoDisponible() > 0 ? "Disponible (" + horario.getCupoDisponible() + " cupos)" : "Completo"));
                    }
                    int opcionHorario = scanner.nextInt();
                    Horario horarioElegido = ciudadElegida.getHorarios(terminalElegido).get(opcionHorario - 1);

                    // Validamos si es que hay un cupo disponible
                    if (horarioElegido.getCupoDisponible() <= 0) {
                        System.out.println("Lo sentimos, no hay cupos disponibles para ese horario.");
                        return;
                    }

                    //registramos la compra
                    System.out.println("Ingrese su nombre:");
                    String nombre = scanner.next();

                    System.out.println("Ingrese su apellido:");
                    String apellido = scanner.next();

                    System.out.println("Ingrese su RUT:");
                    String rut = scanner.next();

                    Compra compra = new Compra(nombre, apellido, rut, ciudadElegida, terminalElegido, horarioElegido);
                    System.out.println("Compra registrada exitosamente para " + compra.getDestino().getNombre() + " (" + compra.getTerminal() + ") a las " + compra.getHorario().getHora());

                    // Descontamos un cupo
                    horarioElegido.setCupoDisponible(horarioElegido.getCupoDisponible() - 1);

                    System.out.println("Cupos disponibles para " + ciudadElegida.getNombre() + " (" + terminalElegido + ") a las " + horarioElegido.getHora() + ": " + horarioElegido.getCupoDisponible());

                    break;

                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 2);
        scanner.close();
    }

    private static void cargarCiudades() {
        //agregamos ciudades, terminales y horarios

        Ciudad ciudad1 = new Ciudad("Santiago");
        ciudad1.addTerminal("Terminal Alameda");
        ciudad1.addHorario("Terminal Alameda", "08:00", 30);
        ciudad1.addHorario("Terminal Alameda", "12:00", 30);
        ciudad1.addHorario("Terminal Alameda", "18:00", 30);
        ciudad1.addTerminal("Terminal Sur");
        ciudad1.addHorario("Terminal Sur", "09:00", 30);
        ciudad1.addHorario("Terminal Sur", "14:00", 30);
        ciudad1.addHorario("Terminal Sur", "19:00", 30);

        ciudades.add(ciudad1);

        Ciudad ciudad2 = new Ciudad("Concepción");
        ciudad2.addTerminal("Terminal Collao");
        ciudad2.addHorario("Terminal Collao", "09:00", 30);
        ciudad2.addHorario("Terminal Collao", "14:00", 30);
        ciudad2.addHorario("Terminal Collao", "19:00", 30);
        ciudad2.addTerminal("Terminal Biobío");
        ciudad2.addHorario("Terminal Biobío", "10:00", 30);
        ciudad2.addHorario("Terminal Biobío", "15:00", 30);
        ciudad2.addHorario("Terminal Biobío", "20:00", 30);

        ciudades.add(ciudad2);
            }
        }

