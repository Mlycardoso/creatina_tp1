package br.com.creatinastore.Componente;

import java.math.BigDecimal;

import br.com.creatinastore.DefaultEntity.DefaultEntity;
import br.com.creatinastore.UnidadePeso.UnidadePeso;
import br.com.creatinastore.UnidadePeso.UnidadePesoConverter;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;

@Entity
@Table(name = "componentes")
public class Componente extends DefaultEntity{

    @Column(nullable = false)
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    @Column(name = "quantidade")
    private BigDecimal quantidade;

    @NotNull
    @Column(name = "concentracao")
    @Convert(converter = UnidadePesoConverter.class)
    private UnidadePeso concentracao;

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

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public UnidadePeso getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(UnidadePeso concentracao) {
        this.concentracao = concentracao;
    }

}
