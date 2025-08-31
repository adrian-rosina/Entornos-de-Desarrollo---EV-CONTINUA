package com.pajareria;


/**
 * Representa un pájaro (producto) disponible en el catálogo.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class Pajaro {
    private String especie;
    private String color;
    private double precio;
    private int stock; // Extra: gestión de stock

    /**
     * Constructor para crear una nueva instancia de Pájaro.
     *
     * @param especie La especie del pájaro (ej. "Canario").
     * @param color El color predominante del pájaro.
     * @param precio El precio de venta.
     * @param stock La cantidad de pájaros disponibles.
     */
    public Pajaro(String especie, String color, double precio, int stock) {
        this.especie = especie;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Devuelve la especie del pájaro.
     *
     * @return La especie del pájaro.
     */
    public String getEspecie() { return especie; }
    
    /**
     * Devuelve el color del pájaro.
     *
     * @return El color del pájaro.
     */
    public String getColor() { return color; }
    
    /**
     * Devuelve el precio de venta del pájaro.
     *
     * @return El precio de venta.
     */
    public double getPrecio() { return precio; }
    
    /**
     * Devuelve la cantidad de pájaros disponibles en stock.
     *
     * @return El stock disponible.
     */
    public int getStock() { return stock; }

    /**
     * Establece una nueva cantidad de stock para el pájaro.
     *
     * @param stock La nueva cantidad de stock.
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * Devuelve una representación en cadena del objeto Pájaro.
     *
     * @return Una cadena con los detalles del pájaro.
     */
    @Override
    public String toString() {
        return "Pajaro {" +
                "especie='" + especie + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}