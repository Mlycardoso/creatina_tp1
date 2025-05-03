package br.com.creatinastore.Cliente;

import java.util.List;

import br.com.creatinastore.Cliente.DTO.ClienteDadosDTO;
import br.com.creatinastore.Cliente.DTO.ClienteResponseDTO;
import br.com.creatinastore.Usuario.DTO.UsuarioLoginDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @GET
    public List<ClienteResponseDTO> listar() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public ClienteResponseDTO buscar(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/busca")
    public List<ClienteResponseDTO> buscarPorNome(@QueryParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    @Path("/login")
    public Response cadastrarLogin(@Valid UsuarioLoginDTO dto) {
        ClienteResponseDTO cliente = service.cadastrarLogin(dto);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @POST
    @Path("/{id}/dados")
    public Response cadastrarDados(@PathParam("id") Long id, @Valid ClienteDadosDTO dto) {
        ClienteResponseDTO cliente = service.cadastrarDados(id, dto);
        return Response.ok(cliente).build();
    }

    @PUT
    @Path("/{id}/login")
    public ClienteResponseDTO atualizarLogin(@PathParam("id") Long id, @Valid UsuarioLoginDTO dto) {
        return service.atualizarLogin(id, dto);
    }

    @PUT
    @Path("/{id}/dados")
    public ClienteResponseDTO atualizarDados(@PathParam("id") Long id, @Valid ClienteDadosDTO dto) {
        return service.atualizarDados(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
