package com.practice.inventariozapatos.repositories;

import com.practice.inventariozapatos.model.Productos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends CrudRepository<Productos, Long> {

}
