package br.com.creatinastore.Usuario.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    String senha
) {}

