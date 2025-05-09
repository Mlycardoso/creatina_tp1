package br.com.creatinastore.Disponibilidade;

import java.util.List;

import br.com.creatinastore.Disponibilidade.DTO.DisponibilidadeResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/disponibilidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisponibilidadeResource {

    @Inject
    DisponibilidadeService service;

    @GET
    public List<DisponibilidadeResponseDTO> listarTodas() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public DisponibilidadeResponseDTO buscar(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/produto/{idProduto}")
    public DisponibilidadeResponseDTO buscarPorProduto(@PathParam("idProduto") Long idProduto) {
        return service.buscarPorProdutoId(idProduto);
    }

    @GET
    @Path("/vencidos")
    public List<DisponibilidadeResponseDTO> listarVencidos() {
        return service.listarVencidos();
    }

    @GET
    @Path("/estoque-baixo")
    public List<DisponibilidadeResponseDTO> listarComEstoqueAbaixo(@QueryParam("limite") int limite) {
        return service.listarComEstoqueBaixo(limite);
    }
}
