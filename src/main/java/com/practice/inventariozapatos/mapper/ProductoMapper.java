package com.practice.inventariozapatos.mapper;

import com.practice.inventariozapatos.dto.ProductoRequestDTO;
import com.practice.inventariozapatos.dto.ProductoResponseDTO;
import com.practice.inventariozapatos.model.Productos;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Productos toEntity(ProductoRequestDTO dto) {
        return Productos.builder()
                .nombre(dto.nombre())
                .talla(dto.talla())
                .precio(dto.precio())
                .categoria(dto.categoria())
                .build();
    }

    public ProductoResponseDTO toDTO(Productos entity) {
        return ProductoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .talla(entity.getTalla())
                .precio(entity.getPrecio())
                .categoria(entity.getCategoria())
                .build();
    }
}
