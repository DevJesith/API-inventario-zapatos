package com.practice.inventariozapatos.services;

import com.practice.inventariozapatos.dto.ProductoRequestDTO;
import com.practice.inventariozapatos.dto.ProductoResponseDTO;
import com.practice.inventariozapatos.model.Productos;

import java.util.List;

public interface ProductosServices {

    //Crear un nuevo producto
    ProductoResponseDTO createProducto(ProductoRequestDTO requestDTO);

    // Obtener todos los productos
    List<ProductoResponseDTO> getProductos();

    // Buscar por ID un producto
    ProductoResponseDTO findById(Long id);

    // Editar producto completo
    ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO requestDTO);

    // Editar campo
    ProductoResponseDTO patchProducto(Long id, ProductoRequestDTO requestDTO);

    // Eliminar un producto
    void deleteById(Long id);


}
