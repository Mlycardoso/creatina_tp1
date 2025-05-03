package br.com.creatinastore.Componente;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import br.com.creatinastore.Componente.DTO.ComponenteRequestDTO;
import br.com.creatinastore.Componente.DTO.ComponenteResponseDTO;

@ApplicationScoped
public class ComponenteService {

    @Inject
    ComponenteRepository repository;

    public List<ComponenteResponseDTO> findAll() {
        return repository.listAll()
                            .stream()
                            .map(e -> ComponenteResponseDTO.fromEntity(e)).toList();
    }

    public ComponenteResponseDTO findById(Long id) {
        Componente componente = repository.findById(id);

        if (componente == null) 
            throw new NotFoundException("Componente com ID " + id + " não encontrado.");
        
        return ComponenteResponseDTO.fromEntity(componente);
    }

    // Precisa passar esse método para Repository depois
    public List<ComponenteResponseDTO> findByNome(String nome) {
        return repository.find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%")
                         .stream()
                         .map(ComponenteResponseDTO::fromEntity)
                         .toList();
    }

    @Transactional
    public ComponenteResponseDTO create(ComponenteRequestDTO dto) {
        Componente componente = new Componente();

        componente.setNome(dto.nome());
        componente.setDescricao(dto.descricao());
        componente.setConcentracao(dto.concentracao());

        repository.persist(componente);
        return ComponenteResponseDTO.fromEntity(componente);
    }

    @Transactional
    public ComponenteResponseDTO update(Long id, ComponenteRequestDTO dtoNovoComponente) {
        Componente componente = repository.findById(id);

        if (componente == null) 
            throw new NotFoundException("Componente com ID " + id + " não encontrado.");
        
        componente.setNome(dtoNovoComponente.nome());
        componente.setDescricao(dtoNovoComponente.descricao());
        componente.setConcentracao(dtoNovoComponente.concentracao());
        componente.setDataAlteracao(LocalDateTime.now());
        return ComponenteResponseDTO.fromEntity(componente);
        
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}

