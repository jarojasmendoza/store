package com.mantenimiento.api.store.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductoRequest {
    private int id;
    private String nombre;
    private Date fechaRegistro;
}
