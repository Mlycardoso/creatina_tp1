package br.com.creatinastore.Peso.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.creatinastore.Peso.Peso;
import br.com.creatinastore.UnidadePeso.UnidadePeso;

public record PesoResponseDTO(
    Long id,
    BigDecimal valor,
    UnidadePeso unidadePeso,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static PesoResponseDTO fromEntity(Peso peso) {
        return new PesoResponseDTO(peso.getId(), 
                                   peso.getValor(), 
                                   peso.getUnidade(),
                                   peso.getDataCadastro(),
                                   peso.getDataAlteracao());
    }
}