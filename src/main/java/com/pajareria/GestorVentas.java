package com.pajareria;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Servicio para crear y consultar ventas, incluyendo control de stock.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class GestorVentas {
    private final List<Venta> ventas = new ArrayList<>();

    /**
     * Crea una nueva venta con un menú guiado para el usuario.
     *
     * @param sc El objeto Scanner para leer la entrada del usuario.
     * @param gc El GestorClientes para buscar clientes.
     * @param gp El GestorPajaros para gestionar el stock y catálogo.
     */
    public void nuevaVenta(Scanner sc, GestorClientes gc, GestorPajaros gp) {
        if (gc.getClientes().isEmpty()) {
            System.out.println("No hay clientes. Da de alta un cliente primero.");
            return;
        }
        if (gp.getCatalogo().isEmpty()) {
            System.out.println("No hay pájaros en catálogo.");
            return;
        }

        System.out.print("DNI del cliente: ");
        String dni = sc.nextLine().trim();
        Cliente cliente = gc.buscarClientePorDNI(dni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Venta venta = new Venta(cliente);
        boolean agregando = true;
        while (agregando) {
            System.out.println("\nCatálogo actual (solo con stock):");
            List<Integer> indicesDisponibles = listarCatalogoConStock(gp);
            if (indicesDisponibles.isEmpty()) {
                System.out.println("No hay stock disponible. No se pueden añadir más pájaros.");
                break;
            }
            System.out.print("Selecciona el número del pájaro a vender (0 para terminar): ");
            int opcion = GestorClientes.leerEntero(sc);
            if (opcion == 0) {
                agregando = false;
            } else if (opcion > 0 && opcion <= indicesDisponibles.size()) {
                int indiceSeleccionado = indicesDisponibles.get(opcion - 1);
                Pajaro pajaro = gp.getCatalogo().get(indiceSeleccionado);
                venta.addPajaro(pajaro);
                pajaro.setStock(pajaro.getStock() - 1);
                System.out.println("Pájaro añadido a la venta.");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        if (venta.getLineasDeVenta().isEmpty()) {
            System.out.println("Venta cancelada. No se añadieron productos.");
        } else {
            ventas.add(venta);
            System.out.println("\n--- VENTA REGISTRADA ---");
            System.out.println(venta);
        }
    }

    /**
     * Muestra la lista de ventas registradas.
     */
    public void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        System.out.println("\n=== LISTADO DE VENTAS ===");
        for (Venta v : ventas) {
            System.out.println(v);
        }
    }

    /**
     * Muestra las ventas de un cliente específico, buscando por su DNI.
     *
     * @param sc El objeto Scanner para leer la entrada del usuario.
     */
    public void mostrarVentasPorCliente(Scanner sc) {
        System.out.print("DNI del cliente: ");
        String dni = sc.nextLine().trim();
        boolean encontrado = false;
        double totalCliente = 0.0;
        for (Venta v : ventas) {
            if (v.getCliente().getDni().equalsIgnoreCase(dni)) {
                System.out.println(v);
                totalCliente += v.calcularTotal();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay ventas para ese cliente.");
        } else {
            System.out.println("Importe total de ventas del cliente: " + String.format("%.2f", totalCliente) + "€");
        }
    }

    /**
     * Muestra un resumen del total de ventas por cada cliente.
     */
    public void mostrarTotalesPorCliente() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        Map<String, Double> acumulados = new HashMap<>();
        for (Venta v : ventas) {
            String dni = v.getCliente().getDni();
            acumulados.put(dni, acumulados.getOrDefault(dni, 0.0) + v.calcularTotal());
        }
        System.out.println("\n=== Total de ventas por cliente ===");
        for (Map.Entry<String, Double> e : acumulados.entrySet()) {
            System.out.println("DNI " + e.getKey() + ": " + String.format("%.2f", e.getValue()) + "€");
        }
    }

    private List<Integer> listarCatalogoConStock(GestorPajaros gp) {
        List<Integer> indicesDisponibles = new ArrayList<>();
        int i = 0;
        for (Pajaro p : gp.getCatalogo()) {
            if (p.getStock() > 0) {
                System.out.println((indicesDisponibles.size() + 1) + ". " + p);
                indicesDisponibles.add(i);
            }
            i++;
        }
        return indicesDisponibles;
    }
}