package com.practice.inventariozapatos.controllers;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductosControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateProductoValidationError() throws Exception {
        // Arrange: preparar JSON invalido
        String invalidJson = """
                {
                    "nombre": "",
                    "talla": 80,
                    "precio": -10,
                    "categoria": "NIKE"
                }
                """;

        // Act: ejecutar POST
        mockMvc.perform(
                post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                // Assert: validar respuesta 400 y datos incorrectos
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void testCreateProductoSuccess() throws Exception {
        // Arrange: preparar JSON valido
        String validJson = """
                {
                    "nombre": "Air Jordan 1",
                    "talla": 40,
                    "precio": 180.00,
                    "categoria": "JORDAN"
                }
                """;

        // Act: ejecutar POST
        mockMvc.perform(
                post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                // Assert: validar respuesta 201 y datos correctos
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Air Jordan 1"))
                .andExpect(jsonPath("$.categoria").value("JORDAN"));
    }

    @Test
    void testGetProductoNotFound() throws Exception {
        // Arrange: usar un ID que no existe en la BD
        Long nonExistingId = 50L;

        // Act & Assert: ejecutar GET y validar que devuelve 404
        mockMvc.perform(
                get("/productos/" + nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetProductoSuccess() throws Exception {
        // Arrange: usar un ID que existe en la BD
        Long nonExistingId = 8L;

        // Act & Assert: ejecutar GET y validar que devuelve 200
        mockMvc.perform(
                get("/productos/" + nonExistingId))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProductoSuccess() throws Exception {

        // Arrange: crear un producto inicial
        String createJson = """
                {
                    "nombre": "Air Jordan 1",
                    "talla": 40,
                    "precio": 180.00,
                    "categoria": "JORDAN"
                }
                """;

        MvcResult result = mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isCreated()).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Number idNumber = JsonPath.read(responseBody, "$.id");
        Long id = idNumber.longValue();

        // Act: ejecutar PUT con datos actualizados
        String updatedJson = """
                {
                    "nombre": "Air Jordan 1 Retro",
                    "talla": 43,
                    "precio": 200.00,
                    "categoria": "JORDAN"
                }
                """;

        // Assert: validar que la respuesta refleja los cambios
        mockMvc.perform(
                put("/productos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Air Jordan 1 Retro"))
                .andExpect(jsonPath("$.talla").value(43))
                .andExpect(jsonPath("$.precio").value(200.00));
    }

    @Test
    void testUpdateProductoValidationError() throws Exception {
        // Arrange: preparar JSON invalido
        String invalidJson = """
                {
                    "nombre": "",
                    "talla": 100,
                    "precio": -50,
                    "categoria": "NIKE"
                }
                """;

        // Act & Assert: ejecutar PUT y validar que devuelva 400 con mensaje
        mockMvc.perform(
                put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testDeleteProductoSuccess() throws Exception {

        // Arrange: preparar JSON valido para eliminarlo
        String createJson = """
                {
                    "nombre": "Nike Dunkk",
                    "talla": 41,
                    "precio": 150.00,
                    "categoria": "NIKE"
                }
                """;


        MvcResult result = mockMvc.perform(
                        post("/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createJson))
                .andExpect(status().isCreated())
                .andReturn();

        String respondeseBody = result.getResponse().getContentAsString();
        Number idNumber = JsonPath.read(respondeseBody, "$.id");
        Long id = idNumber.longValue();

        // Act & Assert: ejecutar DELETE y validar que devuelva 204
        mockMvc.perform(
                        delete("/productos/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteProductoNotFound() throws Exception {

        // Arrange: preparar un id que no exista en la BD
        Long id = 999L;

        // Act & Assert: ejecutar DELETE y validar que devuelva 404 con mensaje
        mockMvc.perform(
                        delete("/productos/" + id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }


}
