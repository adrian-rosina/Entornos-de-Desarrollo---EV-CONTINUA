package com.pajareria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VentaTest {

    @Test
    void calcularTotal_sumaPreciosDePajaros() {
        Cliente cliente = new Cliente("Ana", "111A", "600111222", "ana@example.com");
        Venta venta = new Venta(cliente);
        venta.addPajaro(new Pajaro("Canario", "Amarillo", 25.0, 10));
        venta.addPajaro(new Pajaro("Periquito", "Azul", 18.5, 5));

        assertEquals(43.5, venta.calcularTotal(), 0.0001);
    }
}
