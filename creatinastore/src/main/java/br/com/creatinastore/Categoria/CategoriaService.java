package br.com.creatinastore.Categoria;

import java.util.List;

import br.com.creatinastore.Categoria.DTO.CategoriaRequestDTO;
import br.com.creatinastore.Categoria.DTO.CategoriaResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoriaService {

    @Inject
    CategoriaRepository repository;

    public List<CategoriaResponseDTO> findAll() {
        return repository.listAll().stream()
                         .map(CategoriaResponseDTO::fromEntity)
                         .toList();
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = repository.findById(id);

        if (categoria == null)
            throw new NotFoundException("Categoria com ID " + id + " não encontrado.");

        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        repository.persist(categoria);
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id);

        if (categoria == null)
            throw new NotFoundException("Categoria com ID " + id + " não encontrado.");

        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @Transactional
    public boolean delete(Long id) {
        Categoria categoria = repository.findById(id);

        if (categoria == null)
            throw new NotFoundException("Categoria com ID " + id + " não encontrado.");
            
        return repository.deleteById(id);
    }
}
