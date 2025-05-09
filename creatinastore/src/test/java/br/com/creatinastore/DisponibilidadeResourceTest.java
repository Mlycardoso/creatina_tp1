package br.com.creatinastore;

import org.junit.jupiter.api.*;

import br.com.creatinastore.Creatina.CreatinaService;
import br.com.creatinastore.Creatina.DTO.CreatinaRequestDTO;
import br.com.creatinastore.Disponibilidade.DTO.DisponibilidadeRequestDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DisponibilidadeResourceTest {

    @Inject
    CreatinaService creatinaService;

    Long idDisponibilidade;
    Long idProduto;

    @BeforeEach
    void setup() {
        // Criar uma Creatina que cria automaticamente uma Disponibilidade
        CreatinaRequestDTO dto = new CreatinaRequestDTO(
            "Creatina Teste",
            new BigDecimal("59.90"),
            new BigDecimal("300"),
            Integer.valueOf(1),
            Long.valueOf(1L), 
            Long.valueOf(1L), 
            Long.valueOf(1L),
            List.of(1L), 
            new DisponibilidadeRequestDTO(20, "L123", LocalDate.now().plusMonths(6))
        );

        var response = creatinaService.create(dto);
        idProduto = response.id();
        idDisponibilidade = response.idDisponibilidade();
    }

    @Test
    @Order(1)
    void testListarTodas() {
        given()
            .when().get("/disponibilidades")
            .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    @Order(2)
    void testBuscarPorId() {
        given()
            .when().get("/disponibilidades/" + idDisponibilidade)
            .then()
                .statusCode(200)
                .body("id", is(idDisponibilidade.intValue()));
    }

    @Test
    @Order(3)
    void testBuscarPorProduto() {
        given()
            .when().get("/disponibilidades/produto/" + idProduto)
            .then()
                .statusCode(200)
                .body("id", is(idDisponibilidade.intValue()));
    }

    @Test
    @Order(4)
    void testListarVencidos() {
        given()
            .when().get("/disponibilidades/vencidos")
            .then()
                .statusCode(200);
        // Não espera retorno aqui, a menos que haja um item vencido.
    }

    @Test
    @Order(5)
    void testListarEstoqueBaixo() {
        given()
            .queryParam("limite", 21)
            .when().get("/disponibilidades/estoque-baixo")
            .then()
                .statusCode(200)
                .body("$", not(empty())); 
    }
}
