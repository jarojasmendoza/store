package com.mantenimiento.api.store.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
public class Producto {
    private int id;
    private String nombre;
    private Date fechaRegistro;
}
