package br.com.creatinastore.Marca;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import br.com.creatinastore.Marca.DTO.MarcaRequestDTO;
import br.com.creatinastore.Marca.DTO.MarcaResponseDTO;

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    public List<MarcaResponseDTO> listar() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public MarcaResponseDTO buscar(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Response criar(@Valid MarcaRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                       .entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public MarcaResponseDTO atualizar(@PathParam("id") Long id, @Valid MarcaRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
