package br.com.creatinastore.Componente.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.creatinastore.Componente.Componente;

public record ComponenteResponseDTO(
    Long id,
    String nome,
    String descricao,
    BigDecimal quantidade,
    String concentracao,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static ComponenteResponseDTO fromEntity(Componente componente) {
        return new ComponenteResponseDTO(
            componente.getId(),
            componente.getNome(),
            componente.getDescricao(),
            componente.getQuantidade(),
            componente.getConcentracao().getNomeCompleto(),
            componente.getDataCadastro(),
            componente.getDataAlteracao()
        );
    }
}

