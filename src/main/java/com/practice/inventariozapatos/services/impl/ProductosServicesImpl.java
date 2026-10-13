package com.practice.inventariozapatos.services.impl;

import com.practice.inventariozapatos.model.Productos;
import com.practice.inventariozapatos.repositories.ProductosRepository;
import com.practice.inventariozapatos.services.ProductosServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductosServicesImpl implements ProductosServices {

    private final ProductosRepository repository;

    public ProductosServicesImpl(ProductosRepository repository) {
        this.repository = repository;
    }

    @Override
    public Productos createProducto(Productos producto) {
        Productos newProducto = repository.save(producto);
        return newProducto;
    }

    @Override
    public List<Productos> getProductos() {
        List<Productos> productos = new ArrayList<>();
        repository.findAll().forEach(productos::add);
        return productos;
    }

    @Override
    public Productos findById(Long id) {
        Productos findProducto = repository.findById(id).orElse(null);
        return findProducto;
    }

    @Override
    public Productos updateProducto(Long id, Productos updateNewProducto) {
        Productos getProducto = repository.findById(id).orElse(null);

       if (getProducto == null){
           throw new IllegalArgumentException("No existe un metodo con ese nombre");
       }

       getProducto.setNombre(updateNewProducto.getNombre());
       getProducto.setTalla(updateNewProducto.getTalla());
       getProducto.setCategoria(updateNewProducto.getCategoria());

       return repository.save(getProducto);

    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("No existe el producto");
        }

        repository.deleteById(id);

    }
}
