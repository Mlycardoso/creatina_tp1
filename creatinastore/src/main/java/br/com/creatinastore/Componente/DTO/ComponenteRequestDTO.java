package br.com.creatinastore.Componente.DTO;

import jakarta.validation.constraints.NotBlank;

public record ComponenteRequestDTO(
    @NotBlank(message = "O nome do componente é obrigatório")
    String nome,
    String descricao,
    @NotBlank(message = "A concentração é obrigatória")
    String concentracao
) {}
