package br.com.creatinastore.Componente.DTO;

import java.time.LocalDateTime;

import br.com.creatinastore.Componente.Componente;

public record ComponenteResponseDTO(
    Long id,
    String nome,
    String descricao,
    String concentracao,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static ComponenteResponseDTO fromEntity(Componente componente) {
        return new ComponenteResponseDTO(
            componente.getId(),
            componente.getNome(),
            componente.getDescricao(),
            componente.getConcentracao(),
            componente.getDataCadastro(),
            componente.getDataAlteracao()
        );
    }
}

