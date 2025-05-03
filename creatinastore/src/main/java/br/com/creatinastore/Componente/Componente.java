package br.com.creatinastore.Componente;

import br.com.creatinastore.DefaultEntity.DefaultEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "componentes")
public class Componente extends DefaultEntity{

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private String concentracao;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }
}
