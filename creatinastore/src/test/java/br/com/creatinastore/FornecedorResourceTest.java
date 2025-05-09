package br.com.creatinastore;

import br.com.creatinastore.Fornecedor.DTO.FornecedorRequestDTO;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FornecedorResourceTest {

    static Long id;

    @Test
    @Order(1)
    void testListar() {
        given()
            .when().get("/fornecedores")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testCriarFornecedor() {
        FornecedorRequestDTO dto = new FornecedorRequestDTO(
            "Fornecedor Teste",
            "51999999999",
            "12345678000199",
            "teste@fornecedor.com"
        );

        id = Long.valueOf(
            given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/fornecedores")
                .then()
                    .statusCode(201)
                    .body("id", notNullValue(),
                          "nome", is("Fornecedor Teste"),
                          "cnpj", is("51999999999"),
                          "telefone", is("12345678000199"),
                          "email", is("teste@fornecedor.com"))
                .extract().path("id").toString()
        );
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .when().get("/fornecedores/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    @Order(4)
    void testAtualizar() {
        FornecedorRequestDTO atualizado = new FornecedorRequestDTO(
            "Fornecedor Atualizado",
            "51988888888",
            "12345678000199",
            "atualizado@fornecedor.com"
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/fornecedores/" + id)
            .then()
                .statusCode(204);
    }

    @Test
    @Order(5)
    void testDeletar() {
        given()
            .when().delete("/fornecedores/" + id)
            .then()
                .statusCode(204);
    }
}
