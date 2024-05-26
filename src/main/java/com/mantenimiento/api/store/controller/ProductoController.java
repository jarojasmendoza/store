package com.mantenimiento.api.store.controller;


import com.mantenimiento.api.store.model.Producto;
import com.mantenimiento.api.store.model.ProductoRequest;
import com.mantenimiento.api.store.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productService;

    @PostMapping("/agregar")
    public List<Producto> crearProducto(@RequestBody ProductoRequest idProducto) {
        return productService.insertAndListProducts(idProducto.getId(), idProducto.getNombre(), idProducto.getFechaRegistro());
    }
}
