package br.com.creatinastore.Disponibilidade;

import java.time.LocalDate;
import java.util.List;

import br.com.creatinastore.Disponibilidade.DTO.DisponibilidadeResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DisponibilidadeService {

    @Inject
    DisponibilidadeRepository repository;

    public DisponibilidadeResponseDTO findById(Long id) {
        Disponibilidade d = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Disponibilidade não encontrada"));
        return DisponibilidadeResponseDTO.fromEntity(d);
    }

    public DisponibilidadeResponseDTO buscarPorProdutoId(Long idProduto) {
        Disponibilidade d = repository.find("creatina.id", idProduto).firstResult();
        if (d == null)
            throw new NotFoundException("Disponibilidade com ID não encontrada para o produto com ID "+idProduto);
        return DisponibilidadeResponseDTO.fromEntity(d);
    }

    public List<DisponibilidadeResponseDTO> findAll() {
        return repository.listAll().stream()
            .map(DisponibilidadeResponseDTO::fromEntity)
            .toList();
    }

    public List<DisponibilidadeResponseDTO> listarVencidos() {
        return repository.find("validade < ?1", LocalDate.now())
            .stream()
            .map(DisponibilidadeResponseDTO::fromEntity)
            .toList();
    }

    public List<DisponibilidadeResponseDTO> listarComEstoqueBaixo(int limite) {
        return repository.find("quantidadeEstoque < ?1", limite)
            .stream()
            .map(DisponibilidadeResponseDTO::fromEntity)
            .toList();
    }
}

