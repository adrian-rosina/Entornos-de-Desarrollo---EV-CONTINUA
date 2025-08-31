package com.pajareria;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Servicio de gestión CRUD (Crear, Leer, Actualizar, Borrar) de clientes y utilidades de menú.
 * Contiene métodos para dar de alta, baja, modificar y buscar clientes.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class GestorClientes {
    private final List<Cliente> clientes = new ArrayList<>();

    // -------- CRUD --------
    /**
     * Da de alta un nuevo cliente en el sistema.
     *
     * @param c El objeto Cliente a añadir.
     * @return {@code true} si el cliente se añade correctamente, {@code false} si ya existe un cliente con el mismo DNI.
     */
    public boolean altaCliente(Cliente c) {
        if (buscarClientePorDNI(c.getDni()) != null) return false; // DNI único
        clientes.add(c);
        return true;
    }

    /**
     * Da de baja un cliente del sistema buscando por su DNI.
     *
     * @param dni El DNI del cliente a eliminar.
     * @return {@code true} si se elimina el cliente, {@code false} si el cliente no se encuentra.
     */
    public boolean bajaCliente(String dni) {
        Cliente c = buscarClientePorDNI(dni);
        if (c != null) return clientes.remove(c);
        return false;
    }

    /**
     * Modifica los datos de un cliente existente.
     *
     * @param dni El DNI del cliente a modificar.
     * @param nuevoNombre El nuevo nombre, o {@code null} para no modificar.
     * @param nuevoTelefono El nuevo teléfono, o {@code null} para no modificar.
     * @param nuevoEmail El nuevo email, o {@code null} para no modificar.
     * @return {@code true} si el cliente se modifica, {@code false} si no se encuentra.
     */
    public boolean modificarCliente(String dni, String nuevoNombre, String nuevoTelefono, String nuevoEmail) {
        Cliente c = buscarClientePorDNI(dni);
        if (c == null) return false;
        if (nuevoNombre != null && !nuevoNombre.isBlank()) c.setNombre(nuevoNombre);
        if (nuevoTelefono != null && !nuevoTelefono.isBlank()) c.setTelefono(nuevoTelefono);
        if (nuevoEmail != null && !nuevoEmail.isBlank()) c.setEmail(nuevoEmail);
        return true;
    }

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni El DNI del cliente a buscar.
     * @return El objeto Cliente si se encuentra, o {@code null} si no existe.
     */
    public Cliente buscarClientePorDNI(String dni) {
        for (Cliente c : clientes) {
            if (c.getDni().equalsIgnoreCase(dni)) return c;
        }
        return null;
    }

    /**
     * Muestra en la consola la lista completa de clientes.
     */
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
    }

    /**
     * Devuelve la lista completa de clientes.
     *
     * @return La lista de clientes.
     */
    public List<Cliente> getClientes() { return clientes; }

    /**
     * Muestra el menú de gestión de clientes y gestiona las opciones.
     *
     * @param sc El objeto Scanner para leer la entrada del usuario.
     */
    public void menuClientes(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Alta cliente");
            System.out.println("2. Baja cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Buscar cliente por DNI");
            System.out.println("5. Listar clientes");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> altaClienteMenu(sc);
                case 2 -> bajaClienteMenu(sc);
                case 3 -> modificarClienteMenu(sc);
                case 4 -> buscarClienteMenu(sc);
                case 5 -> listarClientes();
                case 6 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    // Métodos auxiliares de menú y utilidad
    private void altaClienteMenu(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("DNI: ");
        String dni = sc.nextLine().trim();
        System.out.print("Teléfono: ");
        String tel = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        if (nombre.isBlank() || dni.isBlank()) {
            System.out.println("Nombre y DNI son obligatorios.");
            return;
        }
        boolean ok = altaCliente(new Cliente(nombre, dni, tel, email));
        System.out.println(ok ? "Cliente dado de alta." : "Ya existe un cliente con ese DNI.");
    }

    private void bajaClienteMenu(Scanner sc) {
        System.out.print("DNI del cliente a dar de baja: ");
        String dni = sc.nextLine().trim();
        boolean ok = bajaCliente(dni);
        System.out.println(ok ? "Cliente dado de baja." : "No se encontró cliente con ese DNI.");
    }

    private void modificarClienteMenu(Scanner sc) {
        System.out.print("DNI del cliente a modificar: ");
        String dni = sc.nextLine().trim();
        System.out.print("Nuevo nombre (dejar en blanco para no modificar): ");
        String nombre = sc.nextLine().trim();
        System.out.print("Nuevo teléfono: ");
        String tel = sc.nextLine().trim();
        System.out.print("Nuevo email: ");
        String email = sc.nextLine().trim();
        boolean ok = modificarCliente(dni, nombre, tel, email);
        System.out.println(ok ? "Cliente modificado." : "No se pudo modificar.");
    }

    private void buscarClienteMenu(Scanner sc) {
        System.out.print("DNI a buscar: ");
        String dni = sc.nextLine().trim();
        Cliente c = buscarClientePorDNI(dni);
        System.out.println(c != null ? c : "No encontrado.");
    }
    
    /**
     * Utilidad para leer un entero de forma segura desde el Scanner.
     *
     * @param sc El objeto Scanner.
     * @return El entero leído.
     */
    public static int leerEntero(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número válido: ");
            }
        }
    }

    /**
     * Utilidad para leer un número decimal de forma segura desde el Scanner.
     *
     * @param sc El objeto Scanner.
     * @return El número decimal leído.
     */
    public static double leerDouble(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número (p.ej. 12.5): ");
            }
        }
    }
}