package br.com.creatinastore.Creatina.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.creatinastore.Componente.DTO.ComponenteResponseDTO;
import br.com.creatinastore.Creatina.Creatina;
import br.com.creatinastore.Marca.DTO.MarcaResponseDTO;

public record CreatinaResponseDTO(
    Long id,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao,
    String nome,
    BigDecimal preco,
    MarcaResponseDTO marca,
    List<ComponenteResponseDTO> componentes
) {
    public static CreatinaResponseDTO fromEntity(Creatina creatina) {
        return new CreatinaResponseDTO(
            creatina.getId(),
            creatina.getDataCadastro(),
            creatina.getDataAlteracao(),
            creatina.getNome(),
            creatina.getPreco(),
            MarcaResponseDTO.fromEntity(creatina.getMarca()),
            creatina.getComponentes().stream()
                                     .map(ComponenteResponseDTO::fromEntity)
                                     .collect(Collectors.toList())
        );
    }
}

