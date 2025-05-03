package br.com.creatinastore.Marca.DTO;

import java.time.LocalDateTime;

import br.com.creatinastore.Marca.Marca;

public record MarcaResponseDTO(
    Long id,
    String nome,
    String paisOrigem,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static MarcaResponseDTO fromEntity(Marca marca) {
        return new MarcaResponseDTO(marca.getId(), 
                                    marca.getNome(),
                                    marca.getPaisOrigem(),
                                    marca.getDataCadastro(),
                                    marca.getDataAlteracao());
    }
}

