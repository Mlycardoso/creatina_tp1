package br.com.creatinastore.Fornecedor;

import br.com.creatinastore.DefaultEntity.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "fornecedores")
public class Fornecedor extends DefaultEntity{

    @NotBlank
    private String nome;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
