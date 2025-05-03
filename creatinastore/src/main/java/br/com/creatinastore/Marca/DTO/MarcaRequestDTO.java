package br.com.creatinastore.Marca.DTO;

import jakarta.validation.constraints.NotBlank;

public record MarcaRequestDTO(
    @NotBlank(message = "O nome da marca é obrigatório")
    String nome,
    @NotBlank(message = "O nome da marca é obrigatório")
    String paisOrigem
) {}

