import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CarritoDTOTest {

    @Test
    void testConstructorVacio() {
        CarritoDTO carrito = new CarritoDTO();

        assertNull(carrito.getClienteId());
        assertNull(carrito.getItems());
    }

    @Test
    void testConstructorConParametros() {
        Long clienteId = 1L;
        List<ProductoCarritoDTO> items = new ArrayList<>();

        CarritoDTO carrito = new CarritoDTO(clienteId, items);

        assertEquals(clienteId, carrito.getClienteId());
        assertEquals(items, carrito.getItems());
    }

}
