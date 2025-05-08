package br.com.creatinastore.Creatina.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public record CreatinaRequestDTO(
    @NotBlank(message = "O nome do produto é obrigatório")
    String nome,

    @NotNull(message = "O preço do produto é obrigatório")
    @PositiveOrZero
    BigDecimal preco,

    @NotNull(message = "O valor do peso do produto é obrigatório")
    @PositiveOrZero
    BigDecimal valorPeso,

    @NotNull(message = "O ID da unidade de peso do produto é obrigatório")
    Integer idUnidadePeso,

    @NotNull(message = "O ID da marca é obrigatório")
    Long marcaId,

    @NotNull(message = "O ID da categoria é obrigatório")
    Long categoriaId,

    @NotNull(message = "O ID do fornecedor é obrigatório")
    Long fornecedorId,

    @NotNull(message = "A lista de componentes é obrigatória")
    List<Long> componentesIds
) {}

