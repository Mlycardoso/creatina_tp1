package br.com.creatinastore.Categoria.DTO;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(

    @NotBlank(message = "Nome é obrigatório") 
    String nome,
    
    String descricao
) { }

