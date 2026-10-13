package com.practice.inventariozapatos.controllers;

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
    public ResponseEntity<Productos> createProductos(@Valid @RequestBody Productos newProductos) {
        Productos productos = productosServices.createProducto(newProductos);
        return ResponseEntity.status(HttpStatus.CREATED).body(productos);
    }

    @GetMapping
    public ResponseEntity<List<Productos>> getProductos() {
        return ResponseEntity.ok(productosServices.getProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> getProductoById(@PathVariable Long id) {
        Productos producto = productosServices.findById(id);

        if (producto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> updateProducto(@PathVariable Long id, @RequestBody Productos productos) {
        Productos producto1 = productosServices.updateProducto(id, productos);

        return ResponseEntity.ok(producto1);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        productosServices.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
