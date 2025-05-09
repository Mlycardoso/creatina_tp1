package br.com.creatinastore;

import java.math.BigDecimal;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import br.com.creatinastore.Componente.DTO.ComponenteRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComponenteResourceTest {

    static Long id;

    @Test
    @Order(1)
    void testListar() {
        given()
            .when().get("/componentes")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testCriarComponente() {
        ComponenteRequestDTO dto = new ComponenteRequestDTO(
            "Cafeína", 
            "Melhora o foco e a energia", 
            BigDecimal.valueOf(249.99), 
            1
        );

        id = Long.valueOf(
            given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/componentes")
                .then()
                    .statusCode(201)
                    .body("id", notNullValue(),
                          "nome", is("Cafeína"),
                          "descricao", is("Melhora o foco e a energia"),
                          "quantidade", is(249.99f),
                          "concentracao", is("miligramas"))
                .extract().path("id").toString()
        );
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .when().get("/componentes/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    @Order(4)
    void testAtualizar() {
        ComponenteRequestDTO atualizado = new ComponenteRequestDTO(
            "Cafeína Alterada", 
            "Versão atualizada da cafeína", 
            BigDecimal.valueOf(199.99), 
            1
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/componentes/" + id)
            .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    void testDeletar() {
        given()
            .when().delete("/componentes/" + id)
            .then()
                .statusCode(204);
    }
}
