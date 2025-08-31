package com.pajareria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorClientesTest {

    @Test
    void altaCliente_noPermiteDniDuplicado() {
        GestorClientes gc = new GestorClientes();
        Cliente c1 = new Cliente("Adrian", "111A", "600111222", "adrian@gmail.com");
        Cliente c2 = new Cliente("Miriam", "111A", "600333444", "miriam@gmail.com"); // mismo DNI

        assertTrue(gc.altaCliente(c1));
        assertFalse(gc.altaCliente(c2)); // debe rechazar duplicado
    }

    @Test
    void buscarClientePorDNI_devuelveNull_siNoExiste() {
        GestorClientes gc = new GestorClientes();
        assertNull(gc.buscarClientePorDNI("XYZ999"));
    }
}
