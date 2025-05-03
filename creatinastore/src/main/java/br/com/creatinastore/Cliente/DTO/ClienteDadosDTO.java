package br.com.creatinastore.Cliente.DTO;

import jakarta.validation.constraints.NotBlank;

public record ClienteDadosDTO(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O CPF é obrigatório")
    String cpf
) {}