package com.mycompany.testbuses2;

import java.util.ArrayList;
import java.util.Scanner;

public class TestBuses2 {

    private static final String CIUDAD_ORIGEN = "Valparaíso";
    private static final ArrayList<Destino> destinos = new ArrayList<>();

    public static void main(String[] args) {
        cargarDestinos();

        Scanner scanner = new Scanner(System.in);

        //Aqui mostramos las opciones de destino
        System.out.println("Seleccione un destino:");
        for (int i = 0; i < destinos.size(); i++) {
            System.out.println((i + 1) + ". " + destinos.get(i).getNombre());
        }

        int opcionDestino = scanner.nextInt();
        Destino destinoElegido = destinos.get(opcionDestino - 1);

        //Aqui las opciones de horario
        System.out.println("Seleccione un horario para " + destinoElegido.getNombre() + ":");
        for (Horario horario : destinoElegido.getHorarios()) {
            System.out.println(horario.getHora() + " - " + (horario.getCupoDisponible() > 0 ? "Disponible (" + horario.getCupoDisponible() + " cupos)" : "Completo"));
        }

        int opcionHorario = scanner.nextInt();
        Horario horarioElegido = destinoElegido.getHorarios().get(opcionHorario - 1);

        // Validamos los cupos disponibles
        if (horarioElegido.getCupoDisponible() <= 0) {
            System.out.println("Lo sentimos, no hay cupos disponibles para ese horario.");
            return;
        }

        // Se registra la compra
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.next();

        System.out.println("Ingrese su apellido:");
        String apellido = scanner.next();

        System.out.println("Ingrese su RUT:");
        String rut = scanner.next();

        Compra compra = new Compra(nombre, apellido, rut, destinoElegido, horarioElegido);
        System.out.println("Compra registrada exitosamente para " + compra.getDestino().getNombre() + " a las " + compra.getHorario().getHora());

        //Descontamos el cupo
        horarioElegido.setCupoDisponible(horarioElegido.getCupoDisponible() - 1);

        System.out.println("Cupos disponibles para " + destinoElegido.getNombre() + " a las " + horarioElegido.getHora() + ": " + horarioElegido.getCupoDisponible());
    }

    private static void cargarDestinos() {
        // Aqui agregamos los destinos y sus terminales anidados aquí

        Destino destino1 = new Destino("Santiago");
        destino1.addTerminal("Terminal Alameda");
        destino1.addTerminal("Terminal Sur");
        destino1.addHorario("08:00", 30);
        destino1.addHorario("12:00", 30);
        destino1.addHorario("18:00", 30);

        destinos.add(destino1);

        Destino destino2 = new Destino("Concepción");
        destino2.addTerminal("Terminal Collao");
        destino2.addTerminal("Terminal Biobío");
        destino2.addHorario("09:00", 30);
        destino2.addHorario("14:00", 30);
        destino2.addHorario("19:00", 30);

        destinos.add(destino2);
    }
}



