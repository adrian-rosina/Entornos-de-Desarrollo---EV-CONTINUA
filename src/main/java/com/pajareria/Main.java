package com.pajareria;


import java.util.Scanner;

/**
 * Clase principal de la aplicación. Contiene el menú de opciones para interactuar con los gestores.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class Main {
    /**
     * El método principal de la aplicación, que contiene el bucle del menú.
     *
     * @param args Argumentos de la línea de comandos (no se usan).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorClientes gestorClientes = new GestorClientes();
        GestorPajaros gestorPajaros = new GestorPajaros();
        GestorVentas gestorVentas = new GestorVentas();

        // Datos de ejemplo (opcional, para pruebas rápidas)
        gestorClientes.altaCliente(new Cliente("Ana Pérez", "111A", "600111222", "ana@example.com"));
        gestorClientes.altaCliente(new Cliente("Luis Gómez", "222B", "600333444", "luis@example.com"));
        gestorPajaros.altaPajaro(new Pajaro("Canario", "Amarillo", 25.0, 5));
        gestorPajaros.altaPajaro(new Pajaro("Periquito", "Azul", 18.5, 3));
        gestorPajaros.altaPajaro(new Pajaro("Jilguero", "Marrón", 30.0, 2));

        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de clientes");
            System.out.println("2. Gestión de pájaros");
            System.out.println("3. Realizar venta");
            System.out.println("4. Mostrar ventas");
            System.out.println("5. Ventas por cliente");
            System.out.println("6. Totales por cliente (extra)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = GestorClientes.leerEntero(sc);

            switch (opcion) {
                case 1 -> gestorClientes.menuClientes(sc);
                case 2 -> gestorPajaros.menuPajaros(sc);
                case 3 -> gestorVentas.nuevaVenta(sc, gestorClientes, gestorPajaros);
                case 4 -> gestorVentas.mostrarVentas();
                case 5 -> gestorVentas.mostrarVentasPorCliente(sc);
                case 6 -> gestorVentas.mostrarTotalesPorCliente();
                case 7 -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }
}