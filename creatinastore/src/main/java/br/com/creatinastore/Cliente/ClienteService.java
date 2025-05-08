package br.com.creatinastore.Cliente;

import java.util.List;

import br.com.creatinastore.Cliente.DTO.ClienteDadosDTO;
import br.com.creatinastore.Cliente.DTO.ClienteResponseDTO;
import br.com.creatinastore.Usuario.DTO.UsuarioLoginDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository repository;

    public List<ClienteResponseDTO> findAll() {
        return repository.listAll()
                         .stream()
                         .map(ClienteResponseDTO::fromEntity)
                         .toList();
    }

    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new NotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%")
                         .stream()
                         .map(ClienteResponseDTO::fromEntity)
                         .toList();
    }

    @Transactional
    public ClienteResponseDTO cadastrarLogin(UsuarioLoginDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.email());
        cliente.setSenha(dto.senha());
        repository.persist(cliente);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    @Transactional
    public ClienteResponseDTO cadastrarDados(Long id, ClienteDadosDTO dto) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new NotFoundException("Cliente com ID " + id + " não encontrado.");
        }

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizarLogin(Long id, UsuarioLoginDTO dto) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new NotFoundException("Cliente com ID " + id + " não encontrado.");
        }

        cliente.setEmail(dto.email());
        cliente.setSenha(dto.senha());

        return ClienteResponseDTO.fromEntity(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizarDados(Long id, ClienteDadosDTO dto) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new NotFoundException("Cliente com ID " + id + " não encontrado.");
        }

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(cliente);
    }

    @Transactional
    public boolean delete(Long id) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new NotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        return repository.deleteById(id);
    }
}

