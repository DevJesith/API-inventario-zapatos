package com.practice.inventariozapatos.dto;

import com.practice.inventariozapatos.model.Categoria;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private Integer talla;
    private BigDecimal precio;
    private Categoria categoria;
}
