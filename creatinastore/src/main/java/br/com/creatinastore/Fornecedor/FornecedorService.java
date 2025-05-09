package br.com.creatinastore.Fornecedor;

import java.util.List;

import br.com.creatinastore.Fornecedor.DTO.FornecedorRequestDTO;
import br.com.creatinastore.Fornecedor.DTO.FornecedorResponseDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorService {

    @Inject
    FornecedorRepository repository;

    public List<FornecedorResponseDTO> findAll() {
        return repository.listAll().stream()
                         .map(FornecedorResponseDTO::fromEntity)
                         .toList();
    }

    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = repository.findById(id);

        if (fornecedor == null)
            throw new NotFoundException("Fornecedor com ID " + id + " não encontrado.");

        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO create(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());
        repository.persist(fornecedor);
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO update(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = repository.findById(id);

        if (fornecedor == null)
            throw new NotFoundException("Fornecedor com ID " + id + " não encontrado.");

        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());
        
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public boolean delete(Long id) {
        Fornecedor fornecedor = repository.findById(id);

        if (fornecedor == null)
            throw new NotFoundException("Fornecedor com ID " + id + " não encontrado.");
            
        return repository.deleteById(id);
    }
}

