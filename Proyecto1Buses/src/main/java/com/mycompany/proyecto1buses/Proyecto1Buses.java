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
      Ciudad ciudadElegida = null;
      switch (opcion) {

        case 1:
          //Seleccionamos ciudad destino
          System.out.println("Seleccione el método para elegir ciudad destino:");
          System.out.println("1. Por lista");
          System.out.println("2. Por nombre");
          System.out.println("3. Por código");
          int opcionMetodo = scanner.nextInt();
          switch (opcionMetodo) {
            case 1:
              ciudadElegida = seleccionarCiudadDestino(scanner);
              break;
            case 2:
              System.out.println("Ingrese el nombre de la ciudad: ");
              String nombreCiudad = scanner.next();
              ciudadElegida = seleccionarCiudadDestino(nombreCiudad);
              break;
            case 3:
              System.out.println("Ingrese el código de la ciudad: ");
              int codigoCiudad = scanner.nextInt();
              ciudadElegida = seleccionarCiudadDestino(codigoCiudad);
              break;
          }

          //Seleccionamos la terminal destino
          System.out.println("Seleccione un terminal en " + ciudadElegida.getNombre() + ":");
          for (int i = 0; i < ciudadElegida.getTerminales().size(); i++) {
            System.out.println((i + 1) + ". " + ciudadElegida.getTerminales().get(i));
          }
          int opcionTerminal = scanner.nextInt();
          String terminalElegido = ciudadElegida.getTerminales().get(opcionTerminal - 1);

          //Seleccionamos horario
          System.out.println("Seleccione un horario para " + ciudadElegida.getNombre() + " (" + terminalElegido + "):");
          for (Horario horario : ciudadElegida.getHorarios(terminalElegido)) {
            System.out.println(horario.getHora() + " - " + (horario.getCupoDisponible() > 0 ? "Disponible (" + horario.getCupoDisponible() + " cupos)" : "Completo"));
          }
          int opcionHorario = scanner.nextInt();
          Horario horarioElegido = ciudadElegida.getHorarios(terminalElegido).get(opcionHorario - 1);

          //Validamos si es que hay un cupo disponible
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

          //descontamos un cupo
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
    //agregamos ciudades, terminales y horario

    Ciudad ciudad1 = new Ciudad("Santiago");
    ciudad1.addCodigo(1);
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
    ciudad2.addCodigo(2);
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

  private static Ciudad seleccionarCiudadDestino(Scanner scanner) {
    //Mostramos las opciones de ciudades
    for (int i = 0; i < ciudades.size(); i++) {
      System.out.println((i + 1) + ". " + ciudades.get(i).getNombre());
    }

    int opcionCiudad = scanner.nextInt();
    return ciudades.get(opcionCiudad - 1);
  }

  private static Ciudad seleccionarCiudadDestino(String nombreCiudad) {
    // Buscamos ciudad por nombre
    for (Ciudad ciudad : ciudades) {
      if (ciudad.getNombre().equalsIgnoreCase(nombreCiudad)) {
        return ciudad;
      }
    }

    return null;
  }

  private static Ciudad seleccionarCiudadDestino(int codigoCiudad) {
    // Buscamos ciudad por código
    for (Ciudad ciudad : ciudades) {
      if (ciudad.getCodigo() == codigoCiudad) {
        return ciudad;
      }
    }

    return null;
  }

  private static Compra comprarBoleto(Ciudad ciudadElegida, String terminalElegido, Horario horarioElegido) {
    // Registrar la compra
    Scanner scanner = new Scanner(System.in);
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

    return compra;
  }

  private static Compra comprarBoleto(String nombreCiudad, String terminalElegido, String horaElegida) {
    // Buscar ciudad por nombre
    Ciudad ciudadElegida = seleccionarCiudadDestino(nombreCiudad);

    // Buscar horario por hora
    Horario horarioElegido = null;
    for (Horario horario : ciudadElegida.getHorarios(terminalElegido)) {
      if (horario.getHora().equals(horaElegida)) {
        horarioElegido = horario;
        break;
      }
    }

    if (horarioElegido == null) {
      System.out.println("Lo sentimos, no hay un horario disponible para esa hora en " + ciudadElegida.getNombre() + " (" + terminalElegido + ").");
      return null;
    }

    return comprarBoleto(ciudadElegida, terminalElegido, horarioElegido);
  }
}
