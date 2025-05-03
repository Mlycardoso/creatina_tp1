package br.com.creatinastore.Cliente.DTO;

import java.time.LocalDateTime;

import br.com.creatinastore.Cliente.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getDataCadastro(),
            cliente.getDataAlteracao()
        );
    }
}

