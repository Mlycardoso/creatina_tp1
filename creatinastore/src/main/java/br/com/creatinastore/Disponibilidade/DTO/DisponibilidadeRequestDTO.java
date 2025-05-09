package br.com.creatinastore.Disponibilidade.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisponibilidadeRequestDTO(

    @NotNull(message = "A quantidade em estoque é obrigatória")
    Integer quantidadeEstoque,

    @NotBlank(message = "O lote é obrigatório")
    String lote,
    
    @NotNull(message = "A validade é obrigatória")
    LocalDate validade
) {}
