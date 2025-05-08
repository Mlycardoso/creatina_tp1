package br.com.creatinastore.Fornecedor;

import br.com.creatinastore.Fornecedor.DTO.FornecedorRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    FornecedorService service;

    @GET
    public Response listarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloID(@PathParam("id") Long id) {
        return Response.ok().entity(service.findById(id)).build();
    }

    @POST
    public Response criarNovo(FornecedorRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                       .entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarExistente(@PathParam("id") Long id, FornecedorRequestDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarPeloID(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
