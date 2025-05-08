package br.com.creatinastore;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.com.creatinastore.Marca.MarcaService;
import br.com.creatinastore.Marca.DTO.MarcaRequestDTO;
import br.com.creatinastore.Marca.DTO.MarcaResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class MarcaResourceTest {

    @Inject
    MarcaService service;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/marcas")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        MarcaRequestDTO marca = new MarcaRequestDTO(
            "Rio Grande do Sul 2", 
            "RS");

        given()
            .contentType(ContentType.JSON)
            .body(marca)
            .when().post("/marcas")
            .then()
                .statusCode(201)
                .body(
                    "id", notNullValue(),
                    "nome", is("Rio Grande do Sul 2"),
                    "sigla", is("RS") 
                );
    }

    static Long id = null; 

    @Test
    void testAlterar() {
        MarcaRequestDTO marca = new MarcaRequestDTO(
            "Rio Grande do Sul 3", 
            "RS");
       
        id =  service.create(marca).id();

        MarcaRequestDTO marcaAlterada = new MarcaRequestDTO(
            "Rio Grande do Sul - Alterado", 
            "RR");

        given()
            .contentType(ContentType.JSON)
            .body(marcaAlterada)
            .when().put("/marcas/" + id)
            .then()
                .statusCode(204);

        MarcaResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Rio Grande do Sul - Alterado"));
        assertThat(response.paisOrigem(), is("RR"));
    }

    @Test
    void testApagar() {
        given()
        .when().delete("/marcas/" + id)
        .then()
            .statusCode(204);

            MarcaResponseDTO response = service.findById(id);
        assertNull(response);
    }

}
