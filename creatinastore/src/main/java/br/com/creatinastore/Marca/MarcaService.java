package br.com.creatinastore.Marca;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import br.com.creatinastore.Marca.DTO.MarcaRequestDTO;
import br.com.creatinastore.Marca.DTO.MarcaResponseDTO;

@ApplicationScoped
public class MarcaService {

    @Inject
    MarcaRepository repository;

    public List<MarcaResponseDTO> findAll() {
        return repository.listAll()
                            .stream()
                            .map(e -> MarcaResponseDTO.fromEntity(e)).toList();
    }

    public MarcaResponseDTO findById(Long id) {
        Marca marca = repository.findById(id);
        
        if (marca == null)
            throw new NotFoundException("Marca com ID " + id + " não encontrado.");

        return MarcaResponseDTO.fromEntity(marca);
    }

    // Precisa passar esse método para Repository depois
    public List<MarcaResponseDTO> findByNome(String nome) {
        return repository.find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%")
                         .stream()
                         .map(MarcaResponseDTO::fromEntity)
                         .toList();
    }

    @Transactional
    public MarcaResponseDTO create(MarcaRequestDTO dto) {
        Marca marca = new Marca();

        marca.setNome(dto.nome());
        marca.setPaisOrigem(dto.paisOrigem());

        repository.persist(marca);
        return MarcaResponseDTO.fromEntity(marca);
    }

    @Transactional
    public MarcaResponseDTO update(Long id, MarcaRequestDTO dtoNovaMarca) {
        Marca marca = repository.findById(id);

        if(marca == null)
            throw new NotFoundException("Marca com ID " + id + " não encontrado.");

        marca.setNome(dtoNovaMarca.nome());
        marca.setPaisOrigem(dtoNovaMarca.paisOrigem());
        marca.setDataAlteracao(LocalDateTime.now());
        return MarcaResponseDTO.fromEntity(marca);
    }

    @Transactional
    public boolean delete(Long id) {
        Marca marca = repository.findById(id);
        
        if (marca == null)
            throw new NotFoundException("Marca com ID " + id + " não encontrado.");
            
        return repository.deleteById(id);
    }
}
