package com.pajareria;

import java.util.Objects;

/**
 * Representa un cliente de la pajarería.
 * Cada cliente tiene un nombre, DNI, teléfono y correo electrónico.
 *
 * @author Adrián Rosiña Pérez
 * @version 1.0
 */
public class Cliente {
    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    /**
     * Constructor para crear una nueva instancia de Cliente.
     *
     * @param nombre El nombre completo del cliente.
     * @param dni El documento de identidad del cliente (clave única).
     * @param telefono El número de teléfono de contacto.
     * @param email El correo electrónico del cliente.
     */
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene el nombre completo del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() { return nombre; }
    
    /**
     * Obtiene el documento de identidad (DNI) del cliente.
     *
     * @return El DNI del cliente.
     */
    public String getDni() { return dni; }
    
    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono.
     */
    public String getTelefono() { return telefono; }
    
    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return El correo electrónico.
     */
    public String getEmail() { return email; }

    /**
     * Establece un nuevo nombre para el cliente.
     *
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /**
     * Establece un nuevo número de teléfono para el cliente.
     *
     * @param telefono El nuevo teléfono.
     */
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    /**
     * Establece un nuevo correo electrónico para el cliente.
     *
     * @param email El nuevo email.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Devuelve una representación en cadena del objeto Cliente.
     *
     * @return Una cadena que contiene los detalles del cliente.
     */
    @Override
    public String toString() {
        return "Cliente {" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Compara este cliente con otro objeto para determinar si son iguales.
     * La igualdad se basa en el DNI del cliente.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales (mismo DNI), {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    /**
     * Calcula el valor de hash para el cliente, basado en el DNI.
     *
     * @return El valor de hash del DNI.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}