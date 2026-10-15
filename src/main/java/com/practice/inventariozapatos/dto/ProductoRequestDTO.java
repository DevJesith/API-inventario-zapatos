package com.practice.inventariozapatos.dto;

import com.practice.inventariozapatos.model.Categoria;

public record ProductoRequestDTO (String nombre, Integer talla, Double precio, Categoria categoria) {}
