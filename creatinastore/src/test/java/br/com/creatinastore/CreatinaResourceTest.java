package br.com.creatinastore;

import br.com.creatinastore.Creatina.DTO.CreatinaRequestDTO;
import br.com.creatinastore.Disponibilidade.DTO.DisponibilidadeRequestDTO;
import br.com.creatinastore.Creatina.CreatinaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreatinaResourceTest {

    @Inject
    CreatinaService creatinaService;

    static Long id;

    @Test
    @Order(1)
    void testCriarCreatina() {
        var disponibilidade = new DisponibilidadeRequestDTO(
                100,
                "LOTE123",
                LocalDate.now().plusMonths(6)
        );

        var dto = new CreatinaRequestDTO(
                "Creatina Test",
                new BigDecimal("119.90"),
                new BigDecimal("300"),
                1,
                1L, // marcaId
                1L, // categoriaId
                1L, // fornecedorId
                List.of(1L), // componentesIds
                disponibilidade
        );

        String idString = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/produtos")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Creatina Test"))
                .extract().path("id").toString();

        id = Long.valueOf(idString);
    }

    @Test
    @Order(2)
    void testBuscarPorId() {
        given()
                .when().get("/produtos/" + id)
                .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("peso.valor", is(300f))
                .body("peso.unidadePeso", notNullValue())
                .body("quantidadeEstoque", greaterThan(0));
    }

    @Test
    @Order(3)
    void testListarTodas() {
        given()
                .when().get("/produtos")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(4)
    void testAtualizar() {
        var disponibilidade = new DisponibilidadeRequestDTO(
                150,
                "LOTE456",
                LocalDate.now().plusMonths(12)
        );

        var dto = new CreatinaRequestDTO(
                "Creatina Atualizada",
                new BigDecimal("129.90"),
                new BigDecimal("500"),
                1,
                1L,
                1L,
                1L,
                List.of(1L),
                disponibilidade
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().put("/produtos/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    void testDeletar() {
        given()
                .when().delete("/produtos/" + id)
                .then()
                .statusCode(204);
    }
}
