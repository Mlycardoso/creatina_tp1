package br.com.creatinastore.Componente.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComponenteRequestDTO(
    @NotBlank(message = "O nome do componente é obrigatório")
    String nome,

    String descricao,
    
    @NotNull(message = "A quantidade do componente é obrigatória")
    BigDecimal quantidade,

    @NotNull(message = "A unidade de concentração é obrigatória")
    Integer idUnidadePeso
) {}
