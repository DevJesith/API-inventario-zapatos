package com.practice.inventariozapatos.dto;

import com.practice.inventariozapatos.model.Categoria;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequestDTO (
        @NotNull(message = "El nombre es obligatorio")
        @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
        String nombre,

        @NotNull(message = "La talla es obligatoria")
        @Min(value = 30, message = "La talla mínima es 15")
        @Max(value = 50, message = "La talla máxima es 50")
        Integer talla,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a que 0")
        BigDecimal precio,

        @NotNull(message = "La categoría es obligatoria")
        Categoria categoria
) {}
