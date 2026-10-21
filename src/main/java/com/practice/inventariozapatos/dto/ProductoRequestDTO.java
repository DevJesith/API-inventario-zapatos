package com.practice.inventariozapatos.dto;

import com.practice.inventariozapatos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequestDTO (

        @Schema(description = "Nombre del producto", example = "Air Jordan 1")
        @NotNull(message = "El nombre es obligatorio")
        @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
        String nombre,

        @Schema(description = "Talla del producto", example = "42")
        @NotNull(message = "La talla es obligatoria")
        @Min(value = 30, message = "La talla mínima es 15")
        @Max(value = 50, message = "La talla máxima es 50")
        Integer talla,

        @Schema(description = "Precio del producto", example = "199.99")
        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a que 0")
        BigDecimal precio,

        @Schema(description = "Categoría del producto", example = "JORDAN")
        @NotNull(message = "La categoría es obligatoria")
        Categoria categoria
) {}
