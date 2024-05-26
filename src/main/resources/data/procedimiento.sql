CREATE OR REPLACE PROCEDURE SP_INSERTANDLISTPRODUCTS (
    idProducto IN NUMBER,
    nombre IN VARCHAR2,
    fec_registro IN DATE,
    cursor OUT SYS_REFCURSOR,
    codigoRespuesta OUT NUMBER,
    mensajeRespuesta OUT VARCHAR2
) AS
BEGIN
    -- Intentar insertar el nuevo producto
BEGIN
INSERT INTO productos (id, nombre, fecha_registro)
VALUES (idProducto, nombre, fec_registro);

-- Si la inserción es exitosa, establecer el código y mensaje de respuesta
codigoRespuesta := 0;
        mensajeRespuesta := 'Ejecución con éxito';
EXCEPTION
        WHEN OTHERS THEN
            -- Si ocurre un error, establecer el código y mensaje de respuesta
            codigoRespuesta := 1;
            mensajeRespuesta := 'Error: ' || SQLERRM;
            -- Salir del procedimiento en caso de error
            RETURN;
END;

    -- Si la inserción fue exitosa, abrir el cursor para seleccionar todos los productos
OPEN cursor FOR
SELECT id, nombre, fecha_registro
FROM productos;
END SP_INSERTANDLISTPRODUCTS;