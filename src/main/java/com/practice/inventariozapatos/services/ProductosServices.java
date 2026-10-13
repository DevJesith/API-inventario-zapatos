package com.practice.inventariozapatos.services;

import com.practice.inventariozapatos.model.Productos;

import java.util.List;

public interface ProductosServices {

    //Crear un nuevo producto
    Productos createProducto(Productos producto);

    // Obtener todos los productos
    List<Productos> getProductos();

    // Buscar por ID un producto
    Productos findById(Long id);

    // Editar producto
    Productos updateProducto(Long id, Productos updateNewProducto);

    // Eliminar un producto
    void deleteById(Long id);


}
