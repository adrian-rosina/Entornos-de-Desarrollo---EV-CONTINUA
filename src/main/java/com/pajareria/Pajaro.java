package com.pajareria;


/**
 * Representa un pájaro (producto) disponible en el catálogo.
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class Pajaro {
    private String especie;
    private String color;
    private double precio;
    private int stock; // Extra: gestión de stock

    public Pajaro(String especie, String color, double precio, int stock) {
        this.especie = especie;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }

    public String getEspecie() { return especie; }
    public String getColor() { return color; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

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