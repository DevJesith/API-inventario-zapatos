package com.practice.inventariozapatos.controllers;

import com.practice.inventariozapatos.dto.ProductoRequestDTO;
import com.practice.inventariozapatos.dto.ProductoResponseDTO;
import com.practice.inventariozapatos.model.Productos;
import com.practice.inventariozapatos.services.ProductosServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosServices productosServices;


    public ProductosController(ProductosServices productosServices) {
        this.productosServices = productosServices;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProductos(@Valid @RequestBody ProductoRequestDTO requestDTO) {
        ProductoResponseDTO productos = productosServices.createProducto(requestDTO);
        return new ResponseEntity<>(productos, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductos() {
        return ResponseEntity.ok(productosServices.getProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable Long id) {
        ProductoResponseDTO producto = productosServices.findById(id);

        if (producto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO requestDTO) {
        ProductoResponseDTO producto1 = productosServices.updateProducto(id, requestDTO);
        return ResponseEntity.ok(producto1);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        productosServices.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
