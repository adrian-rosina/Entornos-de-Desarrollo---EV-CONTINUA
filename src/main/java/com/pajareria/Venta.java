package com.pajareria;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta asociada a un cliente y a una lista de pájaros.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class Venta {
    private Cliente cliente;
    private List<Pajaro> lineasDeVenta; // cada venta tiene varios pájaros
    private LocalDate fecha;

    /**
     * Constructor para crear una nueva instancia de Venta.
     *
     * @param cliente El cliente al que se asocia la venta.
     */
    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.fecha = LocalDate.now();
        this.lineasDeVenta = new ArrayList<>();
    }

    /**
     * Devuelve el cliente asociado a la venta.
     *
     * @return El cliente.
     */
    public Cliente getCliente() { return cliente; }
    
    /**
     * Devuelve la fecha de la venta.
     *
     * @return La fecha de la venta.
     */
    public LocalDate getFecha() { return fecha; }
    
    /**
     * Devuelve la lista de pájaros incluidos en la venta.
     *
     * @return La lista de líneas de venta.
     */
    public List<Pajaro> getLineasDeVenta() { return lineasDeVenta; }

    /**
     * Añade un pájaro a la lista de productos de la venta.
     *
     * @param p El pájaro a añadir.
     */
    public void addPajaro(Pajaro p) {
        lineasDeVenta.add(p);
    }

    /**
     * Calcula el importe total de la venta sumando los precios de todos los pájaros.
     *
     * @return El importe total de la venta.
     */
    public double calcularTotal() {
        double total = 0.0;
        for (Pajaro p : lineasDeVenta) {
            total += p.getPrecio();
        }
        return total;
    }

    /**
     * Devuelve una representación en cadena de la venta.
     *
     * @return Una cadena con los detalles de la venta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta {fecha=").append(fecha)
                .append(", cliente=").append(cliente.getNombre())
                .append(" (DNI ").append(cliente.getDni()).append(")")
                .append(", total=").append(String.format("%.2f", calcularTotal())).append("€");
        sb.append(", items=");
        for (int i = 0; i < lineasDeVenta.size(); i++) {
            Pajaro p = lineasDeVenta.get(i);
            sb.append(p.getEspecie()).append("-").append(p.getColor())
                    .append("(").append(String.format("%.2f", p.getPrecio())).append("€)");
            if (i < lineasDeVenta.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}