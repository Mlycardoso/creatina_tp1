package br.com.creatinastore.Disponibilidade.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.creatinastore.Disponibilidade.Disponibilidade;

public record DisponibilidadeResponseDTO(
    Long id,
    Integer quantidadeEstoque,
    String lote,
    LocalDate validade,
    Long creatinaId,
    String creatinaNome,
    String creatinaMarcaNome,
    BigDecimal creatinaPreco,
    BigDecimal peso,
    String unidadePeso,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static DisponibilidadeResponseDTO fromEntity(Disponibilidade d) {
        return new DisponibilidadeResponseDTO(
            d.getId(),
            d.getQuantidadeEstoque(),
            d.getLote(),
            d.getValidade(),
            d.getCreatina().getId(),
            d.getCreatina().getNome(),
            d.getCreatina().getMarca().getNome(),
            d.getCreatina().getPreco(),
            d.getCreatina().getPeso().getValor(),
            d.getCreatina().getPeso().getUnidade().getNomeCompleto(),
            d.getDataCadastro(),
            d.getDataAlteracao()
        );
    }
}
