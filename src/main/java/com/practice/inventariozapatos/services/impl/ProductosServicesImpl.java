package com.practice.inventariozapatos.services.impl;

import com.practice.inventariozapatos.dto.ProductoRequestDTO;
import com.practice.inventariozapatos.dto.ProductoResponseDTO;
import com.practice.inventariozapatos.mapper.ProductoMapper;
import com.practice.inventariozapatos.model.Productos;
import com.practice.inventariozapatos.repositories.ProductosRepository;
import com.practice.inventariozapatos.services.ProductosServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductosServicesImpl implements ProductosServices {

    private final ProductosRepository repository;
    private final ProductoMapper mapper;

    public ProductosServicesImpl(ProductosRepository repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductoResponseDTO createProducto(ProductoRequestDTO requestDTO) {
        Productos productos = mapper.toEntity(requestDTO);
        Productos savedProducto = repository.save(productos);
        return mapper.toDTO(savedProducto);
    }

    @Override
    public List<ProductoResponseDTO> getProductos() {
        List<ProductoResponseDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(productos -> dtos.add(mapper.toDTO(productos)));
        return dtos;
    }

    @Override
    public ProductoResponseDTO findById(Long id) {
        Productos findProducto = repository.findById(id).orElse(null);
        return mapper.toDTO(findProducto);
    }

    @Override
    public ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO requestDTO) {
        Productos getProducto = repository.findById(id).orElse(null);

        if (getProducto == null) {
            throw new IllegalArgumentException("No existe un metodo con ese nombre");
        }

        getProducto.setNombre(requestDTO.nombre());
        getProducto.setTalla(requestDTO.talla());
        getProducto.setPrecio(requestDTO.precio());
        getProducto.setCategoria(requestDTO.categoria());

        Productos updatedProductos = repository.save(getProducto);
        return mapper.toDTO(updatedProductos);
    }

    @Override
    public ProductoResponseDTO patchProducto(Long id, ProductoRequestDTO requestDTO) {
        Productos productos = repository.findById(id).orElse(null);

        if (requestDTO.nombre() != null) {
            productos.setNombre(requestDTO.nombre());
        }
        if (requestDTO.talla() != null) {
            productos.setTalla(requestDTO.talla());
        }
        if (requestDTO.precio() != null) {
            productos.setPrecio(requestDTO.precio());
        }
        if (requestDTO.categoria() != null) {
            productos.setCategoria(requestDTO.categoria());
        }

        Productos update = repository.save(productos);
        return mapper.toDTO(update);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No existe el producto");
        }

        repository.deleteById(id);

    }
}
