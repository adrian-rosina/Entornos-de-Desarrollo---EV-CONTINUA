package com.pajareria;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Servicio de gestión del catálogo de pájaros y utilidades de menú.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class GestorPajaros {
    private final List<Pajaro> catalogo = new ArrayList<>();

    /**
     * Da de alta un nuevo pájaro en el catálogo.
     *
     * @param p El pájaro a añadir.
     */
    public void altaPajaro(Pajaro p) {
        catalogo.add(p);
    }

    /**
     * Muestra en la consola la lista completa del catálogo de pájaros.
     */
    public void listarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("No hay pájaros en el catálogo.");
            return;
        }
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " + catalogo.get(i));
        }
    }

    /**
     * Busca pájaros por especie y devuelve una lista de resultados.
     *
     * @param especie La especie a buscar.
     * @return Una lista de pájaros que coinciden con la especie, o una lista vacía si no se encuentra ninguno.
     */
    public List<Pajaro> buscarPorEspecie(String especie) {
        List<Pajaro> res = new ArrayList<>();
        for (Pajaro p : catalogo) {
            if (p.getEspecie().equalsIgnoreCase(especie)) res.add(p);
        }
        return res;
    }

    /**
     * Devuelve el catálogo completo de pájaros.
     *
     * @return La lista del catálogo.
     */
    public List<Pajaro> getCatalogo() { return catalogo; }

    /**
     * Muestra el menú de gestión de pájaros y gestiona las opciones.
     *
     * @param sc El objeto Scanner para leer la entrada del usuario.
     */
    public void menuPajaros(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PÁJAROS ===");
            System.out.println("1. Alta en catálogo");
            System.out.println("2. Listar catálogo");
            System.out.println("3. Buscar por especie");
            System.out.println("4. Ordenar por especie");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = GestorClientes.leerEntero(sc);

            switch (opcion) {
                case 1 -> altaPajaroMenu(sc);
                case 2 -> listarCatalogo();
                case 3 -> buscarPorEspecieMenu(sc);
                case 4 -> ordenarPorEspecie();
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void altaPajaroMenu(Scanner sc) {
        System.out.print("Especie: ");
        String especie = sc.nextLine().trim();
        System.out.print("Color: ");
        String color = sc.nextLine().trim();
        System.out.print("Precio (€): ");
        double precio = GestorClientes.leerDouble(sc);
        System.out.print("Stock: ");
        int stock = GestorClientes.leerEntero(sc);

        if (especie.isBlank()) {
            System.out.println("La especie es obligatoria.");
            return;
        }
        altaPajaro(new Pajaro(especie, color, precio, stock));
        System.out.println("Pájaro añadido al catálogo.");
    }

    private void buscarPorEspecieMenu(Scanner sc) {
        System.out.print("Especie a buscar: ");
        String especie = sc.nextLine().trim();
        List<Pajaro> res = buscarPorEspecie(especie);
        if (res.isEmpty()) {
            System.out.println("No se encontraron pájaros de esa especie.");
        } else {
            for (Pajaro p : res) System.out.println(p);
        }
    }

    private void ordenarPorEspecie() {
        Collections.sort(catalogo, Comparator.comparing(Pajaro::getEspecie, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Catálogo ordenado por especie.");
    }
}