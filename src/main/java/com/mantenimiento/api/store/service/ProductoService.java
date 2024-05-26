package com.mantenimiento.api.store.service;

import com.mantenimiento.api.store.model.Producto;
import com.mantenimiento.api.store.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productRepository;

    public List<Producto> insertAndListProducts(int idProducto, String nombre, Date fec_registro) {
        return productRepository.insertAndListProductos(idProducto, nombre, fec_registro);
    }
}
