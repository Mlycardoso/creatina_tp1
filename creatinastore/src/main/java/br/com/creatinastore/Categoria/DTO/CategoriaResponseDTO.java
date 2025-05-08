package br.com.creatinastore.Categoria.DTO;

import java.time.LocalDateTime;

import br.com.creatinastore.Categoria.Categoria;

public record CategoriaResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static CategoriaResponseDTO fromEntity(Categoria categoria) {
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao(),
            categoria.getDataCadastro(),
            categoria.getDataAlteracao()
        );
    }
}

