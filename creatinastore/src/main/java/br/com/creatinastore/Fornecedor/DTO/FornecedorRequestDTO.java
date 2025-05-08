package br.com.creatinastore.Fornecedor.DTO;

import jakarta.validation.constraints.NotBlank;

public record FornecedorRequestDTO(

    @NotBlank(message = "Nome é obrigatório") 
    String nome,

    @NotBlank(message = "CNPJ é obrigatório") 
    String cnpj,

    String telefone
) {}
