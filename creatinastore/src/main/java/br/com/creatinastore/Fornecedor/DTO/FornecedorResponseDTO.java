package br.com.creatinastore.Fornecedor.DTO;

import java.time.LocalDateTime;

import br.com.creatinastore.Fornecedor.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    String cnpj,
    String telefone,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static FornecedorResponseDTO fromEntity(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getCnpj(),
            fornecedor.getTelefone(),
            fornecedor.getDataCadastro(),
            fornecedor.getDataAlteracao()
        );
    }
}
