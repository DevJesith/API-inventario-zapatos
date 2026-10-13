package com.practice.inventariozapatos.controllers;

import com.practice.inventariozapatos.model.Productos;
import com.practice.inventariozapatos.services.ProductosServices;
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
    public Productos createProductos(@RequestBody Productos newProductos) {
        Productos productos = productosServices.createProducto(newProductos);
        return productos;
    }

    @GetMapping("/")
    public List<Productos> getProductos() {
        return productosServices.getProductos();
    }

    @GetMapping("/{id}")
    public Productos getProductoById(@PathVariable Long id) {
        return productosServices.findById(id);
    }

    @PutMapping("/{id}")
    public Productos updateProducto(@PathVariable Long id, @RequestBody Productos productos) {
        Productos producto1 = productosServices.updateProducto(id, productos);

        return producto1;

    }


    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id){
        productosServices.deleteById(id);
    }


}
