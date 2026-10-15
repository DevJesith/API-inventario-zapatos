package com.practice.inventariozapatos.dto;

import com.practice.inventariozapatos.model.Categoria;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private Integer talla;
    private Double precio;
    private Categoria categoria;
}
