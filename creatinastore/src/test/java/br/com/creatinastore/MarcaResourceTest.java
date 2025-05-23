package br.com.creatinastore;

import org.junit.jupiter.api.*;

import br.com.creatinastore.Marca.DTO.MarcaRequestDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MarcaResourceTest {

    static Long id;

    @Test
    @Order(1)
    void testListar() {
        given()
            .when().get("/marcas")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testCriarMarca() {
        MarcaRequestDTO dto = new MarcaRequestDTO(
            "Marca Teste",
            "Pais Teste"
        );

        id = Long.valueOf(
            given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/marcas")
                .then()
                    .statusCode(201)
                    .body("id", notNullValue(),
                          "nome", is("Marca Teste"),
                          "paisOrigem", is("Pais Teste"))
                .extract().path("id").toString()
        );
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .when().get("/marcas/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    @Order(4)
    void testAtualizar() {
        MarcaRequestDTO dtoAtualizada = new MarcaRequestDTO(
            "Marca Atualizada",
            "Teste Update Pais"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoAtualizada)
            .when().put("/marcas/" + id)
            .then()
                .statusCode(204);
    }

    @Test
    @Order(5)
    void testDeletar() {
        given()
            .when().delete("/marcas/" + id)
            .then()
                .statusCode(204);
    }
}
