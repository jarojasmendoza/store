package com.mantenimiento.api.store.repository;

import com.mantenimiento.api.store.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public class ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Producto> insertAndListProductos(int idProducto, String nombre, Date fec_registro) {
        return jdbcTemplate.execute((Connection connection) -> {
            try (CallableStatement callableStatement = connection.prepareCall("{call SP_INSERTANDLISTPRODUCTS (?, ?, ?, ?, ?, ?)}")) {
                callableStatement.setInt(1, idProducto);
                callableStatement.setString(2, nombre);
                callableStatement.setDate(3, new java.sql.Date(fec_registro.getTime()));
                callableStatement.registerOutParameter(4, Types.REF_CURSOR);
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.registerOutParameter(6, Types.VARCHAR);
                callableStatement.execute();

                int codigoRespuesta = callableStatement.getInt(5);
                String mensajeRespuesta = callableStatement.getString(6);

                if (codigoRespuesta != 0) {
                    throw new RuntimeException("Error al ejecutar el procedimiento almacenado: " + mensajeRespuesta);
                }

                List<Producto> productList = new ArrayList<>();
                try (ResultSet rs = (ResultSet) callableStatement.getObject(4)) {
                    while (rs.next()) {
                        Producto product = new Producto();
                        product.setId(rs.getInt("id"));
                        product.setNombre(rs.getString("nombre"));
                        product.setFechaRegistro(rs.getDate("fecha_registro"));
                        productList.add(product);
                    }
                }

                return productList;
            } catch (SQLException ex) {
                throw new RuntimeException("Error al ejecutar el procedimiento almacenado", ex);
            }
        });
    }

}
