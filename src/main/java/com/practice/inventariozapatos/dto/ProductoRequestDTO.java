package com.practice.inventariozapatos.dto;

import com.practice.inventariozapatos.model.Categoria;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoRequestDTO (
        @NotNull(message = "El nombre es obligatorio")
        @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
        String nombre,
        @NotNull(message = "La talla es obligatoria")
        Integer talla,
        Double precio,
        @NotNull(message = "La categoría es obligatoria")
        Categoria categoria
) {}
