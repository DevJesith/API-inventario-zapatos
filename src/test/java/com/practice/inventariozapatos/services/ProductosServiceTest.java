package com.practice.inventariozapatos.services;

import com.practice.inventariozapatos.dto.ProductoRequestDTO;
import com.practice.inventariozapatos.dto.ProductoResponseDTO;
import com.practice.inventariozapatos.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductosServiceTest {

    @Autowired
    private ProductosServices productosServices;

    // Test de integración: valida el flujo real del servicio con el contexto de Spring Boot
    @Test
    void testCreateProducto(){

        // Arrange: preparar el request con datos validos
        ProductoRequestDTO requestDTO = new ProductoRequestDTO("Air Jordan 1", 42, BigDecimal.valueOf(180.00), Categoria.JORDAN);

        // Act: ejecutar la creacion del producto
        ProductoResponseDTO responseDTO = productosServices.createProducto(requestDTO);

        // Assert: validar que el producto se creó correctamente
        assertNotNull(responseDTO.getId());
        assertEquals("Air Jordan 1", responseDTO.getNombre());
        assertEquals(Categoria.JORDAN, responseDTO.getCategoria());
    }
}
