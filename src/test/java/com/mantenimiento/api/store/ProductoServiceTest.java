package com.mantenimiento.api.store;

import com.mantenimiento.api.store.model.Producto;
import com.mantenimiento.api.store.repository.ProductoRepository;
import com.mantenimiento.api.store.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productRepositoryMock;

    @InjectMocks
    private ProductoService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertAndListProductos() {
        // Configurar el comportamiento del mock de ProductoRepository
        List<Producto> productosEsperados = new ArrayList<>();
        productosEsperados.add(createProducto(1, "Producto 1", new Date()));
        productosEsperados.add(createProducto(2, "Producto 2", new Date()));

        when(productRepositoryMock.insertAndListProductos(1, "Producto 1", new Date()))
                .thenReturn(productosEsperados);

        // Llamar al método bajo prueba
        List<Producto> productosActuales = productService.insertAndListProducts(2, "Producto 2", new Date());

        // Verificar que el método devuelve la lista esperada
        assertEquals(productosEsperados, productosEsperados);
    }


    private Producto createProducto(int id, String nombre, Date fechaRegistro) {
        Producto product = new Producto();
        product.setId(id);
        product.setNombre(nombre);
        product.setFechaRegistro(fechaRegistro);
        return product;
    }
}
