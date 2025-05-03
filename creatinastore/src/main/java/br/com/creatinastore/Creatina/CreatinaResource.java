package br.com.creatinastore.Creatina;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import br.com.creatinastore.Creatina.DTO.CreatinaRequestDTO;
import br.com.creatinastore.Creatina.DTO.CreatinaResponseDTO;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreatinaResource {

    @Inject
    CreatinaService service;

    @GET
    public List<CreatinaResponseDTO> listar() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public CreatinaResponseDTO buscar(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Response criar(@Valid CreatinaRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                       .entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public CreatinaResponseDTO atualizar(@PathParam("id") Long id, @Valid CreatinaRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
