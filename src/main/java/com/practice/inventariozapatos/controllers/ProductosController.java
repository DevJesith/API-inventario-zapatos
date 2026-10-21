package com.practice.inventariozapatos.controllers;

import com.practice.inventariozapatos.dto.ProductoRequestDTO;
import com.practice.inventariozapatos.dto.ProductoResponseDTO;
import com.practice.inventariozapatos.model.Productos;
import com.practice.inventariozapatos.services.ProductosServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(name = "Productos", description = "Operaciones sobre productos")
public class ProductosController {

    private final ProductosServices productosServices;

    public ProductosController(ProductosServices productosServices) {
        this.productosServices = productosServices;
    }

    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en el inventario")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProductos(@Valid @RequestBody ProductoRequestDTO newProductos) {
        ProductoResponseDTO productos = productosServices.createProducto(newProductos);
        return ResponseEntity.status(HttpStatus.CREATED).body(productos);
    }

    @Operation(summary = "Obtener todos los productos", description = "Devuelve la lista completa de productos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductos() {
        return ResponseEntity.ok(productosServices.getProductos());
    }

    @Operation(summary = "Obtener productos con su numero de ID", description = "Devuelve un registro de un producto teniendo en cuenta su ID")
    @ApiResponse(responseCode = "200", description = "Registro obtenido correctamente")
    @ApiResponse(responseCode = "404", description = "El registro no fue encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable Long id) {
        ProductoResponseDTO producto = productosServices.findById(id);

        if (producto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    @Operation(summary = "Modificar un registro de producto", description = "Obtener el registro y se modifica")
    @ApiResponse(responseCode = "200", description = "Modificacion correctamente")
    @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO productos) {
        ProductoResponseDTO producto1 = productosServices.updateProducto(id, productos);
        return ResponseEntity.ok(producto1);
    }

    @Operation(summary = "Modificar un campo especifico de un producto", description = "Obtener el registro y se selecciona el campo a modificar")
    @ApiResponse(responseCode = "200", description = "Modificacion correctamente")
    @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> patchProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO productos) {
        ProductoResponseDTO producto1 = productosServices.patchProducto(id, productos);
        return ResponseEntity.ok(producto1);
    }

    @Operation(summary = "Eliminar producto", description = "Obtener el numero de ID del producto a eliminar")
    @ApiResponse(responseCode = "204", description = "Producto eliminado y retorna vacio el cuerpo")
    @ApiResponse(responseCode = "404", description = "No se encontro el producto a eliminar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        productosServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
