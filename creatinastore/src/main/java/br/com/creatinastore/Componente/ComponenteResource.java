package br.com.creatinastore.Componente;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import br.com.creatinastore.Componente.DTO.ComponenteRequestDTO;
import br.com.creatinastore.Componente.DTO.ComponenteResponseDTO;

@Path("/componentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComponenteResource {

    @Inject
    ComponenteService service;

    @GET
    public List<ComponenteResponseDTO> listar() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public ComponenteResponseDTO buscar(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Response criar(@Valid ComponenteRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                       .entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public ComponenteResponseDTO atualizar(@PathParam("id") Long id, @Valid ComponenteRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
