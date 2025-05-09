package br.com.creatinastore;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.creatinastore.Categoria.CategoriaService;
import br.com.creatinastore.Categoria.DTO.CategoriaRequestDTO;
import br.com.creatinastore.Categoria.DTO.CategoriaResponseDTO;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaResourceTest {

    @Inject
    CategoriaService categoriaService;

    private static Long id;

    @Test
    @Order(1)
    void testCriarCategoria() {
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Categoria Teste", "Categoria usada para testes");

        id = Long.valueOf(
                given()
                    .contentType(ContentType.JSON)
                    .body(dto)
                    .when().post("/categoria")
                    .then()
                        .statusCode(201)
                        .body("id", notNullValue(),
                              "nome", is("Categoria Teste"),
                              "descricao", is("Categoria usada para testes"))
                    .extract().path("id").toString()
        );
    }

    @Test
    @Order(2)
    void testListarCategorias() {
        given()
            .when().get("/categoria")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(3)
    void testBuscarCategoriaPorId() {
        given()
            .when().get("/categoria/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Categoria Teste"));
    }

    @Test
    @Order(4)
    void testAtualizarCategoria() {
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Categoria Atualizada", "Nova descrição");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/categoria/"+id)
            .then()
                .statusCode(204);

        CategoriaResponseDTO response = categoriaService.findById(id);
        assertEquals("Categoria Atualizada", response.nome());
        assertEquals("Nova descrição", response.descricao());
    }

    @Test
    @Order(5)
    void testDeletarCategoria() {
        given()
            .when().delete("/categoria/" + id)
            .then()
                .statusCode(204);

    }
}